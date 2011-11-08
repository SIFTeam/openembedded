DESCRIPTION = "Firmware for CARL9170"
LICENCE = "closed"

SRC_URI = "file://carl9170-1.fw"
S = "${WORKDIR}"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 carl9170-1.fw ${D}${base_libdir}/firmware
}

PACKAGES = "${PN}"
FILES_${PN} += "${base_libdir}/firmware"

PACKAGE_ARCH = "all"
