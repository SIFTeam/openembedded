DESCRIPTION = "Tuxbox common files"
LICENSE = "GPL"
MAINTAINER = "PLi team"

PN = "tuxbox-common"
PR = "r2"

PV = "1.0+svn${SRCREV}"

# use cables.xml and terrestrial.xml from tuxbox.org
SRC_URI = "cvs://anoncvs@cvs.tuxbox.org/cvs/tuxbox/;module=cdk/root/share/tuxbox;method=ext;rsh=ssh;tag=dreambox;date=${SRCDATE} \
	"

SRCREV_FORMAT = "satsxml"

# everything else from openpli svn
SRC_URI += " ${PLISVNURL}/${PLISVNBRANCH}/cdk/cdk/root;module=share;proto=${PLISVNPROTO};name=satsxml \
	${PLISVNURL}/${PLISVNBRANCH}/cdk/cdk/root;module=etc;proto=${PLISVNPROTO}"

FILES_${PN} = "/"

S = "${WORKDIR}"

TRANSPONDER_LISTS = "satellites.xml terrestrial.xml"

#enigma1 need a cables.xml
TRANSPONDER_LISTS_append_dm7020 = " cables.xml"
TRANSPONDER_LISTS_append_dm500plus = " cables.xml"
TRANSPONDER_LISTS_append_dm600pvr = " cables.xml"

do_install() {
	install -d ${D}/etc/tuxbox/
	install -d ${D}/usr/share/tuxbox
	install -m 0644 ${S}/share/tuxbox/scart.conf ${D}/etc/tuxbox/scart.conf

	install -m 0644 ${S}/etc/timezone.xml ${D}/etc/tuxbox/timezone.xml
	ln -sf /etc/tuxbox/timezone.xml ${D}/etc/

	ln -sf /usr/share ${D}/share

	install -m 0644 ${S}/share/tuxbox/satellites.xml ${S}/tuxbox

	for i in ${TRANSPONDER_LISTS}; do
		install -m 0644 ${S}/tuxbox/$i ${D}/etc/tuxbox/$i
		ln -sf /etc/tuxbox/$i ${D}/etc/;
		ln -sf /etc/tuxbox/$i ${D}/usr/share/;
		ln -sf /etc/tuxbox/$i ${D}/usr/share/tuxbox/;
	done;
}
