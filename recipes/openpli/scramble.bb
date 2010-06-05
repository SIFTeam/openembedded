PV = "1.0+svn${SRCPV}"
PR = "r0"

SRC_URI = "${PLISVNURL}/${PLISVNBRANCH}/cdk/apps/tuxbox/tools;module=${PN};rev=${SVNREV};proto=${PLISVNPROTO}"

S = "${WORKDIR}/scramble"

inherit autotools pkgconfig

do_install_append() {
	[ -e ${D}/usr/bin/descramble ] && rm ${D}/usr/bin/descramble
}
