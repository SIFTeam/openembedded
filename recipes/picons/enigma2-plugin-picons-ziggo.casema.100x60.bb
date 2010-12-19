PICON_SATNAMES = "ziggo.casema"

SRCDATE = "20101204"
PN_SUFFIX = ".100x60"
PV = "${SRCDATE}"
PR = "r0"

SRC_URI = "http://downloads.pli-images.org/picons/picons-ziggo-casema-100x60-${SRCDATE}.tar.gz"

include enigma2-plugin-picons.inc

RCONFLICTS = "enigma2-plugin-picons-ziggo.casema"
