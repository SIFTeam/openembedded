DESCRIPTION = "Linux kernel for Vu+ duo"
LICENSE = "GPL"
KV = "2.6.18-7.3"
PV = "2.6.18-7.3"
PR = "r4"

MODULE = "stblinux-2.6.18"

#DEPENDS = "mtd-minimal-nand-utils"
#RDEPENDS_kernel-image = "mtd-minimal-nand-utils"


SRC_URI = "http://archive.vuplus.com/download/stblinux-${KV}.tar.bz2 \
	file://linux_bm750_nand.patch;patch=1;pnum=0 \
        file://linux_bm750_proc.patch;patch=1;pnum=0 \
        file://linux_bm750_resource.patch;patch=1;pnum=0 \
        file://linux_bm750_serial.patch;patch=1;pnum=0 \
        file://linux_bm750_setup.patch;patch=1;pnum=0 \
        file://linux_bm750_arch_makefile.patch;patch=1;pnum=0 \
        file://linux_bm750_kobject.patch;patch=1;pnum=0 \
        file://linux_bm750_dvb-core_fe.patch;patch=1;pnum=0 \
	file://bm750_defconfig \
	"


S = "${WORKDIR}/stblinux-2.6.18"

inherit kernel

export OS = "Linux"
KERNEL_IMAGETYPE = "vmlinux"
KERNEL_OUTPUT = "vmlinux"
KERNEL_OBJECT_SUFFIX = "ko"

FILES_kernel-image = "${KERNEL_IMAGEDEST}/*"

do_configure_prepend() {
	oe_machinstall -m 0644 ${WORKDIR}/bm750_defconfig ${S}/.config
	if [ -d ${WORKDIR}/cdfs-${PV} ]; then
		mv ${WORKDIR}/cdfs-${PV} ${S}/fs/cdfs
		cd ${S} & patch -p0 < ${S}/fs/cdfs/patch.cdfs
	fi;
	oe_runmake oldconfig
}

do_install_append () {
        install -d ${D}/boot
        install -m 0755 vmlinux ${D}/boot/vmlinux
        gzip ${D}/boot/vmlinux
}

pkg_preinst_kernel-image () {
	[ -d /proc/stb ] && mount -o rw,remount /boot
	true
	if [ -f /boot/vmlinux.gz ];
	then rm -f /boot/vmlinux.gz;
	fi
}

pkg_postinst_kernel-image () {
	if [ -d /proc/stb ];
	then flash_eraseall /dev/mtd1; nandwrite /dev/mtd1 /boot/vmlinux.gz -p;
	fi
	[ -d /proc/stb ] && mount -o ro,remount /boot
	true

}

pkg_prerm_kernel-image () {
	[ -d /proc/stb ] && mount -o rw,remount /boot
	true
}

pkg_postrm_kernel-image () {
	[ -d /proc/stb ] && mount -o ro,remount /boot
	true
}
