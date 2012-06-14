DESCRIPTION = "Utils for DVB-C blindscan"
SECTION = "base"
PRIORITY = "optional"
LICENSE = "proprietary"
RDEPENDS = "ncurses"
PACKAGE_ARCH = "${MACHINE_ARCH}"
SRC_URI = "http://archive.vuplus.com/download/utils/vuplus-blindscan-utils-${MACHINE}-${PV}.tar.bz2"

PROVIDES += "virtual/blindscan-dvbc"
RPROVIDES_${PN} += "virtual/blindscan-dvbc"

RREPLACES_${PN} += "vuplus-blindscan-utils"
RCONFLICTS_${PN} += "vuplus-blindscan-utils"

PV = "2.0"
PR = "r2"

S = "${WORKDIR}/blindscan-utils"

do_install() {
	install -d "${D}/${bindir}"
	install -m 0755 "${S}/tda1002x" "${D}/${bindir}"
}
