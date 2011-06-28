DESCRIPTION = "Hardware drivers for et5000"
SECTION = "base"
PRIORITY = "required"
LICENSE = "proprietary"

SRCDATE = "20110627"

KV = "2.6.18-7.4"
PV = "2.6.18-7.4+${SRCDATE}"
MACHINE_KERNEL_PR_append = ".1"

RDEPENDS = "kernel (${KV}) et-fpupdate"

SRC_URI = "http://www.et-view.com/download/drivers/${MACHINE}-drivers-gcc44-${SRCDATE}.zip"

S = "${WORKDIR}"

PACKAGE_STRIP = "no"

inherit module

do_compile() {
}

do_install() {
	install -d ${D}/lib/modules/${KV}/extra
	install -d ${D}/${sysconfdir}/modutils
	for i in tpm modloader modloader2 dvb; do
		install -m 0755 ${WORKDIR}/$i.ko ${D}/lib/modules/${KV}/extra
		echo $i >> ${D}/${sysconfdir}/modutils/${MACHINE}
	done
}
