DESCRIPTION = "Custom softcam example and support scripts"

PN = "enigma2-plugin-softcams-customcam"
PV = "1"
PR = "r0"

SRC_URI = "file://*"

CAMNAME = "CustomCAM"

S = "${WORKDIR}"

require softcam.inc

# Users will modify or replace it, so don't upgrade it.
CONFFILES = "/usr/bin/${CAMNAME}"

do_install() {
	install -d ${D}/usr/bin
	install -m 0755 ${S}/${CAMNAME} ${D}/usr/bin/${CAMNAME}
}
