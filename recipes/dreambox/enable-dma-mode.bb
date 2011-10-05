DESCRIPTION = "Enable DMA mode on 7025 IDE devices"
SECTION = "base"
PRIORITY = "optional"
DEPENDS = ""
LICENSE = "GPL"
PV = "3.2"
PR = "r0"

SRC_URI = "file://init file://enable-dma-mode.sh file://dma_mode.hdc"

do_install () {
	install -d ${D}${sysconfdir}/init.d
	install -d ${D}${sysconfdir}/rcS.d
	install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/${PN}
	install -d ${D}/usr/bin
	install -m 0755 ${WORKDIR}/enable-dma-mode.sh ${D}/usr/bin/enable-dma-mode.sh
	install -d ${D}${sysconfdir}/default
	install -m 0755 ${WORKDIR}/dma_mode.hdc ${D}${sysconfdir}/default/dma_mode.hdc
	ln -sf ../init.d/${PN} ${D}${sysconfdir}/rcS.d/S09${PN}
}

PACKAGES = "${PN}"
FILES_${PN} = "${sysconfdir}/init.d ${sysconfdir}/rcS.d ${sysconfdir}/default /usr/bin/enable-dma-mode.sh"
CONFFILES_${PN} = "${sysconfdir}/default/dma_mode.hdc"
