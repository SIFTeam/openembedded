DESCRIPTION = "NTFS utilies"
HOMEPAGE = "http://www.tuxera.com/"
DEPENDS = "fuse"
LICENSE = "GPLv2"
PR = "r2"

SRC_URI = "http://tuxera.com/opensource/ntfs-3g_ntfsprogs-${PV}.tgz \
           file://skip-erange-errors.patch"

SRC_URI[md5sum] = "2b39dece8897bc748f4ab4c40ec7699e"
SRC_URI[sha256sum] = "ad36e19706c7303b10aa0a9bf2c2dd0309b91cd0171f1c9eb361d94a85017432"

inherit autotools 

EXTRA_OECONF += "--disable-gnome-vfs"
