DESCRIPTION = "A tool to apply the data-recovery capability concepts of RAID-like systems \
to the posting & recovery of multi-part archives on Usenet."
LICENSE = "GPL"
HOMEPAGE = "http://parchive.sourceforge.net/"
DEPENDS = "libsigc++-2.0"
PR = "r2"

SRC_URI = "${SOURCEFORGE_MIRROR}/parchive/${PN}/${PN}-${PV}.tar.gz \
	file://par2cmdline-0.4-gcc4.patch \
	file://par2-softlink.patch \
	"

inherit autotools pkgconfig

SRC_URI[md5sum] = "1551b63e57e3c232254dc62073b723a9"
SRC_URI[sha256sum] = "9e32b7dbcf7bca8249f98824757d4868714156fe2276516504cd26f736e9f677"

