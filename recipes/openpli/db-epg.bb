DEPENDS = "sqlite3 tuxbox-libs"

PV = "1.0+svn${SRCPV}"
PR = "r0"

SRC_URI = "${PLISVNURL}/${PLISVNBRANCH}/cdk/apps/tuxbox/tools;module=db_epg;proto=${PLISVNPROTO}"

S = "${WORKDIR}/db_epg"

inherit autotools pkgconfig
