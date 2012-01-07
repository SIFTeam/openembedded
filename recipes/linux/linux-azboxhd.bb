DESCRIPTION = "Linux kernel for azbox hd"
LICENSE = "GPL"
KV = "2.6.22"
PV = "2.6.22"

#Don't use PR here, use MACHINE_KERNEL_PR in machine.conf instead
#PR = "r6"
#or use MACHINE_KERNEL_PR_append, when a rebuild of external modules is not required

PR = "r3"

SRC_URI += "http://downloads.sourceforge.net/project/rticoree2/kernel/azboxhd-linux-2.6.22.tar.gz \
	http://openee.sifteam.eu/azbox/zbimage-linux-xrpc-2.6.22.19_25.zip \
	file://azboxhd_defconfig \
	file://tangofreq.patch;pnum=1"

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
	[ -e /boot/zbimage-linux-xrpc ] && rm /boot/zbimage-linux-xrpc
	true
}
