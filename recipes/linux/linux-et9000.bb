DESCRIPTION = "Linux kernel for et9000"
LICENSE = "GPL"
KV = "2.6.18-7.4"
PV = "2.6.18-7.4"

#Don't use PR here, use MACHINE_KERNEL_PR in machine.conf instead
#PR = "r6"
#or use MACHINE_KERNEL_PR_append, when a rebuild of external modules is not required

MACHINE_KERNEL_PR_append = ".2"

DEPENDS = "mtd-minimal-nand-utils"
RDEPENDS_kernel-image = "mtd-minimal-nand-utils"

SRC_URI += "http://www.et-view.com/download/stblinux-2.6.18-gcc44.tar.gz \
	file://${MACHINE}_defconfig \
	file://disable_uarts.patch \
	http://trappist.elis.ugent.be/~mronsse/cdfs/download/cdfs-2.6.18.tar.bz2 \
	file://stblinux-2.6.18-cdfs.patch;apply=no \
	file://linux-2.6.18-fix-proc-cputype.patch \
	"

S = "${WORKDIR}/stblinux-2.6.18"

inherit kernel

export OS = "Linux"
KERNEL_OBJECT_SUFFIX = "ko"
KERNEL_OUTPUT = "vmlinux"
KERNEL_IMAGETYPE = "vmlinux"
KERNEL_IMAGEDEST = "/tmp"

FILES_kernel-image = "/${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz"

do_configure_prepend() {
	oe_machinstall -m 0644 ${WORKDIR}/${MACHINE}_defconfig ${S}/.config
	if [ -d ${WORKDIR}/cdfs-${PV} ]; then
		mv ${WORKDIR}/cdfs-${PV} ${S}/fs/cdfs
		cd ${S} & patch -p0 < ${S}/fs/cdfs/patch.cdfs
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
