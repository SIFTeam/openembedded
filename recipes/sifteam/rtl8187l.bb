DESCRIPTION = "Driver for Realtek RTL8187L USB 802.11b/g WiFi sticks"
SECTION = "kernel/modules"
LICENSE = "GPL"
RDEPENDS = "wireless-tools"

PN = "rtl8187l"

SRCDATE = "20110309"
BRANCH = "master"
PV = "git${SRCDATE}"
SRCREV = "a2ddc6f3893a4c0187f224407bcacde9d53c430d"

inherit module

PR = "r1"

SRC_URI = "git://openee.git.sourceforge.net/gitroot/openee/drivers;protocol=git;branch=${BRANCH};tag=${SRCREV}"

S = "${WORKDIR}/git/wifi/rtl8187l"

EXTRA_OEMAKE = "LINUX_SRC=${STAGING_KERNEL_DIR}"

do_compile () {
        unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS CC LD CPP
        oe_runmake 'MODPATH={D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net' \
                   'KERNEL_SOURCE=${STAGING_KERNEL_DIR}' \
                   'KDIR=${STAGING_KERNEL_DIR}' \
                   'KSRC=${STAGING_KERNEL_DIR}' \
                   'KVER=${KERNEL_VERSION}' \
                   'CC=${KERNEL_CC}' \
                   'LD=${KERNEL_LD}'
}

SRC_URI_append_cuberevo = " file://sh4-fix.patch;patch=1"
SRC_URI_append_cuberevo-mini = " file://sh4-fix.patch;patch=1"
SRC_URI_append_cuberevo-mini2 = " file://sh4-fix.patch;patch=1"
SRC_URI_append_cuberevo-mini-fta = " file://sh4-fix.patch;patch=1"
SRC_URI_append_cuberevo-250hd = " file://sh4-fix.patch;patch=1"
SRC_URI_append_cuberevo-2000hd = " file://sh4-fix.patch;patch=1"
SRC_URI_append_cuberevo-9500hd = " file://sh4-fix.patch;patch=1"
SRC_URI_append_cuberevo-100hd = " file://sh4-fix.patch;patch=1"

do_install() {
	install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/net
	install -m 0644 ${S}/ieee80211/ieee80211_crypt-rtl.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net
	install -m 0644 ${S}/ieee80211/ieee80211_crypt_ccmp-rtl.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net
	install -m 0644 ${S}/ieee80211/ieee80211_crypt_tkip-rtl.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net
	install -m 0644 ${S}/ieee80211/ieee80211_crypt_wep-rtl.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net
	install -m 0644 ${S}/ieee80211/ieee80211-rtl.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net
        install -m 0644 ${S}/rtl8187/rtl8187.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net
}
