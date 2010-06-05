PV = "20100514"
PR = "r0"
DESCRIPTION = "Tune kernel parameters on startup"
PACKAGES = "${PN}"

SRC_URI = "file://${PN}.sh"

INITSCRIPT_NAME = "${PN}"
INITSCRIPT_PARAMS = "start 92 S ."

inherit update-rc.d

do_install() {
	install -d ${D}/etc/init.d
	install -m 0755 ${WORKDIR}/${PN}.sh ${D}/etc/init.d/${PN}
}
