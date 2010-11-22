PV = "${SRCDATE}"
PR = "r0"

SRC_URI = "http://downloads.pli-images.org/settings/${SETTINGS_FILENAME}_${SRCDATE}.tar.gz"

S = "${WORKDIR}"

include e2settings.inc
