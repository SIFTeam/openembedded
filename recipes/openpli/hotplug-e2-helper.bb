DESCIPTION = "helper tool to deliver hotplug events to e2"
MAINTAINER = "PLi team"
LICENSE = "GPLv2"

inherit gitpkgv

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
PR = "r0"

SRC_URI = "git://openpli.git.sourceforge.net/gitroot/openpli/${PN};protocol=git"

S = "${WORKDIR}/git"

inherit autotools

pkg_postinst_${PN} () {
	rm -f $D/autofs
	mkdir $D/autofs
	true
}
