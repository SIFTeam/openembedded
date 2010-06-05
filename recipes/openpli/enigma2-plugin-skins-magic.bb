DESCRIPTION = "Magic skin for Enigma2"
MAINTAINER = "Mike Looijmans"

inherit gitpkgv

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
PR = "r0"

PACKAGES = "${PN} ${PN}-hd ${PN}-ehd font-valis-enigma font-valis-hd"
PROVIDES += "font-valis-enigma font-valis-hd"

SRC_URI = "git://openpli.git.sourceforge.net/gitroot/openpli/skin-magic;protocol=git"

FILES_${PN} = "/usr/share/enigma2/Magic"
FILES_${PN}-hd = "/usr/share/enigma2/Magic-HD"
FILES_${PN}-ehd = "/usr/share/enigma2/Magic-EHD"
FILES_font-valis-enigma = "/usr/share/fonts/valis_enigma.ttf"
FILES_font-valis-hd = "/usr/share/fonts/hd.ttf /usr/share/fonts/hdi.ttf"

RDEPENDS_${PN} = "font-valis-enigma"
RDEPENDS_${PN}-hd = "font-valis-hd"
RDEPENDS_${PN}-ehd = "font-valis-hd"
S = "${WORKDIR}/git"

do_install() {
	install -d ${D}/usr/share
	cp -rp ${S}/usr/share/* ${D}/usr/share/
	chmod -R a+rX ${D}/usr/share/enigma2/
	chmod 644 ${D}/usr/share/fonts/*.ttf
}
