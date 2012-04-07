DESCRIPTION = "Linux kernel for Gigablue ${MACHINE}"
LICENSE = "GPL"
SRCDATE = "20120404"

MACHINE_KERNEL_PR_append = ".0"

DEPENDS = "mtd-minimal-nand-utils"
RDEPENDS_kernel-image = "mtd-minimal-nand-utils"

SRC_URI = "file://linux-${PV}-gb800xx-${SRCDATE}.tar.gz \
		file://${MACHINE}_defconfig \
		file://fix_cpu_proc.patch;striplevel=1 \
		file://igmp.patch \
		file://linux_3.1.1.patch;striplevel=1 \
        file://fix_cpu_proc2.patch;striplevel=1 \
		file://dvb-usb-af9035.patch \
		file://dvb-usb-it9135.patch \
		file://tda18218-7mhz-lopass.patch \
		file://dvb-usb-a867.patch \
		file://PCTV-DVB-S2-stick-460e.patch \
		file://cxd2820r-enable-LNA-for-DVB-T.patch \
		file://cxd2820r-changed-condition-to-break-out-from-wait-lock-loop.patch \
		file://dvb-usb-smsdvb_fix_frontend.patch \
		file://dvb-usb-rtl2832.patch \
		file://cxd2820r-output-full-range-SNR.patch \
		file://xc3028-fix-center-frequency.patch \
		file://cinergy_s2_usb_r2.patch \
		file://af9015-output-full-range-SNR.patch \
		file://dvb-as102.patch \
		"

S = "${WORKDIR}/linux-${PV}"

inherit kernel

export OS = "Linux"
KERNEL_OBJECT_SUFFIX = "ko"
KERNEL_OUTPUT = "vmlinux"
KERNEL_IMAGETYPE = "vmlinux"
KERNEL_IMAGEDEST = "tmp"

FILES_kernel-image = "/${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz"

do_configure_prepend() {
	oe_machinstall -m 0644 ${WORKDIR}/${MACHINE}_defconfig ${S}/.config
	oe_runmake oldconfig
}

kernel_do_install_append() {
	install -d ${D}/${KERNEL_IMAGEDEST}
	install -m 0755 ${KERNEL_OUTPUT} ${D}/${KERNEL_IMAGEDEST}
	gzip ${D}/${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}
}

pkg_postinst_kernel-image () {
	if [ -d /proc/stb ] ; then
		flash_eraseall -j /dev/mtd2
		nandwrite -p /dev/mtd2 /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz
	fi
	rm -f /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz
	true
}
