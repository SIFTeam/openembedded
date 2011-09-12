# we cannot use PACKAGES_DYNAMIC = "enigma2-plugin-.*"  here, because enigma2-plugins already has it,
# so we only publish enigma2-plugin-pli-.* here (as a result, only those can occur in any RDEPENDS)

PACKAGES_DYNAMIC = "enigma2-plugin-pli-.*"

DEPENDS = "nfs-utils ushare twistedsnmp"

RDEPENDS_enigma2-plugin-pli-snmpagent = "enigma2-plugin-pli-bitrate twistedsnmp"

DESCRIPTION_enigma2-plugin-extensions-ushare = "UPnP media server"
RDEPENDS_enigma2-plugin-extensions-ushare = "ushare"

DESCRIPTION_enigma2-plugin-pli-bitrate = "Bitrate viewer"
DESCRIPTION_enigma2-plugin-pli-newsreader = "RSS reader"

DESCRIPTION_enigma2-plugin-pli-buienradar = "Buienradar actuele situatie"

DESCRIPTION_enigma2-plugin-pli-nfsserver = "NFS server configuration"
RDEPENDS_enigma2-plugin-pli-nfsserver = "nfs-utils"

DESCRIPTION_enigma2-plugin-pli-remotecontrolchannel = "Remote control channel selection"

DESCRIPTION_enigma2-plugin-pli-screenposition = "Adjust the screen position"
PACKAGE_ARCH_enigma2-plugin-pli-screenposition = "${MACHINE_ARCH}"

DESCRIPTION_enigma2-plugin-pli-streaminterface = "Stream webinterface on port 40080"
RDEPENDS_enigma2-plugin-pli-streaminterface = "python-twisted-web"

inherit gitpkgv

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
PR = "r2"

SRC_URI = "git://openpli.git.sourceforge.net/gitroot/openpli/enigma2-plugins;protocol=git \
		   file://pythonpaths.patch"


S = "${WORKDIR}/git"

inherit autotools

EXTRA_OECONF = "--with-boxtype=${MACHINE} \
    STAGING_INCDIR=${STAGING_INCDIR} \
    STAGING_LIBDIR=${STAGING_LIBDIR}"

python populate_packages_prepend () {

	enigma2_plugindir = bb.data.expand('${libdir}/enigma2/python/Plugins', d)

	do_split_packages(d, enigma2_plugindir, '(.*?/.*?)/.*', 'enigma2-plugin-%s', '%s ', recursive=True, match_path=True, prepend=True, extra_depends = "enigma2")

	# we have to perform some tricks to get non-standard files in the plugin packages,
	# unfortunately FILES_append doesn't work
	def files_append(pn, newfiles):
		files = bb.data.getVar('FILES_' + pn, d, 1)
		if files:
			files += " " + newfiles + " "
			bb.data.setVar('FILES_' + pn, files, d)

	files_append('enigma2-plugin-pli-newsreader', '/etc/feeds.xml.default')
	files_append('enigma2-plugin-pli-bitrate', '/usr/bin/bitrate')
	files_append('enigma2-plugin-pli-ppanel', '/etc/ppanels')
}

do_install_append() {
	rm ${D}/usr/lib/enigma2/python/Plugins/PLi/PPanel/url.py
	find ${D}/usr/lib/enigma2/python/ -name '*.pyc' -exec rm {} \;
}

pkg_postinst_enigma2-plugin-pli-newsreader() {
	[ -e $D/etc/feeds.xml ] || mv $D/etc/feeds.xml.default $D/etc/feeds.xml
}
