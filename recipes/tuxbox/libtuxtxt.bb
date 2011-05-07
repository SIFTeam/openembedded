DESCRIPTION = "tuxbox libtuxtxt"
DEPENDS = "dreambox-dvbincludes libpng freetype"

inherit gitpkgv

SRC_URI = "git://openpli.git.sourceforge.net/gitroot/openpli/tuxtxt;protocol=git"

S = "${WORKDIR}/git/libtuxtxt"

PV = "2.0+git${SRCPV}"
PKGV = "2.0+git${GITPKGV}"
PR = "r0"

PROVIDES += "libtuxtxt-enigma2"
RPROVIDES_${PN} += "libtuxtxt-enigma2"

EXTRA_OECONF = "--with-boxtype=generic"

inherit autotools pkgconfig

FILES_libtuxtxt_append = " /usr/lib/libtuxtxt.so"

FILES_${PN}-dev = "/usr/lib/libtuxtxt.la /usr/lib/pkgconfig/tuxbox-tuxtxt.pc"
