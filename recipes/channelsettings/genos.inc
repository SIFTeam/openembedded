FILEDATE = "10.28"
PV = "2007.${FILEDATE}"
PR = "r1"

SRC_URI = "http://downloads.pli-images.org/helenite/channelsettings/${SETTINGS_FILENAME}.v${FILEDATE}.tar.gz"

S = "${WORKDIR}"

include settings.inc
