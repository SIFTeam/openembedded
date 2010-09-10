# EXAMPLE for oscam, does not work yet...
CSNAME = "oscam"
DESCRIPTION = "{CSNAME} ${PV} cardserver"
PN = "enigma2-plugin-softcams-${CSNAME}"
PV = "0.0"
PR = "r0"

SRC_URI = "http://downloads.pli-images.org/softcams/${CSNAME}-${PV}.zip"

S = "${WORKDIR}/${CSNAME}"

INHIBIT_PACKAGE_STRIP = "1"

CSSTART = "exec start-stop-daemon -S -b -x /usr/bin/${CSNAME}"

require cardserver.inc

do_install() {
	install -d ${D}/usr/bin
	install -m 0755 ${S}/bin/${CSNAME}.mips ${D}/usr/bin/${CSNAME}
	install -d ${D}/etc/${CSNAME}
}

pkg_postinst() {
}

