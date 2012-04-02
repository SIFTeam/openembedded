DESCRIPTION = "Utilities for transponder & dvb-s blindscan"
SECTION = "base"
PRIORITY = "optional"
RDEPENDS = "ncurses"
PV = "2.0"
PR = "r0"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PROVIDES += "virtual/satscanutils"
RPROVIDES_${PN} += "virtual/satscanutils"


SRC_URI = "http://archive.vuplus.com/download/utils/vuplus-blindscan-utils-${MACHINE}-${PV}.tar.bz2"

S = "${WORKDIR}/blindscan-utils"

do_install() {
	install -d ${D}/${bindir}/
	for i in `find ${S} -type f -maxdepth 1`; do
		install -m 0755 $i ${D}/${bindir}/;
	done;
}
