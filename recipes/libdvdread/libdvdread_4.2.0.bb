DESCRIPTION = "libdvdread provides a simple foundation for reading DVD video disks. \
It provides the functionality that is required to access many DVDs. \
It parses IFO files, reads NAV-blocks, and performs CSS authentication and descrambling."
HOMEPAGE = "http://dvdnav.mplayerhq.hu"
LICENSE = "GPL"
DEPENDS = "libdvdcss"
RRECOMMENDS_${PN} = "libdvdcss"
SECTION = "libs/multimedia"

PR = "r0"

SRC_URI = "http://dvdnav.mplayerhq.hu/releases/libdvdread-${PV}.tar.bz2"

inherit autotools pkgconfig

SRC_URI[md5sum] = "ab7a19d3ab1a437ae754ef477d6231a4"
SRC_URI[sha256sum] = "0bea15da842a4b04a482b009d72dcc6d9c9524ccc1bf67e5748319ec5ada8097"

EXTRA_OEMAKE = "DESTDIR=${D}"

do_stage() {
	autotools_stage_all
	cat ${S}/misc/dvdread-config | sed -e "s,-I/usr/include,-I${STAGING_INCDIR}," \
				     | sed -e "s,-L/usr/lib,-L${STAGING_LIBDIR}," \
		> ${STAGING_BINDIR_CROSS}/dvdread-config
	chmod a+rx ${STAGING_BINDIR_CROSS}/dvdread-config
}

