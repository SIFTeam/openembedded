DESCRIPTION = "Hardware drivers for Gigablue HD 800 Series"
SECTION = "base"
PRIORITY = "required"
LICENSE = "proprietary"

KV = "2.6.36"
PV = "${KV}"

SRCDATE = "20120116"

RDEPENDS = "kernel (${KV}) kernel-module-firmware-class kernel-module-i2c-core"
PR = "r03-${SRCDATE}"

MACHINE_KERNEL_PR_append = ".${SRCDATE}"

inherit module

do_compile() {
}

do_strip_modules() {
}

SRC_URI = "file://gb800xx-dvb-modules-${SRCDATE}.tar.gz"

S = "${WORKDIR}"

do_install() {
    install -d ${D}/lib/modules/${KV}/extra
    for f in *.ko; do
        install -m 0644 ${WORKDIR}/$f ${D}/lib/modules/${KV}/extra/$f;
    done
    install -d ${D}/${sysconfdir}/modutils
    for i in `ls | grep \\.ko | sed -e 's/.ko//g'`; do
        echo $i >> ${D}/${sysconfdir}/modutils/_tm
    done

}
