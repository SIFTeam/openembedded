DESCRIPTION = "Hardware drivers for Gigablue HD 800 Series"
SECTION = "base"
PRIORITY = "required"
LICENSE = "proprietary"

PV = "3.1.1"

SRCDATE = "20120604"
RCONFLICTS_${PN} = "gb800se-dvb-modules"
RREPLACES_${PN} = "gb800se-dvb-modules"

RDEPENDS = "kernel (${PV})"
PR = "r08-${SRCDATE}"

MACHINE_KERNEL_PR_append = ".${SRCDATE}"

inherit module

do_compile() {
}

do_strip_modules() {
}

SRC_URI = "file://gigablue-gb800xx-drivers-${PV}-${SRCDATE}.zip"

S = "${WORKDIR}"

do_install() {
    install -d ${D}/lib/modules/${PV}/extra
    for f in *.ko; do
        install -m 0644 ${WORKDIR}/$f ${D}/lib/modules/${PV}/extra/$f;
    done
    install -d ${D}/${sysconfdir}/modutils
    for i in `ls | grep \\.ko | sed -e 's/.ko//g'`; do
        echo $i >> ${D}/${sysconfdir}/modutils/_giga
    done

}
