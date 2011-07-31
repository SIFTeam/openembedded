DESCRIPTION = "Boot from CF card (ppanel)"
MAINTAINER = "Mike Looijmans <dream@milosoftware>"
PACKAGE_ARCH = "dm7025"

PV = "1.0"
PR = "r0"

SRC_URI = 'file://*'

FILES_${PN} = "/etc/ppanels/BootFromCF.xml"

S = "${WORKDIR}"

DEPENDS = ""
RDEPENDS_${PN} = "dreambox-boottool-cf"

PACKAGES = "${PN}"


do_install () {
	install -d ${D}/etc/ppanels
	install -m 644 ${S}/BootFromCF.xml ${D}/etc/ppanels/
}


