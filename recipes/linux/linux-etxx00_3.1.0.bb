DESCRIPTION = "Linux kernel for ${MACHINE}"
LICENSE = "GPL"

MACHINE_KERNEL_PR_append = ".3"

DEPENDS = "mtd-minimal-nand-utils"
RDEPENDS_kernel-image = "mtd-minimal-nand-utils"

SRC_URI += "http://www.et-view.com/download/linux-${PV}.tar.gz \
	file://${MACHINE}_defconfig \
	file://fix-proc-cputype.patch \
	file://dvb-usb-af9035.patch \
	file://dvb-usb-it9135.patch \
	file://tda18218-7mhz-lopass.patch \
	file://dvb-usb-a867.patch \
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
	if [ -d /proc/stb ] ; then
		flash_eraseall -j /dev/mtd1
		nandwrite -p /dev/mtd1 /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz
	fi
	rm -f /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz
	true
}
