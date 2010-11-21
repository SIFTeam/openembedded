DESCRIPTION = "Magic skin for Enigma2"
MAINTAINER = "Mike Looijmans"
DESCRIPTION_font-valis-hd = "Valis hd fonts"
DESCRIPTION_font-valis-enigma = "Valis enigma font"

inherit gitpkgv

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
PR = "r1"

PV_font-valis-enigma = "2009.11.12"
PKGV_font-valis-enigma = "${PV_font-valis-enigma}"
PV_font-valis-hd = "2010.05.14"
PKGV_font-valis-hd = "${PV_font-valis-hd}"
PR_font-valis-enigma = "r0"
PR_font-valis-hd = "r0"

PACKAGES = "${PN} skin-magic-hd-common ${PN}-hd ${PN}-ehd ${PN}-hd-lite ${PN}-ehd-lite font-valis-enigma font-valis-hd"
PROVIDES += "font-valis-enigma font-valis-hd skin-magic-hd-common"

OPENPLI_GIT ?= "git://openpli.git.sourceforge.net/gitroot/openpli"
SRC_URI = "${OPENPLI_GIT}/skin-magic;protocol=git"

FILES_${PN} = "/usr/share/enigma2/Magic"
FILES_skin-magic-hd-common = "/usr/share/enigma2/Magic-HD-Common/"
FILES_${PN}-hd = "/usr/share/enigma2/Magic-HD/"
FILES_${PN}-hd-lite = "/usr/share/enigma2/Magic-HD-Light/"
FILES_${PN}-ehd = "/usr/share/enigma2/Magic-EHD/"
FILES_${PN}-ehd-lite = "/usr/share/enigma2/Magic-EHD-Light/"
FILES_font-valis-enigma = "/usr/share/fonts/valis_enigma.ttf"
FILES_font-valis-hd = "/usr/share/fonts/hd.ttf /usr/share/fonts/hdi.ttf"

RDEPENDS_${PN} = "font-valis-enigma"
RDEPHD = "font-valis-hd skin-magic-hd-common"
RDEPENDS_${PN}-hd = "${RDEPHD}"
RDEPENDS_${PN}-hd-lite = "${RDEPHD}"
RDEPENDS_${PN}-ehd = "${RDEPHD}"
RDEPENDS_${PN}-ehd-lite = "${RDEPHD}"
S = "${WORKDIR}/git"

do_install() {
	install -d ${D}/usr/share
	cp -rp ${S}/usr/share/* ${D}/usr/share/
	chmod -R a+rX ${D}/usr/share/enigma2/
	chmod 644 ${D}/usr/share/fonts/*.ttf
}
