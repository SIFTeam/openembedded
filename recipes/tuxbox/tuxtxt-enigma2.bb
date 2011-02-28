DEPENDS = "freetype libtuxtxt-enigma2"
DESCRIPTION = "tuxbox tuxtxt for enigma2"

inherit gitpkgv

SRC_URI = "git://openpli.git.sourceforge.net/gitroot/openpli/tuxtxt;protocol=git"

S = "${WORKDIR}/git/tuxtxt"

PV = "2.0+git${SRCPV}"
PKGV = "2.0+git${GITPKGV}"
PR = "r0"

FILES_${PN}-dbg = "/usr/lib/.debug"
FILES_${PN}-src = "/usr/lib/enigma2/python/Plugins/Extensions/Tuxtxt/*.py"
FILES_${PN} = "/usr/lib/libtuxtxt32bpp.so* /usr/share/fonts /usr/lib/enigma2/python/Plugins/Extensions/Tuxtxt/*.pyo /etc/tuxtxt"

inherit autotools pkgconfig

EXTRA_OECONF = "--with-boxtype=generic --with-configdir=/etc \
        ${@base_contains("MACHINE_FEATURES", "textlcd", "--with-textlcd" , "", d)} \
	"

