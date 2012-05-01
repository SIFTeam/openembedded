DESCRIPTION = "Linux kernel for ${MACHINE}"
LICENSE = "GPL"

MACHINE_KERNEL_PR_append = ".8"

DEPENDS = "mtd-minimal-nand-utils"
RDEPENDS_kernel-image = "mtd-minimal-nand-utils"

SRC_URI += "http://www.et-view.com/download/linux-${PV}.tar.gz \
	file://${MACHINE}_defconfig \
	file://fix-proc-cputype.patch \
	file://iosched-slice_idle-1.patch \
	file://add-dmx-source-timecode.patch \
	file://rtl8712-release-firmware-fix.patch \
	file://dvb-usb-af9035.patch \
	file://tda18218-7mhz-lopass.patch \
	file://dvb-usb-a867.patch \
	file://dvb-usb-rtl2832.patch \
	file://cxd2820r-enable-LNA-for-DVB-T.patch \
	file://cxd2820r-changed-condition-to-break-out-from-wait-lock-loop.patch \
	file://cxd2820r-output-full-range-SNR.patch \
	file://cinergy_s2_usb_r2.patch \
	file://as102-backports-from-kernel-3.4.patch \
	file://as102-scale-MER-to-full-range.patch \
	file://as102-adjust-signal-strength-report.patch \
	file://em28xx-pre-allocate-DVB-isoc-transfer-buffers.patch \
	file://em28xx-dvb-stop-URBs-when-stopping-the-streaming.patch \
	file://af9015-output-full-range-SNR.patch \
	file://it913x-backports-from-kernel-3.4.patch \
	file://it913x-backports-from-kernel-3.5.patch \
	file://it913x-switch-off-PID-filter-by-default.patch \
	file://tda10071-BUGFIX-delivery-system.patch \
	file://dvb-usb-dib0700-disable-sleep.patch \
	file://dvb_usb_disable_rc_polling.patch \
	"

SRC_URI_append_et5x00 = " file://disable_early_fb.patch"

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
