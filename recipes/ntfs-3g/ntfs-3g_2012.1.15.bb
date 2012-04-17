DESCRIPTION = "The NTFS-3G driver is an open source, freely available NTFS driver for Linux with read and write support."
HOMEPAGE = "http://www.ntfs-3g.org/"
LICENSE = "GPLv2"
DEPENDS = "fuse libgcrypt"
PR = "r5"

SRC_URI = http://tuxera.com/opensource/ntfs-3g_ntfsprogs-${PV}.tgz
S = "${WORKDIR}/ntfs-3g_ntfsprogs-${PV}"

inherit autotools

EXTRA_OEMAKE = "LDCONFIG=echo"
EXTRA_OECONF = "--with-fuse=external --disable-mtab"

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
	/sbin/mkfs.ntfs \
	/usr/sbin/* \
	"

SRC_URI[md5sum] = "341acae00a290cab9b00464db65015cc"
SRC_URI[sha256sum] = "6f1611c5000de7ca99141a9b853cba2c8dbd86c8e36d5efbe7ba918af773fb25"

