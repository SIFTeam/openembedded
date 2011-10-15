CAMNAME = "oscam-unstable"
DESCRIPTION = "${CAMNAME} ${PV} Open Source Softcam"
RDEPENDS = "${CAMNAME}"
RCONFLICTS = "enigma2-plugin-softcams-${CAMNAME}-cs enigma2-plugin-softcams-oscam-stable-cs enigma2-plugin-softcams-oscam-experimental-cs"
PACKAGE_ARCH = "all"
PN = "enigma2-plugin-softcams-${CAMNAME}"
PV = "1.10"
PR = "r4.${INC_PR}"

require softcamoscam.inc


