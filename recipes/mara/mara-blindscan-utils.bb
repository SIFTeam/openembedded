DESCRIPTION = "Utilities needed to do transponder blindscan with Xtrend dvb receivers"
SECTION = "base"
PRIORITY = "optional"
MAINTAINER = "AndyBlac"
LICENSE = "GPL"
RDEPENDS_${PN} = "ncurses"
PV = "1.0"
PR = "r0"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PROVIDES += "virtual/blindscanutils"
RPROVIDES_${PN} += "virtual/blindscanutils"

SRC_URI = "file://et-blindscan-utils.tar.gz"

S = "${WORKDIR}/et-blindscan-utils/"

do_install() {
	install -d ${D}/${bindir}/
	for i in `find ${S} -type f -maxdepth 1`; do
		install -m 0755 $i ${D}/${bindir}/;
	done;
}
