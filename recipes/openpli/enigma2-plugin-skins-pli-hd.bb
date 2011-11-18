DESCRIPTION = "PLi HD skin"
MAINTAINER = "littlesat"

SRCREV_pn-${PN} ?= "${AUTOREV}"
inherit gitpkgv

PV = "0.1+git${SRCPV}"
PKGV = "0.1+git${GITPKGV}"
PR = "r0"

PACKAGES = "${PN}"

SRC_URI = "git://github.com/littlesat/skin-PLiHD.git;protocol=git"

FILES_${PN} = "/usr/share/enigma2/"

RDEPENDS_${PN} = "font-valis-hd"
S = "${WORKDIR}/git"

do_compile() {
}

do_install() {
	install -d ${D}/usr/share
	cp -rp ${S}/usr/share/* ${D}/usr/share/
	chmod -R a+rX ${D}/usr/share/enigma2/
}
