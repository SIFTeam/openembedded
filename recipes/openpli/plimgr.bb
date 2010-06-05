PN = "plimgr"
PV = "1.0+svn${SRCPV}"
PR = "r0"

SRC_URI = "${PLISVNURL}/${PLISVNBRANCH}/cdk/cdk;module=allboxes;proto=${PLISVNPROTO} \
	file://plimgr.sh "

S = "${WORKDIR}/plimgr"

INITSCRIPT_NAME = "plimgr"
INITSCRIPT_PARAMS = "defaults 40 20"

inherit autotools pkgconfig update-rc.d

# NOTE: plimgr itself is installed with pli-plugins; in fact we only need the scripts...

do_install() {
	install -d ${D}/etc/init.d
	install -m 0755 ${WORKDIR}/plimgr.sh ${D}/etc/init.d/plimgr
	install -d ${D}/etc/plimgr/cams
	install -d ${D}/etc/plimgr/cardservers
	install -d ${D}/etc/plimgr/services
	install -d ${D}/etc/plimgr/scripts
	install -m 0755 `find ${WORKDIR}/allboxes/var_init/etc/plimgr/scripts -maxdepth 1 -type f` ${D}/etc/plimgr/scripts
}
