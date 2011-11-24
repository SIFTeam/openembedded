DESCRIPTION = " Driver for Ralink 2870 USB 802.11n/b/g WiFi sticks"
SECTION = "kernel/modules"
LICENSE = "GPLv2"
RDEPENDS = "wireless-tools"

PN = "rt2870"

SRCDATE = "20110309"
BRANCH = "master"
PV = "2.4.1.0-git${SRCDATE}"
SRCREV = "a2ddc6f3893a4c0187f224407bcacde9d53c430d"

inherit module

PR = "r1"

SRC_URI = "git://openee.git.sourceforge.net/gitroot/openee/drivers;protocol=git;branch=${BRANCH};tag=${SRCREV}"

S = "${WORKDIR}/git/wifi/rt2870"

EXTRA_OEMAKE = "LINUX_SRC=${STAGING_KERNEL_DIR}"

do_install() {
	install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless
	install -m 0644 ${S}/os/linux/rt2870sta.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless
	install -d ${D}/etc/Wireless/RT2870STA
	install -m 0644 ${S}/RT2870STA.dat ${D}/etc/Wireless/RT2870STA
}
