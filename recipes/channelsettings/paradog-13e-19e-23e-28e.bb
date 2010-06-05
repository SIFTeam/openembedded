DESCRIPTION = "Paradog 13E,19E,23E,28E"

PN = "enigma2-plugin-settings-paradog-13e-19e-23e-28e"
PV = "2007.04.08"
PR = "r1"

SRC_URI = "http://downloads.pli-images.org/settings/paradog-13E-19E-23E-28E_${PV}.tar.bz2"

S = "${WORKDIR}"

INHIBIT_PACKAGE_STRIP = "1"

do_install() {
	install -d ${D}/etc/enigma2
	install -m 0644 ${S}/etc/enigma2/* ${D}/etc/enigma2/
}

PACKAGE_ARCH = "all"
FILES_${PN} = "/"