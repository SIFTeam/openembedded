DESCRIPTION = "Linux kernel for ${MACHINE}"
LICENSE = "GPL"
KV = "3.1.1"
SRCREV = "r2"
MACHINE_KERNEL_PR_append = ".15"

DEPENDS = "mtd-utils"
RDEPENDS_kernel-image = "mtd-utils"

SRC_URI += "http://archive.vuplus.com/download/kernel/linux-${KV}_${SRCREV}.tar.bz2 \
	file://${MACHINE}_defconfig \
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
	"

SRC_URI_append_vusolo = " file://fix_cpu_proc_vusolo.patch file://linux_3.1.1_vusolo.patch"
SRC_URI_append_vuduo = " file://fix_cpu_proc_vuduo.patch file://linux_3.1.1_vuduo.patch file://linux-sata_brcm.patch"
SRC_URI_append_vuuno = " file://fix_cpu_proc_vuuno.patch file://linux_3.1.1_vuuno.patch file://linux-sata_brcm.patch"
SRC_URI_append_vuultimo = " file://fix_cpu_proc_vuultimo.patch file://linux-sata_brcm.patch"

S = "${WORKDIR}/linux-${PV}"

inherit kernel

export OS = "Linux"
KERNEL_OBJECT_SUFFIX = "ko"
KERNEL_OUTPUT = "vmlinux"
KERNEL_IMAGETYPE = "vmlinux"
KERNEL_IMAGEDEST = "/tmp"

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
	if [ "x$D" == "x" ]; then
		if [ -f /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz ] ; then
			flash_eraseall -j /dev/mtd1
			nandwrite -p /dev/mtd1 /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz
			rm -f /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz
		fi
	fi
	true
}
