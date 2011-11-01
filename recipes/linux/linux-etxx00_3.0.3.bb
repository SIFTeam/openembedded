DESCRIPTION = "Linux kernel for ${MACHINE}"
LICENSE = "GPL"
KV = "3.0.3"

#Don't use PR here, use MACHINE_KERNEL_PR in machine.conf instead
#PR = "r6"
#or use MACHINE_KERNEL_PR_append, when a rebuild of external modules is not required

MACHINE_KERNEL_PR_append = ".5"

DEPENDS = "mtd-minimal-nand-utils"
RDEPENDS_kernel-image = "mtd-minimal-nand-utils"

SRC_URI += "http://www.et-view.com/download/linux-${KV}.tar.gz \
	file://${MACHINE}_defconfig \
	file://linux-${KV}-fix-proc-cputype.patch \
	file://linux-3.0-dvb-usb-af9035.patch \
	file://linux-3.0-tda18218-7mhz-lopass.patch \
	file://linux-3.0-dvb-usb-a867.patch \
	"

S = "${WORKDIR}/linux-${KV}"

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
