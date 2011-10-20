DESCRIPTION = "The NTFS-3G driver is an open source, freely available NTFS driver for Linux with read and write support."
HOMEPAGE = "http://www.ntfs-3g.org/"
LICENSE = "GPLv2"
DEPENDS = "fuse"
RDEPENDS_${PN} = "fuse"
PR = "r0"

SRC_URI = http://tuxera.com/opensource/ntfs-3g-${PV}.tgz

inherit autotools

EXTRA_OEMAKE = "LDCONFIG=echo"

SRC_URI[md5sum] = "15a5cf5752012269fa168c24191f00e2"
SRC_URI[sha256sum] = "d8a101002cd4cc493f5da5efd02f1860c32f46ed14a99e1356c6d376d39a0a6b"
