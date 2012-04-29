DESCRIPTION = "Utils for DVB-S blindscan"
SECTION = "base"
PRIORITY = "optional"
LICENSE = "proprietary"
PACKAGE_ARCH = "${MACHINE_ARCH}"
PACKAGE_STRIP = "no"
SRC_URI = "http://www.et-view.com/download.php?no=81"

PROVIDES += "virtual/blindscan-dvbs"
RPROVIDES_${PN} += "virtual/blindscan-dvbs"

RREPLACES_${PN} += "et-blindscan-utils"
RCONFLICTS_${PN} += "et-blindscan-utils"

PV = "1.0"
PR = "r2"

S = "${WORKDIR}"

do_compile() {
	mv "download.php?no=81" "et-blindscan-utils.ipkg"
	ar x "et-blindscan-utils.ipkg" data.tar.gz
	tar xvfz data.tar.gz usr/bin/avl_xtrend_blindscan
}

do_install() {
	install -m 0755 -d "${D}/${bindir}"
	install -m 0755 "${S}/usr/bin/avl_xtrend_blindscan" "${D}/${bindir}"
}
