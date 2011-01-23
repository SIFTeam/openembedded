CAMNAME = "scam"
DESCRIPTION = "${CAMNAME} ${PV} softcam"
RDEPENDS = "libcrypto-compat"

PN = "enigma2-plugin-softcams-${CAMNAME}"
PV = "3.53"
PR = "r2"

SRC_URI = "http://downloads.pli-images.org/softcams/scam_v${PV}.zip"

S = "${WORKDIR}/scam_v${PV}/"

INHIBIT_PACKAGE_STRIP = "1"

require softcam.inc

do_compile_prepend() {
	mv ${WORKDIR}/scam\ v${PV}/* ${S}
}

do_install() {
	install -d ${D}/usr/bin
	install -m 0755 ${S}/${CAMNAME}.${TARGET_ARCH} ${D}/usr/bin/${CAMNAME}
}
