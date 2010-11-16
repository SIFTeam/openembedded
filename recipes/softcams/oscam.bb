CAMNAME = "oscam"
DESCRIPTION = "${CAMNAME} ${PV} Open Source Softcam"
RDEPENDS = "oscam-bin"
PACKAGE_ARCH = "all"
PN = "enigma2-plugin-softcams-${CAMNAME}"
PV = "1.0"
PR = "r2"

SRC_URI = "\
        file://config/oscam.* \
	"

S = "${WORKDIR}/${CAMNAME}"

INHIBIT_PACKAGE_STRIP = "1"

CAMSTART = "exec start-stop-daemon -S -x /usr/bin/${CAMNAME} -- -b -c /etc/tuxbox/config"

require softcam.inc

CONFFILES = "/etc/tuxbox/config/oscam.conf /etc/tuxbox/config/oscam.server /etc/tuxbox/config/oscam.srvid /etc/tuxbox/config/oscam.user"

do_install() {
	install -d ${D}/etc/tuxbox/config
	install -m 0644 ${WORKDIR}/config/* ${D}/etc/tuxbox/config/
}
