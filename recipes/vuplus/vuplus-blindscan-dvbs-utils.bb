DESCRIPTION = "Tool for DVB-S blindscan"
SECTION = "base"
PRIORITY = "optional"
LICENSE = "proprietary"
PACKAGE_ARCH = "${MACHINE_ARCH}"
SRC_URI = "http://archive.vuplus.com/download/utils/vuplus-blindscan-utils-${MACHINE}-${PV}.tar.bz2"

PROVIDES += "virtual/blindscan-dvbs"
RPROVIDES_${PN} += "virtual/blindscan-dvbs"

RREPLACES_${PN} += "vuplus-blindscan-utils"
RCONFLICTS_${PN} += "vuplus-blindscan-utils"

PV = "2.0"
PR = "r2"

S = "${WORKDIR}/blindscan-utils"

do_install() {
	install -d "${D}/${bindir}"
	install -m 0755 "${S}/vuplus_blindscan" "${D}/${bindir}"
}
