DESCRIPTION = "Hardware drivers for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "proprietary"

KV = "3.3.0"
SRCDATE = "20120331"

PV = "${KV}+${SRCDATE}"
MACHINE_KERNEL_PR_append = ".0"

RDEPENDS_${PN} += "et-fpupdate"

SRC_URI = "http://www.et-view.com/download/drivers/${MACHINE}-drivers-${KV}-${SRCDATE}.zip"

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
		echo $i >> ${D}/${sysconfdir}/modutils/_${MACHINE}
	done
}
