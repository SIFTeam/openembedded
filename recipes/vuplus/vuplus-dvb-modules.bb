DESCRIPTION = "Hardware drivers for VuPlus"
SECTION = "base"
PRIORITY = "required"
LICENSE = "proprietary"


def get_modules_extension(bb, d):
	if bb.data.getVar('GLIBC_ADDONS', d, 1) in ['nptl']:
		return "-gcc4.1"
	return ""

KV = "2.6.18-7.3"
PV = "${KV}"

SRCDATE = "20111122"

RDEPENDS = "kernel (${KV}) kernel-module-firmware-class kernel-module-input kernel-module-evdev kernel-module-i2c-core kernel-module-snd kernel-module-snd-pcm"
MACHINE_KERNEL_PR_append = ".${SRCDATE}.1"

VUPLUS_KERNEL_GCC = "4.4.3"
DOWNLOADMACHINE = "${MACHINE}"
DOWNLOADMACHINE_vuduo = "bm750"

inherit module

do_compile() {
}

do_strip_modules() {
}

SRC_URI = "http://archive.vuplus.com/download/drivers/vuplus-dvb-modules-${DOWNLOADMACHINE}-${KV}-${VUPLUS_KERNEL_GCC}-${SRCDATE}.tar.gz"

S = "${WORKDIR}"

do_install() {
    install -d ${D}/lib/modules/${KV}/extra
    for f in *.ko; do
        install -m 0644 ${WORKDIR}/$f ${D}/lib/modules/${KV}/extra/$f;
    done
    install -d ${D}/${sysconfdir}/modutils
    for i in `ls | grep \\.ko | sed -e 's/.ko//g'`; do
        echo $i >> ${D}/${sysconfdir}/modutils/dvb_vuplus
    done

}
