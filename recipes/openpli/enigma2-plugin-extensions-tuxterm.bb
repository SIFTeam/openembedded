DESCRIPTION = "Tuxbox Terminal plugin"
MAINTAINER = "Sven Karschewski <seddi@i-have-a-dreambox.com>"
RDEPENDS_${PN} = "tuxterm"

PV = "0.2+svn${SRCPV}"
PR = "r1"

SRC_URI = "${PLISVNURL}/${PLISVNBRANCH}/external;module=tuxterm-enigma2-plugin;proto=${PLISVNPROTO}"

S = "${WORKDIR}/tuxterm-enigma2-plugin"

inherit autotools

FILES_${PN} = "/"
