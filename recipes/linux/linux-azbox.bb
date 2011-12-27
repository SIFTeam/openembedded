DESCRIPTION = "Linux kernel for azbox hd"
LICENSE = "GPL"
KV = "2.6.22.19"
PV = "2.6.22.19"

MACHINE_KERNEL_PR_append = "41"

SRC_URI += "http://openee.sifteam.eu/azbox/linux-2.6.22-AZBOXHD.tar.gz \
	http://openee.sifteam.eu/azbox/zbimage-linux-xrpc_20111117.zip \
	file://azbox_defconfig"

S = "${WORKDIR}/linux-2.6.22.19"

inherit kernel

export OS = "Linux"
KERNEL_OBJECT_SUFFIX = "ko"
KERNEL_OUTPUT = "vmlinux"
KERNEL_IMAGETYPE = "vmlinux"
KERNEL_IMAGEDEST = "/tmp"

FILES_kernel-image = "/boot/zbimage-linux-xrpc"

do_configure_prepend() {
	oe_machinstall -m 0644 ${WORKDIR}/${MACHINE}_defconfig ${S}/.config
	oe_runmake oldconfig
}

kernel_do_compile() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS MACHINE
	oe_runmake include/linux/version.h CC="${KERNEL_CC}" LD="${KERNEL_LD}"
	oe_runmake ${KERNEL_IMAGETYPE} CC="${KERNEL_CC}" LD="${KERNEL_LD}" AR="${AR}" OBJDUMP="${OBJDUMP}" NM="${NM}"
	oe_runmake modules CC="${KERNEL_CC}" LD="${KERNEL_LD}" AR="${AR}" OBJDUMP="${OBJDUMP}"
}

do_install_append () {
	install -d ${D}/boot
	install -m 0644 ${WORKDIR}/zbimage-linux-xrpc ${D}/boot/zbimage-linux-xrpc
}

pkg_postinst_kernel-image () {
	if [ -e /boot/zbimage-linux-xrpc ]; then
		echo "updating kernel... please DON'T turn off or reboot the decoder"
		dd if=/boot/zbimage-linux-xrpc of=/dev/mtd4
		echo "update completed"
		rm /boot/zbimage-linux-xrpc
	fi
	true
}
