DESCRIPTION = "Tuxbox common files"
LICENSE = "GPL"
MAINTAINER = "openAAF"

PN = "tuxbox-common"
PR = "r7"

PV = "2.0"

SRC_URI = "${PLISVNURL}/${PLISVNBRANCH}/cdk/cdk;module=root;proto=${PLISVNPROTO}"

FILES_${PN} = "/"

S = "${WORKDIR}"

TRANSPONDER_LISTS = "satellites.xml terrestrial.xml"

do_install() {
    install -d ${D}/etc/enigma2
	install -d ${D}/etc/tuxbox
	install -d ${D}/usr/share
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
