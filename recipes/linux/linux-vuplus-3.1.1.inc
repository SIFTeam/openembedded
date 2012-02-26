CRIPTION = "Linux kernel for vuplus"
LICENSE = "GPL"
KV = "3.1.1"

SRCREV = "r3"

MODULE = "linux-3.1.1"

DEPENDS = "mtd-minimal-nand-utils"
RDEPENDS_kernel-image = "mtd-minimal-nand-utils"

SRC_URI += "http://archive.vuplus.com/download/kernel/linux-${KV}_${SRCREV}.tar.bz2 \
	file://fix_cpu_proc.patch;striplevel=1 \
	file://iosched-slice_idle-1.patch \
        file://${MACHINE}_defconfig \
        "

S = "${WORKDIR}/linux-${KV}"

inherit kernel


export OS = "Linux"
KERNEL_IMAGETYPE = "vmlinux"
KERNEL_OUTPUT = "vmlinux"
KERNEL_OBJECT_SUFFIX = "ko"
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
        if [ "x$D" == "x" ]; then
                if [ -f /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz ] ; then
                        flash_eraseall -j /dev/mtd1
                        nandwrite -p /dev/mtd1 /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz
                        rm -f /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz
                fi
        fi
        true
}

