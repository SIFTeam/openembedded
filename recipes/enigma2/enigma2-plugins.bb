DESCRIPTION = "Additional plugins for Enigma2"
MAINTAINER = "Felix Domke <tmbinc@elitedvb.net>"

PACKAGES_DYNAMIC = "enigma2-plugin-*"

TAG = ""
PV = "experimental-cvs${SRCDATE}"
PR = "r3"

SRC_URI = "cvs://anonymous@cvs.schwerkraft.elitedvb.net/cvsroot/enigma2-plugins;module=enigma2-plugins;method=pserver${TAG};date=${SRCDATE}"

SRC_URI += " \
	file://autotimer-nl.patch \
	file://netcaster.patch \
	file://networkbrowser-nl.diff;striplevel=0 \
	file://networkbrowser.diff;striplevel=0 \
	file://webif-datetime.patch \
	file://webif-moviefolder.patch \
	file://webif-grabrefresh.patch \
	file://webif-https-disable.patch \
	file://removeunwanted.patch \
	file://dosomething.patch \
	file://imdb_eventinfo.patch \
	file://shoutcast_etc.patch \
	file://networkbrowser-serial.patch \
	file://networkbrowser-remount.patch \
	file://mytube-default.patch \
	"

CONFFILES_${PN} += "${sysconfdir}/enigma2/movietags"
FILES_${PN} += " /usr/share/enigma2 /usr/share/fonts "
FILES_${PN}-meta = "${datadir}/meta"
PACKAGES += "${PN}-meta"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit autotools

S = "${WORKDIR}/enigma2-plugins"

DEPENDS = "python-pyopenssl python-gdata streamripper python-mutagen python-twisted"
DEPENDS += "enigma2"

python populate_packages_prepend () {
	enigma2_plugindir = bb.data.expand('${libdir}/enigma2/python/Plugins', d)

	do_split_packages(d, enigma2_plugindir, '(.*?/.*?)/.*', 'enigma2-plugin-%s', 'Enigma2 Plugin: %s', recursive=True, match_path=True, prepend=True)

	def getControlLines(mydir, d, package):
		import os
		try:
			#ac3lipsync is renamed since 20091121 to audiosync.. but rename in cvs is not possible without lost of revision history..
			#so the foldername is still ac3lipsync
			if package == 'audiosync':
				package = 'ac3lipsync'
			src = open(mydir + package + "/CONTROL/control").read()
		except IOError:
			return
		for line in src.split("\n"):
			if line.startswith('Package: '):
				full_package = line[9:]
			if line.startswith('Depends: '):
				# some plugins still reference twisted-* dependencies, these packages are now called python-twisted-*
				depends = line[9:].replace(',', '').split(' ')
				rdepends = ''
				for depend in depends:
					if depend.startswith('twisted-'):
						rdepends += ' ' + depend.replace('twisted-', 'python-twisted-')
					else:
						rdepends += ' ' + depend
				bb.data.setVar('RDEPENDS_' + full_package, rdepends, d)
			if line.startswith('Description: '):
				bb.data.setVar('DESCRIPTION_' + full_package, line[13:], d)
			if line.startswith('Replaces: '):
				bb.data.setVar('RREPLACES_' + full_package, ' '.join(line[10:].split(', ')), d)
			if line.startswith('Conflicts: '):
				bb.data.setVar('RCONFLICTS_' + full_package, ' '.join(line[11:].split(', ')), d)
			if line.startswith('Maintainer: '):
				bb.data.setVar('MAINTAINER_' + full_package, line[12:], d)


	mydir = bb.data.getVar('D', d, 1) + "/../enigma2-plugins/"
	for package in bb.data.getVar('PACKAGES', d, 1).split():
		getControlLines(mydir, d, package.split('-')[-1])
}
