DESCRIPTION = "Hardware drivers for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "proprietary"


KV_azboxme = "3.3.1-opensat"
KV_azboxminime = "3.3.1-opensat"
KV_azboxhd = "3.4.4-opensat"

SRCDATE_azboxme = "20120817"
SRCDATE_azboxminime = "20120817"
SRCDATE_azboxhd = "20120828-2"


PV = "${KV}+${SRCDATE}"
MACHINE_KERNEL_PR_append = ".0"


SRC_URI = "http://azbox-enigma2-project.googlecode.com/files/${MACHINE}-dvb-modules-${KV}-${SRCDATE}.tar.gz"

S = "${WORKDIR}"


PACKAGE_STRIP = "no"

inherit module

do_compile() {
}

do_install_azboxhd() {
    install -d ${D}/lib/modules/${KV}/extra
    install -d ${D}/${sysconfdir}/modutils

    for f in llad em8xxx 863xi2c cx24116 mxl201rf mxl5007t stv6110x stv090x tda10023 zl10353 nimdetect sci 863xdvb; do
	install -m 0644 ${WORKDIR}/$f.ko ${D}/lib/modules/${KV}/extra/$f.ko
	echo $f >> ${D}/${sysconfdir}/modutils/_${MACHINE}
    done

    install -d ${D}/lib/firmware
    install -m 0644 ${WORKDIR}/dvb-fe-cx24116.fw ${D}/lib/firmware/dvb-fe-cx24116.fw


    install -d ${D}/etc/mdev
    install -m 0755 ${WORKDIR}/staticdevices.tar.gz.install ${D}/etc/mdev/staticdevices.tar.gz
}

do_install_azboxme() {
    install -d ${D}/lib/modules/${KV}/extra
    install -d ${D}/${sysconfdir}/modutils

    for f in 865xi2c avl6211 avl2108 mxl241sf nimdetect sci 865xdvb; do
	install -m 0644 ${WORKDIR}/$f.ko ${D}/lib/modules/${KV}/extra/$f.ko
	echo $f >> ${D}/${sysconfdir}/modutils/_${MACHINE}
    done

    install -d ${D}/lib/firmware
    install -m 0644 ${WORKDIR}/dvb-fe-avl2108.fw ${D}/lib/firmware/dvb-fe-avl2108.fw
    install -m 0644 ${WORKDIR}/dvb-fe-avl2108-blind.fw ${D}/lib/firmware/dvb-fe-avl2108-blind.fw
    install -m 0644 ${WORKDIR}/dvb-fe-avl6211.fw ${D}/lib/firmware/dvb-fe-avl6211.fw

    install -d ${D}/etc/mdev
    install -m 0755 ${WORKDIR}/staticdevices.tar.gz.install ${D}/etc/mdev/staticdevices.tar.gz
}

do_install_azboxminime() {
 do_install_azboxme
}

FILES_${PN} = "/"



