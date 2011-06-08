DESCRIPTION = "Enable DMA mode on 7025 IDE devices"
SECTION = "base"
PRIORITY = "optional"
DEPENDS = ""
LICENSE = "GPL"
PV = "2.0"
PR = "r0"

SRC_URI = "file://init"

do_install () {
	install -d ${D}${sysconfdir}/init.d
	install -d ${D}${sysconfdir}/rcS.d
	install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/${PN}
	ln -sf ../init.d/${PN} ${D}${sysconfdir}/rcS.d/S09${PN}
}

PACKAGES = "${PN}"
FILES_${PN} = "${sysconfdir}/init.d ${sysconfdir}/rcS.d"
