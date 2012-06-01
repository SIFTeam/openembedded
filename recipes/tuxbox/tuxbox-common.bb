DESCRIPTION = "Tuxbox common files"
LICENSE = "GPL"
MAINTAINER = "PLi team"

PR = "r3"

PV = "1.0+svn${SRCREV}"

SRC_URI = "${PLISVNURL}/${PLISVNBRANCH}/cdk/cdk;module=root;proto=${PLISVNPROTO}"

FILES_${PN} = "/"

S = "${WORKDIR}"

TRANSPONDER_LISTS = "satellites.xml terrestrial.xml"

do_install() {
	install -d ${D}/etc/tuxbox/
	install -d ${D}/usr/share/tuxbox
	install -m 0644 ${S}/root/share/tuxbox/scart.conf ${D}/etc/tuxbox/scart.conf

	install -m 0644 ${S}/root/etc/timezone.xml ${D}/etc/tuxbox/timezone.xml
	ln -sf /etc/tuxbox/timezone.xml ${D}/etc/

	ln -sf /usr/share ${D}/share

	for i in ${TRANSPONDER_LISTS}; do
		install -m 0644 ${S}/root/share/tuxbox/$i ${D}/etc/tuxbox/$i
		ln -sf /etc/tuxbox/$i ${D}/etc/;
		ln -sf /etc/tuxbox/$i ${D}/usr/share/;
		ln -sf /etc/tuxbox/$i ${D}/usr/share/tuxbox/;
	done;
}
