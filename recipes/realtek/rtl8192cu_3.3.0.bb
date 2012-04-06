DESCRIPTION = "driver for Realtek USB wireless devices"
SECTION = "kernel/modules"
LICENSE = "GPLv2"

RREPLACES_${PN} = "kernel-module-rtl8192cu"

inherit module 

SRC_URI = "http://downloads.pli-images.org/misc/rtl8188C_8192C_8192D_usb_linux_v3.3.0_2971.20111128.tar.gz \
	file://additional_productids.patch \
	"

S = "${WORKDIR}/rtl8188C_8192C_8192D_usb_linux_v3.3.0_2971.20111128"

MACHINE_KERNEL_PR_append = ".2"

EXTRA_OEMAKE = "KSRC=${STAGING_KERNEL_DIR}"

do_install() {
	install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless
	install -m 0644 ${S}/8192cu.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless
	install -d ${D}/etc/modutils
	echo 8192cu > ${D}/etc/modutils/r8192cu
}

