DESCRIPTION = "libnl is a library for applications dealing with netlink sockets"
SECTION = "libs/network"
LICENSE = "LGPL"
HOMEPAGE = "http://people.suug.ch/~tgr/libnl"
PV = "1.0+gitr${SRCREV}"
PR = "r0"

inherit autotools pkgconfig

#CFLAGS += '-DVLAN_FLAG_REORDER_HDR=1'

SRC_URI = "git://git.kernel.org/pub/scm/libs/netlink/libnl.git;protocol=git"
S = "${WORKDIR}/git"

do_stage () {
	autotools_stage_all prefix=${prefix}
}

PACKAGES =+ "${PN}-route ${PN}-nf ${PN}-genl"
FILES_${PN}-route = "${libdir}/libnl-route.so.*"
FILES_${PN}-nf    = "${libdir}/libnl-nfd.so.*"
FILES_${PN}-genl  = "${libdir}/libnl-genl.so.*"
