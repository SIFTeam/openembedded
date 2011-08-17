DESCRIPTION = "Linux kernel for Vu+ duo"
LICENSE = "GPL"
KV = "2.6.18-7.3"
PV = "2.6.18-7.3"

#Don't use PR here, use MACHINE_KERNEL_PR in machine.conf instead
#PR = "r6"

MACHINE_KERNEL_PR_append = ".4"

DEPENDS = "mtd-minimal-nand-utils"
RDEPENDS_kernel-image = "mtd-minimal-nand-utils"

SRC_URI = "http://archive.vuplus.com/download/stblinux-${KV}.tar.bz2 \
	file://linux_bm750_nand.patch;striplevel=0 \
	file://linux_bm750_proc.patch;striplevel=0 \
	file://linux_bm750_resource.patch;striplevel=0 \
	file://linux_bm750_serial.patch;striplevel=0 \
	file://linux_bm750_setup.patch;striplevel=0 \
	file://linux_bm750_arch_makefile.patch;striplevel=0 \
	file://linux_bm750_kobject.patch;striplevel=0 \
	file://linux-2.6.18-dvb-frontends.patch;striplevel=0 \
	file://linux-2.6.18-dvb-core.patch;striplevel=0 \
	file://dvb-include-2.6.18-5.3.patch;striplevel=0 \
	file://bm750_defconfig \
	"

SRC_URI += "file://linux_bm750_gcc_4.4.patch"

S = "${WORKDIR}/stblinux-2.6.18"

inherit kernel

export OS = "Linux"
KERNEL_OBJECT_SUFFIX = "ko"
KERNEL_OUTPUT = "vmlinux"
KERNEL_IMAGETYPE = "vmlinux"
KERNEL_IMAGEDEST = "tmp"

FILES_kernel-image = "/${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz"

do_configure_prepend() {
	oe_machinstall -m 0644 ${WORKDIR}/bm750_defconfig ${S}/.config
	if [ -d ${WORKDIR}/cdfs-${PV} ]; then
		mv ${WORKDIR}/cdfs-${PV} ${S}/fs/cdfs
		cd ${S}; patch -p0 < ${S}/fs/cdfs/patch.cdfs
	fi;
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

