DESCRIPTION = "v4l dvb firmware files"

PV = "1.0"
PR = "r1"

SRC_URI = "file://firmware.tar.gz"

S = "${WORKDIR}"

FILES_${PN} = "/lib/firmware"

do_install() {
	install -d ${D}/lib/firmware
	install -m 0755 ${S}/*.* ${D}/lib/firmware
}

PACKAGE_ARCH = "all"