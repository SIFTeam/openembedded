DESCRIPTION = "tuxbox libtuxtxt"
DEPENDS = "dreambox-dvbincludes libpng freetype"

inherit gitpkgv

SRC_URI = "git://openpli.git.sourceforge.net/gitroot/openpli/tuxtxt;protocol=git"
SRC_URI_azboxhd = "git://openpli.git.sourceforge.net/gitroot/openpli/tuxtxt;protocol=git \
		file://libtuxtxt_azbox.patch"
SRC_URI_azboxme = "git://openpli.git.sourceforge.net/gitroot/openpli/tuxtxt;protocol=git \
		file://libtuxtxt_azbox.patch"

S = "${WORKDIR}/git/libtuxtxt"

PV = "2.0+git${SRCPV}"
PKGV = "2.0+git${GITPKGV}"
PR = "r1"

PROVIDES += "libtuxtxt-enigma2"
RPROVIDES_${PN} += "libtuxtxt-enigma2"

EXTRA_OECONF = "--with-boxtype=generic"

inherit autotools pkgconfig

FILES_libtuxtxt_append = " /usr/lib/libtuxtxt.so"

FILES_${PN}-dev = "/usr/lib/libtuxtxt.la /usr/lib/pkgconfig/tuxbox-tuxtxt.pc"
