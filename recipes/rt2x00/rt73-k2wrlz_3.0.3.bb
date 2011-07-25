DESCRIPTION = "Enhanced Driver for Ralink rt73 USB 802.11g WiFi sticks"
HOMEPAGE = "http://homepages.tu-darmstadt.de/~p_larbig/wlan"
SECTION = "kernel/modules"
LICENSE = "GPL"

MACHINE_KERNEL_PR_append = ".2"

SRC_URI="http://homepages.tu-darmstadt.de/~p_larbig/wlan/${PN}-${PV}.tar.bz2"

inherit module
 
S = "${WORKDIR}/${PN}-${PV}/Module/"
 
EXTRA_OEMAKE = "KERNDIR=${STAGING_KERNEL_DIR}"
 
do_install() {
	install -d ${D}${base_libdir}/modules/${KERNEL_VERSION}/extra
	install -m 0644 rt73${KERNEL_OBJECT_SUFFIX} ${D}${base_libdir}/modules/${KERNEL_VERSION}/extra/
	install -d ${D}/etc/modutils
	echo "rt73 ifname=wlan%d" > ${D}/etc/modutils/rt73
}

SRC_URI[md5sum] = "904c1e22c5d635ba24c88e5f92c84941"
SRC_URI[sha256sum] = "1d4b57fb2f5f44b98c556af0fa6b130ed2fad58b28d15029e6d5f31d531990fc"
