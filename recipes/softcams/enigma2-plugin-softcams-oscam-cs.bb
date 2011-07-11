DESCRIPTION = "${CSNAME} as cardserver"
PACKAGE_ARCH = "all"
RDEPENDS = "oscam"
RCONFLICTS = "enigma2-plugin-softcams-${CSNAME}"
PN = "enigma2-plugin-softcams-${CSNAME}-cs"
PV = "1.0"
PR = "r0"


SRC_URI = "file://config/oscam.*"

S = "${WORKDIR}/${CSNAME}"

INHIBIT_PACKAGE_STRIP = "1"

CSNAME = "oscam"
CSSTART = "exec start-stop-daemon -S -x /usr/bin/${CSNAME} -- -b -c /etc/tuxbox/config"

require cardserver.inc

CONFFILES = "/etc/tuxbox/config/oscam.conf /etc/tuxbox/config/oscam.server /etc/tuxbox/config/oscam.srvid /etc/tuxbox/config/oscam.user"

do_install() {
	install -d ${D}/etc/tuxbox/config
	install -m 0644 ${WORKDIR}/config/* ${D}/etc/tuxbox/config/
}

