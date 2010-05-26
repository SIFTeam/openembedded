DESCRIPTION = "libebml is a C++ libary to parse EBML files"
HOMEPAGE = "http://www.matroska.org/"
SECTION = "libs"
PRIORITY = "optional"

LICENSE="LGPL-2.1"

SRC_URI = "http://www.bunkus.org/videotools/mkvtoolnix/sources/${PN}-${PV}.tar.bz2"

do_compile() {
	oe_runmake -C make/linux
}

do_install() {
	oe_runmake -C make/linux install includedir="${STAGING_INCDIR}/ebml" libdir="${STAGING_LIBDIR}"
}
