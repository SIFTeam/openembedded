DESCRIPTION = "newcs ${PV} cardserver"

PN = "enigma2-plugin-softcams-newcs"
PV = "1.67RC1"
PR = "r4"

SRC_URI = "http://downloads.pli-images.org/softcams/newcs-${PV}.zip\
	http://downloads.pli-images.org/softcams/newcs.xml"

S = "${WORKDIR}/newcs-1.67_RC1"

INHIBIT_PACKAGE_STRIP = "1"

CSNAME = "newcs"
CSSTART = "exec start-stop-daemon -S -b -x /usr/bin/${CSNAME}"

require cardserver.inc

do_install() {
	install -d ${D}/usr/bin
	install -m 0755 ${S}/bin/newcs.mips ${D}/usr/bin/newcs
	install -d ${D}/etc/tuxbox/config
	install -m 0644 ${WORKDIR}/newcs.xml ${D}/etc/tuxbox/config/newcs.xml.example
}

pkg_postinst () {
	[ -e $D/etc/tuxbox/config/newcs.xml ] || cp $D/etc/tuxbox/config/newcs.xml.example $D/etc/tuxbox/config/newcs.xml
}

