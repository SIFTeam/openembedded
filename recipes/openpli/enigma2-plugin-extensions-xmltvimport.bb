DESCRIPTION = "Imports xmltv files into the EPG cache of enigma2"
MAINTAINER = "MiLo, rytec @ pli-images.org"
LICENSE = "GPLv2"

PV = "1.0"
PR = "r20"

inherit gitrev

OPENPLI_GIT ?= "git://openpli.git.sourceforge.net/gitroot/openpli"
SRC_URI = "${OPENPLI_GIT}/plugin-xmltvimport;protocol=git"

FILES_${PN} = "/usr/bin /usr/lib/enigma2/python"

S = "${WORKDIR}/git/src"

DEPENDS = ""
RDEPENDS = "python-compression python-shell"
RRECOMMENDS = "${PN}-rytec"
PACKAGES = "dbg-${PN} ${PN}"

PLUGIN = "EPGImport"

inherit distutils

FILES_${PN} = "/usr/lib/enigma2"
FILES_dbg-${PN} = "/usr/lib/enigma2/python/Plugins/Extensions/${PLUGIN}/.debug"

do_install_append() {
	# silly hacky me, this could be done by distutils, but i can't figure it out...
	install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions
	mv ${D}/usr/lib/python2.5/site-packages/${PLUGIN} ${D}/usr/lib/enigma2/python/Plugins/Extensions/${PLUGIN}
	mv ${D}/usr/lib/python2.5/site-packages/*.so ${D}/usr/lib/enigma2/python/Plugins/Extensions/${PLUGIN}
	rm -rf ${D}/usr/lib/python2.5/site-packages
	install -m 644 ${S}/EPGImport/plugin.png ${D}/usr/lib/enigma2/python/Plugins/Extensions/${PLUGIN}
}

pkg_postinst() {
	if grep -q PLi $D/etc/image-version
	then
		# PLi needs no patch...
		true
	else
		[ -f $D/usr/bin/enigma2.sh.xmltvbak ] || {
			cp $D/usr/bin/enigma2.sh $D/usr/bin/enigma2.sh.xmltvbak
			sed '3ipython $D/usr/lib/enigma2/python/Plugins/Extensions/EPGImport/boot.py' $D/usr/bin/enigma2.sh.xmltvbak > $D/usr/bin/enigma2.sh
		}
	fi
}

pkg_prerm() {
	if [ -f /usr/bin/enigma2.sh.xmltvbak ] ; then
		mv -f /usr/bin/enigma2.sh.xmltvbak /usr/bin/enigma2.sh
	fi
}
