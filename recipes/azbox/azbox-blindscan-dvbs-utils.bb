DESCRIPTION = "Tool for DVB-S/S2 blindscan"
SECTION = "base"
PRIORITY = "optional"
LICENSE = "proprietary"
PACKAGE_ARCH = "${MACHINE_ARCH}"
SRC_URI = "http://azbox-enigma2-project.googlecode.com/files/azbox-blindscan-utils-${MACHINE}-${PV}.tar.bz2"

PROVIDES += "virtual/blindscan-dvbs"
RPROVIDES_${PN} += "virtual/blindscan-dvbs"

RREPLACES_${PN} += "azbox-blindscan-utils"
RCONFLICTS_${PN} += "azbox-blindscan-utils"

PV = "1.2"
PR = "r2"

S = "${WORKDIR}/blindscan-utils"

do_install() {
	install -d "${D}/${bindir}"
	install -m 0755 "${S}/avl_azbox_blindscan" "${D}/${bindir}"
}

