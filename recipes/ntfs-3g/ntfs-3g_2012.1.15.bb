DESCRIPTION = "The NTFS-3G driver is an open source, freely available NTFS driver for Linux with read and write support."
HOMEPAGE = "http://www.ntfs-3g.org/"
LICENSE = "GPLv2"
DEPENDS = "fuse libgcrypt"
RDEPENDS_${PN} = "fuse"
PR = "r2"

SRC_URI = http://tuxera.com/opensource/ntfs-3g_ntfsprogs-${PV}.tgz
S = "${WORKDIR}/ntfs-3g_ntfsprogs-${PV}"

inherit autotools

EXTRA_OECONF = "--disable-ntfsprogs"
EXTRA_OEMAKE = "LDCONFIG=echo"

