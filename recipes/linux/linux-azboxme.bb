DESCRIPTION = "Linux kernel for azboxme"
LICENSE = "GPL"
KV = "2.6.22"
PV = "2.6.22"

#Don't use PR here, use MACHINE_KERNEL_PR in machine.conf instead
#PR = "r6"
#or use MACHINE_KERNEL_PR_append, when a rebuild of external modules is not required

PR = "r2"

SRC_URI += "http://downloads.sourceforge.net/project/rticoree2/kernel/azboxme-linux-2.6.22.tar.gz \
	file://azboxme_defconfig"

S = "${WORKDIR}/linux-2.6.22.19"

inherit kernel

export OS = "Linux"
KERNEL_OBJECT_SUFFIX = "ko"
KERNEL_OUTPUT = "vmlinux"
KERNEL_IMAGETYPE = "vmlinux"
KERNEL_IMAGEDEST = "/tmp"

do_configure_prepend() {
	oe_machinstall -m 0644 ${WORKDIR}/azboxme_defconfig ${S}/.config
	oe_runmake oldconfig
}

kernel_do_compile() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS MACHINE
	oe_runmake include/linux/version.h CC="${KERNEL_CC}" LD="${KERNEL_LD}"
	oe_runmake ${KERNEL_IMAGETYPE} CC="${KERNEL_CC}" LD="${KERNEL_LD}" AR="${AR}" OBJDUMP="${OBJDUMP}" NM="${NM}"
	oe_runmake modules CC="${KERNEL_CC}" LD="${KERNEL_LD}" AR="${AR}" OBJDUMP="${OBJDUMP}"
}

do_install_append () {
	true
}

pkg_postinst_kernel-image () {
	true
}
