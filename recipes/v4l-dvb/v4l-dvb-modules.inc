DESCRIPTION = "v4l-dvb drivers"
HOMEPAGE = "http://linuxtv.org/"
SECTION = "kernel/modules"
LICENSE = "GPL"

inherit module

PACKAGES_DYNAMIC += "v4l-dvb-module-*"
PACKAGES_DYNAMIC += "v4l-dvb-firmware-*"

do_configure() {
	install -m 0644 ${WORKDIR}/defconfig ${S}/v4l/.config
}

do_compile() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	oe_runmake DIR="${STAGING_KERNEL_DIR}" ${MAKE_TARGETS}
}

do_install() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	oe_runmake DIR="${STAGING_KERNEL_DIR}" DEPMOD=echo DESTDIR="${D}" install
	install -d ${D}/etc/modutils
	if [ "${KERNEL_MAJOR_VERSION}" = "2.6" ]; then
		install -d ${D}/etc/modprobe.d
	fi
}

PACKAGES = "v4l-dvb-modules"
FILES_${PN} = ""
ALLOW_EMPTY_${PN} = "1"

pkg_postinst_modules () {
if [ -n "$D" ]; then
	${HOST_PREFIX}depmod -A -b $D -F ${STAGING_KERNEL_DIR}/System.map-${KERNEL_VERSION} ${KERNEL_VERSION}
else
	depmod -a
	update-modules || true
fi
}

pkg_postrm_modules () {
update-modules || true
}

autoload_postinst_fragment() {
if [ x"$D" = "x" ]; then
	modprobe %s || true
fi
}

python populate_packages_prepend () {
	import os
	def extract_modinfo(file):
		import tempfile, re
		tempfile.tempdir = bb.data.getVar("WORKDIR", d, 1)
		tf = tempfile.mkstemp()
		tmpfile = tf[1]
		cmd = "PATH=\"%s\" %sobjcopy -j .modinfo -O binary %s %s" % (bb.data.getVar("PATH", d, 1), bb.data.getVar("HOST_PREFIX", d, 1) or "", file, tmpfile)
		os.system(cmd)
		f = open(tmpfile)
		l = f.read().split("\000")
		f.close()
		os.close(tf[0])
		os.unlink(tmpfile)
		exp = re.compile("([^=]+)=(.*)")
		vals = {}
		for i in l:
			m = exp.match(i)
			if not m:
				continue
			vals[m.group(1)] = m.group(2)
		return vals

	def parse_depmod():
		import re

		dvar = bb.data.getVar('PKGD', d, 1)
		if not dvar:
			bb.error("D not defined")
			return

		kernelver = bb.data.getVar('KERNEL_VERSION', d, 1)
		kernelver_stripped = kernelver
		m = re.match('^(.*-hh.*)[\.\+].*$', kernelver)
		if m:
			kernelver_stripped = m.group(1)
		path = bb.data.getVar("PATH", d, 1)
		host_prefix = bb.data.getVar("HOST_PREFIX", d, 1) or ""

		cmd = "PATH=\"%s\" %sdepmod -n -a -r -b %s %s" % (path, host_prefix, dvar, kernelver_stripped)
		f = os.popen(cmd, 'r')

		deps = {}
		pattern0 = "^(.*\.k?o):..*$"
		pattern1 = "^(.*\.k?o):\s*(.*\.k?o)\s*$"
		pattern2 = "^(.*\.k?o):\s*(.*\.k?o)\s*\\\$"
		pattern3 = "^\t(.*\.k?o)\s*\\\$"
		pattern4 = "^\t(.*\.k?o)\s*$"

		line = f.readline()
		while line:
			if not re.match(pattern0, line):
				line = f.readline()
				continue
			m1 = re.match(pattern1, line)
			if m1:
				deps[m1.group(1)] = m1.group(2).split()
			else:
				m2 = re.match(pattern2, line)
				if m2:
					deps[m2.group(1)] = m2.group(2).split()
					line = f.readline()
					m3 = re.match(pattern3, line)
					while m3:
						deps[m2.group(1)].extend(m3.group(1).split())
						line = f.readline()
						m3 = re.match(pattern3, line)
					m4 = re.match(pattern4, line)
					deps[m2.group(1)].extend(m4.group(1).split())
			line = f.readline()
		f.close()
		return deps

	def get_dependencies(file, pattern, format):
		prefix = os.path.normpath(os.path.join(
			os.path.join(bb.data.getVar('PKGD', d, 1) or ''),
			'lib/modules',
			bb.data.getVar('KERNEL_VERSION', d, 1)
		)) + '/'
		file = file.replace(prefix, '', 1)

		if module_deps.has_key(file):
			import re
			dependencies = []
			for i in module_deps[file]:
				m = re.match(pattern, os.path.basename(i))
				if not m:
					continue
				on = legitimize_package_name(m.group(1))
				dependency_pkg = format % on
				dependencies.append(dependency_pkg)
			return dependencies
		return []

	def frob_metadata(file, pkg, pattern, format, basename):
		import re
		vals = extract_modinfo(file)

		dvar = bb.data.getVar('PKGD', d, 1)

		# If autoloading is requested, output /etc/modutils/<name> and append
		# appropriate modprobe commands to the postinst
		autoload = bb.data.getVar('module_autoload_%s' % basename, d, 1)
		if not autoload:
			# Also, try canonical name with dashes
			autoload = bb.data.getVar('module_autoload_%s' % basename.replace('_', '-'), d, 1)
		modutilsname = basename
		if not autoload and basename.startswith('dvb-usb-'):
			# force autoload for all usb modules
			autoload = basename
			# but use a custom modutils file name
			modutilsname = 'v4l-%s' % basename
		if autoload:
			name = '%s/etc/modutils/%s' % (dvar, modutilsname)
			f = open(name, 'w')
			for m in autoload.split():
				f.write('%s\n' % m)
			f.close()
			postinst = bb.data.getVar('pkg_postinst_%s' % pkg, d, 1)
			if not postinst:
				bb.fatal("pkg_postinst_%s not defined" % pkg)
			postinst += bb.data.getVar('autoload_postinst_fragment', d, 1) % autoload
			bb.data.setVar('pkg_postinst_%s' % pkg, postinst, d)

		# Write out any modconf fragment
		modconf = bb.data.getVar('module_conf_%s' % basename, d, 1)
		if modconf:
			if bb.data.getVar("KERNEL_MAJOR_VERSION", d, 1) == "2.6":
				name = '%s/etc/modprobe.d/%s.conf' % (dvar, basename)
			else:
				name = '%s/etc/modutils/%s.conf' % (dvar, basename)
			f = open(name, 'w')
			f.write("%s\n" % modconf)
			f.close()

		files = bb.data.getVar('FILES_%s' % pkg, d, 1)
		files = "%s /etc/modutils/%s /etc/modutils/%s.conf /etc/modprobe.d/%s.conf" % (files, modutilsname, basename, basename)
		bb.data.setVar('FILES_%s' % pkg, files, d)

		if vals.has_key("description"):
			old_desc = bb.data.getVar('DESCRIPTION_' + pkg, d, 1) or ""
			bb.data.setVar('DESCRIPTION_' + pkg, old_desc + "; " + vals["description"], d)

		rdepends_str = bb.data.getVar('RDEPENDS_' + pkg, d, 1)
		if rdepends_str:
			rdepends = rdepends_str.split()
		else:
			rdepends = []
		rdepends.extend(get_dependencies(file, pattern, format))
		bb.data.setVar('RDEPENDS_' + pkg, ' '.join(rdepends), d)

	module_deps = parse_depmod()
	module_regex = '^(.*)\.k?o$'
	module_pattern = 'v4l-dvb-module-%s'

	postinst = bb.data.getVar('pkg_postinst_modules', d, 1)
	postrm = bb.data.getVar('pkg_postrm_modules', d, 1)

        maybe_update_modules = "update-modules "
        if bb.data.getVar("ONLINE_PACKAGE_MANAGEMENT", d) == "none":
                maybe_update_modules = ""

	do_split_packages(d, root='/lib/firmware', file_regex='^(.*)\.bin$', output_pattern='v4l-dvb-firmware-%s', description='Firmware for %s', recursive=True, extra_depends='')
	do_split_packages(d, root='/lib/firmware', file_regex='^(.*)\.fw$', output_pattern='v4l-dvb-firmware-%s', description='Firmware for %s', recursive=True, extra_depends='')
	do_split_packages(d, root='/lib/modules', file_regex=module_regex, output_pattern=module_pattern, description='%s kernel module', postinst=postinst, postrm=postrm, recursive=True, hook=frob_metadata, extra_depends='%skernel-%s' % (maybe_update_modules, bb.data.getVar("KERNEL_VERSION", d, 1)))

	import re
	metapkg = bb.data.getVar('PN', d, 1)
	bb.data.setVar('ALLOW_EMPTY_' + metapkg, "1", d)
	bb.data.setVar('FILES_' + metapkg, "", d)
	blacklist = []
	depchains = (d.getVar("DEPCHAIN_POST", True) or "").split()
	for l in module_deps.values():
		for i in l:
			pkg = module_pattern % legitimize_package_name(re.match(module_regex, os.path.basename(i)).group(1))
			blacklist.append(pkg)
	metapkg_rrecommends = []
	packages = bb.data.getVar('PACKAGES', d, 1).split()
	for pkg in packages[1:]:
		if not pkg in blacklist and not pkg in metapkg_rrecommends and not any(pkg.endswith(post) for post in depchains):
			metapkg_rrecommends.append(pkg)
	bb.data.setVar('RRECOMMENDS_' + metapkg, ' '.join(metapkg_rrecommends), d)
	bb.data.setVar('DESCRIPTION_' + metapkg, 'v4l-dvb modules meta package', d)
}
