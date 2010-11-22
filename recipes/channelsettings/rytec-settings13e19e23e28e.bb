OWNER = "rytec"
SATNAMES = "13e19e23e28e"

SRC_URI = "http://www.rytec.be/tools/Rytec_E2_${SATNAMES}_${SRCDATE}.zip"

require rytec-settings.inc
DESCRIPTION = "Rytec settings ${SATNAMES}"

S="${WORKDIR}/Rytec_E2_13e19e23e28e_${SRCDATE}"
