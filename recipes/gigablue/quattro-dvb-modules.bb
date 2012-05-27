DESCRIPTION = "Hardware drivers for Gigablue HD Quattro"
SECTION = "base"
PRIORITY = "required"
LICENSE = "proprietary"

KV = "2.6.37-2.8"
PV = "${KV}"

SRCDATE = "20120526"

RDEPENDS = "kernel (${KV})"
PR = "r06-${SRCDATE}"

MACHINE_KERNEL_PR_append = ".${SRCDATE}"

inherit module

do_compile() {
}

PACKAGE_STRIP = "no"

SRC_URI = "file://quattro-dvb-modules-${SRCDATE}.tar.gz"

S = "${WORKDIR}"

do_install() {
    install -d ${D}/lib/modules/${KV}/extra
    for f in *.ko; do
        install -m 0644 ${WORKDIR}/$f ${D}/lib/modules/${KV}/extra/$f;
    done
    install -d ${D}/${sysconfdir}/modutils
    for i in `ls | grep \\.ko | sed -e 's/.ko//g'`; do
        echo $i >> ${D}/${sysconfdir}/modutils/_giga
    done
}
