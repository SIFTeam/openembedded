# libdreamdvd.bb build file
DESCRIPTION="libdvdnav wrapper for dream multimedia stbs"
LICENSE = "GPL"
DEPENDS = "libdvdnav"
RDEPENDS_${PN} = "libdvdnav"

PR = "r0"
PV = "1.0+cvs${SRCDATE}"

SRCDATE = "20100330"
#no hw scaling support for 7025 yet.. so use old libdreamdvd
SRCDATE_dm7025 = "20090517"

SRC_URI="cvs://anonymous@cvs.schwerkraft.elitedvb.net/cvsroot/libdreamdvd;module=libdreamdvd;method=pserver"

CFLAGS_append = "${@base_contains("MACHINE_FEATURES", "lpcm", " -DHARDWARE_SUPPORT_LPCM" , "", d)}"

S = "${WORKDIR}/libdreamdvd"

inherit autotools pkgconfig

do_stage() {
	oe_runmake install prefix=${STAGING_DIR} \
		bindir=${STAGING_BINDIR} \
		includedir=${STAGING_INCDIR} \
		libdir=${STAGING_LIBDIR} \
		datadir=${STAGING_DATADIR}
}
