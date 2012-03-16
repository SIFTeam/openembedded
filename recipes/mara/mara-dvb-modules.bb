DESCRIPTION = "Hardware drivers for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "proprietary"

KV = "3.2.2"
SRCDATE = "20120207"

PV = "${KV}+${SRCDATE}"
MACHINE_KERNEL_PR_append = ".0"

RDEPENDS_${PN} += "mara-fpupdate"

SRC_URI = "file://mara20120207.zip"

S = "${WORKDIR}"

PACKAGE_STRIP = "no"

inherit module

do_compile() {
}

do_install() {
	install -d ${D}/lib/modules/${KV}/extra
	install -d ${D}/lib/modules/${KV}/firmware
	install -d ${D}/${sysconfdir}/modutils
	for i in a2108 a8286 starci brcm7405 starci; do
		install -m 0755 ${WORKDIR}/$i.ko ${D}/lib/modules/${KV}/extra
		echo $i >> ${D}/${sysconfdir}/modutils/_${MACHINE}
	done
	install -m 0755 ${WORKDIR}/a2108.fw ${D}/lib/modules/${KV}/firmware
}
