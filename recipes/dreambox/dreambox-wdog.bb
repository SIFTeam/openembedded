DESCRIPTION = "Watchdog to automatically restart binaries"
SECTION = "base"
PRIORITY = "optional"
LICENSE = "proprietary"
MAINTAINER = "Felix Domke <tmbinc@elitedvb.net>"

PV = "1.1"
PV_mipsel = "1.2"
PR = "r0"

SRC_URI = "http://sources.dreamboxupdate.com/download/7020/wdog-${MACHINE}-${PV}"

S = "${WORKDIR}"

INHIBIT_PACKAGE_STRIP = "1"

do_install() {
	install -d ${D}/usr/bin
	install -m 0755 ${WORKDIR}/wdog-${MACHINE}-${PV} ${D}/usr/bin/wdog
}

PACKAGE_ARCH := "${MACHINE_ARCH}"
