CAMNAME = "opencam"
DESCRIPTION = "${CAMNAME} ${OPENCAM_VERSION} softcam"
PN = "enigma2-plugin-softcams-opencam"
OPENCAM_VERSION = "2.0.13"
PV = "v${OPENCAM_VERSION}"
PR = "r1"

SRC_URI = "http://downloads.pli-images.org/softcams/opencam_7025_${OPENCAM_VERSION}.tar.gz"

S = "${WORKDIR}/"

require softcam.inc

INHIBIT_PACKAGE_STRIP = "1"
CONFFILES = "/etc/opencam.conf"

do_install() {
	install -d ${D}/usr/bin
	install -m 0755 ${S}/opencam_7025 ${D}/usr/bin/${CAMNAME}
	install -d ${D}/etc
	install -m 0644 ${S}/opencam.conf ${D}/etc/opencam.conf
}
