PICON_SATNAMES = "ziggo.casema"

PV = "${SRCDATE}"
PR = "r1"

SRC_URI = "http://downloads.pli-images.org/picons/picons-ziggo-casema-${SRCDATE}.tar.gz"

include enigma2-plugin-picons.inc

RCONFLICTS = "enigma2-plugin-picons-ziggo.casema.100x60"
