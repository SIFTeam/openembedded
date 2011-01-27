DESCRIPTION = "A library for accessing a CDDB server"
HOMEPAGE = "http://libcddb.sourceforge.net"
SECTION = "libs"
PRIORITY = "optional"
LICENSE = "LGPL-2"
MAINTAINER = "Andreas Frisch <andreas.frisch@multimedia-labs.de>"

PR = "r0"

SRC_URI = " \
	http://downloads.sourceforge.net/${PN}/${PN}-${PV}.tar.bz2 \
	file://${P}-configure-realloc.patch \
	file://${P}-asneeded-nonglibc.patch \
	"

inherit autotools pkgconfig

SRC_URI[md5sum] = "e4a7f9579956c32b7f300eb12e9ea6df"
SRC_URI[sha256sum] = "4e7d3aae339cf5037d972cd5f3ab23a3b643f6b05e4125430da4f22682770bf9"
