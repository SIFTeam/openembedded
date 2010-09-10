CAMNAME = "scam"
DESCRIPTION = "${CAMNAME} ${PV} softcam"

PN = "enigma2-plugin-softcams-${CAMNAME}"
PV = "3.53"
PR = "r1"

SRC_URI = "http://downloads.pli-images.org/softcams/scam_v${PV}.zip"

S = "${WORKDIR}/scam\ v${PV}/"

INHIBIT_PACKAGE_STRIP = "1"

require softcam.inc

do_install() {
	install -d ${D}/usr/bin
	install -m 0755 ${S}/${CAMNAME}.${TARGET_ARCH} ${D}/usr/bin/${CAMNAME}
}
