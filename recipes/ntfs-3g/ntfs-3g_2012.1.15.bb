DESCRIPTION = "The NTFS-3G driver is an open source, freely available NTFS driver for Linux with read and write support."
HOMEPAGE = "http://www.ntfs-3g.org/"
LICENSE = "GPLv2"
DEPENDS = "fuse libgcrypt"
RDEPENDS_${PN} = "fuse"
PR = "r3"

SRC_URI = http://tuxera.com/opensource/ntfs-3g_ntfsprogs-${PV}.tgz
S = "${WORKDIR}/ntfs-3g_ntfsprogs-${PV}"

inherit autotools

EXTRA_OEMAKE = "LDCONFIG=echo"

PACKAGES =+ "ntfsprogs"
PROVIDES =+ "ntfsprogs"
DESCRIPTION_ntfsprogs = "NTFS utilies"
FILES_ntfsprogs = "\
	/usr/bin/ntfscluster \
	/usr/bin/ntfsfix \
	/usr/bin/ntfsls \
	/usr/bin/ntfscat \
	/usr/bin/ntfscmp \
	/usr/bin/ntfsinfo \
	/usr/sbin/* \
	"
