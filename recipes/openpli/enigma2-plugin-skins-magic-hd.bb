DESCRIPTION = "Magic HD skins for Enigma2"
MAINTAINER = "Mike Looijmans"

inherit gitpkgv

EPSM = "enigma2-plugin-skins-magic"
PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
PR = "r3"

PV_font-valis-hd = "2010.05.14"
PR_font-valis-hd = "r1"
PKGV_font-valis-hd = "${PV_font-valis-hd}"
DESCRIPTION_font-valis-hd = "Valis hd fonts"

PACKAGES = "skin-magic-hd-common ${EPSM}-hd ${EPSM}-ehd ${EPSM}-hd-lite ${EPSM}-ehd-lite font-valis-hd"
PROVIDES = "${PN} font-valis-hd skin-magic-hd-common"

SRC_URI = "git://git.assembla.com/openvix.7.git;protocol=git;branch=experimental file://pli.png"

FILES_skin-magic-hd-common = "/usr/share/enigma2/Magic-HD-Common/"
FILES_${EPSM}-hd = "/usr/share/enigma2/Magic-HD/"
FILES_${EPSM}-hd-lite = "/usr/share/enigma2/Magic-HD-Light/"
FILES_${EPSM}-ehd = "/usr/share/enigma2/Magic-EHD/"
FILES_${EPSM}-ehd-lite = "/usr/share/enigma2/Magic-EHD-Light/"
FILES_font-valis-hd = "/usr/share/fonts/hd.ttf /usr/share/fonts/hdi.ttf"

RDEPHD = "font-valis-hd skin-magic-hd-common"
RDEPENDS_${EPSM}-hd = "${RDEPHD}"
RDEPENDS_${EPSM}-hd-lite = "${RDEPHD}"
RDEPENDS_${EPSM}-ehd = "${RDEPHD}"
RDEPENDS_${EPSM}-ehd-lite = "${RDEPHD}"
S = "${WORKDIR}/git"

# replace logo
do_compile() {
	mv ${WORKDIR}/pli.png ${S}/usr/share/enigma2/Magic-HD-Common/logos/
	rm -f ${S}/usr/share/enigma2/Magic-HD-Common/logos/vix.png
	for fn in ${S}/usr/share/enigma2/Magic-*/skin.xml
	do
		sed -i 's#/vix.png#/pli.png#g' "${fn}"
	done
}

do_install() {
	install -d ${D}/usr/share
	cp -rp ${S}/usr/share/* ${D}/usr/share/
	chmod -R a+rX ${D}/usr/share/enigma2/
	chmod 644 ${D}/usr/share/fonts/*.ttf
}
