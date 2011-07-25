DESCRIPTION = "Driver for Realtek 871x series USB 802.11b/g/n WiFi stick"
HOMEPAGE = "http://www.realtek.com.tw"
SECTION = "kernel/modules"
LICENSE = "GPL"

MACHINE_KERNEL_PR_append = ".2"

SRC_URI = "http://downloads.pli-images.org/misc/rtl871x.tgz \
           file://mipsel-compatibility.patch"

inherit module

S = "${WORKDIR}/rtl871x"
 
EXTRA_OEMAKE = "KERNDIR=${STAGING_KERNEL_DIR}"

do_compile () {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS CC LD CPP
	oe_runmake 'MODPATH={D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net' \
		'KERNEL_SOURCE=${STAGING_KERNEL_DIR}' \
		'LINUX_SRC=${STAGING_KERNEL_DIR}' \
		'KDIR=${STAGING_KERNEL_DIR}' \
		'KERNDIR=${STAGING_KERNEL_DIR}' \
		'KSRC=${STAGING_KERNEL_DIR}' \
		'KERNEL_VERSION=${KERNEL_VERSION}' \
		'KVER=${KERNEL_VERSION}' \
		'CC=${KERNEL_CC}' \
		'AR=${KERNEL_AR}' \
		'LD=${KERNEL_LD}'
}

do_install() {
	install -d ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/
	install -m 0644 8712u${KERNEL_OBJECT_SUFFIX} ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/
	install -d ${D}/etc/modutils
	echo 8712u > ${D}/etc/modutils/wlan8712u
}
