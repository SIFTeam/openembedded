DESCRIPTION = "libmatroska is a C++ libary to parse Matroska files (.mkv and .mka)"
HOMEPAGE = "http://www.matroska.org/"
SECTION = "libs"
PRIORITY = "optional"

LICENSE = "GPL-2"

DEPENDS = "libebml"

PR = "r0"

SRC_URI = "http://www.bunkus.org/videotools/mkvtoolnix/sources/${PN}-${PV}.tar.bz2"

do_compile() {
	oe_runmake -C make/linux
}

do_install() {
	oe_runmake -C make/linux install includedir="${STAGING_INCDIR}/matroska" libdir="${STAGING_LIBDIR}"
}

