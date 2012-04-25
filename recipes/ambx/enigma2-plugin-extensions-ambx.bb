DESCRIPTION = "Create nifty light effects with an amBX kit"
MAINTAINER = "MiLo @ pli-images.org"
LICENSE = "GPLv2"

inherit gitpkgv

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
PR = "r1"

OPENPLI_URI = "git://openpli.git.sourceforge.net/gitroot/openpli/plugin-ambx;protocol=git"
GIGABLUE_URI = "git://git.openmips.com/plugin-ambx.git;protocol=git"

SRC_URI = "${@base_contains("MACHINE_FEATURES", "gigablue", "${GIGABLUE_URI}", "${OPENPLI_URI}", d)}"

FILES_${PN} = "/usr/bin /usr/lib/enigma2/python"

S = "${WORKDIR}/git/plugin"

DEPENDS = "ambx"
RDEPENDS_${PN} = "python-pyambx"
PACKAGES = "${PN}"

PLUGIN = "amBX"

inherit distutils

FILES_${PN} = "/usr/lib/enigma2"

do_install_append() {
	# silly hacky me, this could be done by distutils, but i can't figure it out...
	install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions
	mv ${D}/usr/lib/python2.6/site-packages/${PLUGIN} ${D}/usr/lib/enigma2/python/Plugins/Extensions/${PLUGIN}
	rm -rf ${D}/usr/lib/python2.6/site-packages
	install -m 644 ${S}/${PLUGIN}/plugin.png ${D}/usr/lib/enigma2/python/Plugins/Extensions/${PLUGIN}
}

