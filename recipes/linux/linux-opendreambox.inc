DESCRIPTION = "Linux Kernel"
SECTION = "kernel"
LICENSE = "GPL"

inherit kernel

PATCHLEVEL ?= ""

SRC_URI = "${KERNELORG_MIRROR}/pub/linux/kernel/v2.6/linux-${PV}${PATCHLEVEL}.tar.bz2 \
	http://sources.dreamboxupdate.com/download/kernel-patches/${P}-${SRCREV}.patch.bz2 \
	file://defconfig"

S = "${WORKDIR}/linux-${PV}"

FILES_kernel-image = "/boot/vmlinux.gz /boot/autoexec.bat"

KERNEL_IMAGETYPE = "vmlinux"
KERNEL_OUTPUT = "vmlinux"

PKG_kernel-image = "kernel-image"
PKG_kernel-base = "kernel-base"
RPROVIDES_kernel = "kernel-${KERNEL_VERSION}"
RPROVIDES_kernel-image = "kernel-image-${KERNEL_VERSION}"
RPROVIDES_kernel-base = "kernel-base-${KERNEL_VERSION}"
PROVIDES += "kernel-${KERNEL_VERSION} kernel-image-${KERNEL_VERSION} kernel-base-${KERNEL_VERSION}"

addtask munge after before do_patch after do_unpack

do_munge () {
	if [ ${PATCHLEVEL} != "" ]; then
		mv ${S}${PATCHLEVEL} ${S};
	fi;
}

do_configure_prepend () {
	install -m 0644 ${WORKDIR}/defconfig ${S}/.config
	oe_runmake oldconfig
}

do_install_append () {
	install -d ${D}/boot
	install -m 0755 vmlinux ${D}/boot/vmlinux
	echo "/flash/bootlogo.elf" > ${D}/boot/autoexec.bat
	gzip ${D}/boot/vmlinux
	echo "/flash/vmlinux.gz" >> ${D}/boot/autoexec.bat
}

pkg_preinst_kernel-image () {
	[ -d /proc/stb ] && mount -t jffs2 mtd:'boot partition' /boot
	true
}

pkg_postinst_kernel-image () {
	[ -d /proc/stb ] && umount /boot
	true
}

pkg_prerm_kernel-image () {
	[ -d /proc/stb ] && mount -t jffs2 mtd:'boot partition' /boot
	true
}

pkg_postrm_kernel-image () {
	[ -d /proc/stb ] && umount /boot
	true
}
