SECTION = "base"
PRIORITY = "required"
LICENSE = "proprietary"

KV = "2.6.31-3.2"
PV = "2.6.31-3.2+${SRCDATE}"
MACHINE_KERNEL_PR_append = ".1"

RDEPENDS = "kernel (${KV}) et-fpupdate"

SRC_URI = "http://www.et-view.com/download/drivers/${MACHINE}-drivers-2.6.31-${SRCDATE}.zip"

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
