DESCRIPTION = "SHR Phone UI Daemon"
HOMEPAGE = "http://shr-project.org/"
LICENSE = "GPL"
SECTION = "x11/applications"
DEPENDS += " dbus-glib libframeworkd-glib libphone-ui sqlite3 shr-specs"
PV = "0.0.0+gitr${SRCREV}"
PR = "r3"

SRC_URI = "git://git.shr-project.org/repo/phoneuid.git;protocol=http;branch=master"
S = "${WORKDIR}/git"

inherit autotools

EXTRA_OECONF = "\
       SPECS_PATH=${STAGING_DATADIR}/shr-specs \
"


FILES_${PN} += "${datadir}"
