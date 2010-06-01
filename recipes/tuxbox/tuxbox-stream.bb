DEPENDS = "dreambox-dvbincludes tuxbox-libs"
RDEPENDS_${PN} = "libtuxbox-mpegtools0"
DESCRIPTION = "tuxbox net streaming tools"
MAINTAINER = "Felix Domke <tmbinc@elitdvb.net>"

SRC_URI = "${PLISVNURL}/${PLISVNBRANCH}/cdk/apps/dvb/tools;module=stream;proto=${PLISVNPROTO} \
						file://acinclude.m4 \
						file://enable_transform.diff \
						file://add_configfiles.diff"

S = "${WORKDIR}/stream"
PV = "1.0+svn${SRCREV}"
PR = "r3"

inherit autotools pkgconfig

bindir = "/usr/bin"
sbindir = "/usr/sbin"

EXTRA_OECONF = "--with-target=native --with-boxtype=${MACHINE}"

CFLAGS_append = " -DHAVE_DREAMBOX_HARDWARE"

do_configure_prepend() {
	install ${WORKDIR}/acinclude.m4 ${S}/acinclude.m4
}

do_install_append() {
	ln -s streampes ${D}/usr/sbin/streames
}
