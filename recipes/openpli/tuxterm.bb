DESCRIPTION = "TuxTerm"
MAINTAINER = "Sven Karschewski <seddi@i-have-a-dreambox.com>"
DEPENDS = "freetype"

PN = "tuxterm"
PV = "0.2+svn${SRCPV}"
PR = "r1"

SRC_URI = "${PLISVNURL}/${PLISVNBRANCH}/external;module=${PN};proto=${PLISVNPROTO}"

S = "${WORKDIR}/${PN}"

inherit autotools

FILES_${PN} = "/"

EXTRA_OECONF = "${@base_contains("MACHINE_FEATURES", "32bpp", "--with-bpp=32" , "--with-bpp=8", d)}"
