DESCRIPTION = "Hardware drivers for Gigablue HD Quattro"
SECTION = "base"
PRIORITY = "required"
LICENSE = "proprietary"

KV = "2.6.37-2.5"
PV = "${KV}"

SRCDATE = "20110101"

RDEPENDS = "kernel (${KV})"
PR = "r04-${SRCDATE}"

MACHINE_KERNEL_PR_append = ".${SRCDATE}"

inherit module

do_compile() {
}

PACKAGE_STRIP = "no"

SRC_URI = "file://quattro-dvb-modules-${SRCDATE}.tar.gz"

S = "${WORKDIR}"

do_install() {
    install -d ${D}/lib/modules/${KV}/extra
	install -d ${D}/${sysconfdir}/modutils
	for i in fb dvb-core dvb; do
		install -m 0755 ${WORKDIR}/$i.ko ${D}/lib/modules/${KV}/extra
		echo $i >> ${D}/${sysconfdir}/modutils/_${MACHINE}
	done
}
