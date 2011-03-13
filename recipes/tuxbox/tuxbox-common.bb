DESCRIPTION = "Tuxbox common files"
LICENSE = "GPL"
MAINTAINER = "Felix Domke <tmbinc@elitdvb.net>"

PN = "tuxbox-common"
PR = "r1"

PV = "1.0+svn${SRCREV}"

SRC_URI = "cvs://anoncvs@cvs.tuxbox.org/cvs/tuxbox/;module=cdk/root/share/tuxbox;method=ext;rsh=ssh;tag=dreambox;date=${SRCDATE} \
	http://dreamboxupdate.com/download/opendreambox/tuxbox-common-r11.tar.gz"

###
###OpenPLi stuff begin
SRCREV_FORMAT = "satsxml"

SRC_URI += " ${PLISVNURL}/${PLISVNBRANCH}/cdk/cdk/root;module=share;proto=${PLISVNPROTO};name=satsxml \
	${PLISVNURL}/${PLISVNBRANCH}/cdk/cdk/root;module=etc;proto=${PLISVNPROTO}"

do_openpli_preinstall() {
	install -m 0644 ${WORKDIR}/etc/timezone.xml ${S}
	install -m 0644 ${WORKDIR}/share/tuxbox/satellites.xml ${WORKDIR}/tuxbox
}

addtask openpli_preinstall after do_compile before do_install
###OpenPLi stuff end
###

FILES_${PN} = "/"

S = "${WORKDIR}/tuxbox-common-r11"

TRANSPONDER_LISTS = "satellites.xml terrestrial.xml"

#enigma1 need a cables.xml
TRANSPONDER_LISTS_append_dm7020 = " cables.xml"
TRANSPONDER_LISTS_append_dm500plus = " cables.xml"
TRANSPONDER_LISTS_append_dm600pvr = " cables.xml"

do_install() {
	install -d ${D}/etc/init.d
	install -d ${D}/etc/rcS.d
	install -d ${D}/etc/tuxbox/
	install -d ${D}/usr/share/tuxbox
	install -m 0644 ${S}/scart.conf ${D}/etc/tuxbox/scart.conf

	install -m 0644 ${S}/timezone.xml ${D}/etc/tuxbox/timezone.xml
	ln -sf /etc/tuxbox/timezone.xml ${D}/etc/

	ln -sf /usr/share ${D}/share

	for i in ${TRANSPONDER_LISTS}; do
		install -m 0644 ${WORKDIR}/tuxbox/$i ${D}/etc/tuxbox/$i
		ln -sf /etc/tuxbox/$i ${D}/etc/;
		ln -sf /etc/tuxbox/$i ${D}/usr/share/;
		ln -sf /etc/tuxbox/$i ${D}/usr/share/tuxbox/;
	done;
}
