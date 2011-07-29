CAMNAME = "oscam-experimental"
DESCRIPTION = "${CAMNAME} ${PV} Open Source Softcam"
RDEPENDS = "${CAMNAME}"
RCONFLICTS = "enigma2-plugin-softcams-${CAMNAME}-cs enigma2-plugin-softcams-oscam-stable-cs enigma2-plugin-softcams-oscam-unstable-cs"
PACKAGE_ARCH = "all"
PN = "enigma2-plugin-softcams-${CAMNAME}"
PV = "1.20"
PR = "r3"

SRC_URI = "\
        file://config/${CAMNAME}/oscam.* \
	"

S = "${WORKDIR}/${CAMNAME}"

INHIBIT_PACKAGE_STRIP = "1"

CAMSTART = "exec start-stop-daemon -S -x /usr/bin/${CAMNAME} -- -b -r 2 -c /etc/tuxbox/config/${CAMNAME}"

require softcamoscam.inc

CONFFILES = "/etc/tuxbox/config/${CAMNAME}/oscam.conf /etc/tuxbox/config/${CAMNAME}/oscam.server /etc/tuxbox/config/${CAMNAME}/oscam.srvid /etc/tuxbox/config/${CAMNAME}/oscam.user /etc/tuxbox/config/${CAMNAME}/oscam.ac /etc/tuxbox/config/${CAMNAME}/oscam.cert /etc/tuxbox/config/${CAMNAME}/oscam.dvbapi /etc/tuxbox/config/${CAMNAME}/oscam.guess /etc/tuxbox/config/${CAMNAME}/oscam.ird /etc/tuxbox/config/${CAMNAME}/oscam.pem /etc/tuxbox/config/${CAMNAME}/oscam.provid /etc/tuxbox/config/${CAMNAME}/oscam.services /etc/tuxbox/config/${CAMNAME}/oscam.tiers"

do_install() {
	install -d ${D}/etc/tuxbox/config/${CAMNAME}
	install -m 0644 ${WORKDIR}/config/${CAMNAME}/* ${D}/etc/tuxbox/config/${CAMNAME}/
}
