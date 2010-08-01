PATCHLEVEL = ".16"

require linux-opendreambox.inc

SRCREV = "3b5bab90dc6d57b80ed0bbbdf745e6532207e61f"
PR = "r0"

SRC_URI_append = "\
	file://squashfs-lzma-0001-move-zlib-decompression-wrapper-code-into.patch;patch=1 \
	file://squashfs-lzma-0002-Factor-out-remaining-zlib-dependencies-int.patch;patch=1 \
	file://squashfs-lzma-0003-add-a-decompressor-framework.patch;patch=1 \
	file://squashfs-lzma-0004-add-decompressor-entries-for-lzma-and-lzo.patch;patch=1 \
	file://squashfs-lzma-0005-add-an-extra-parameter-to-the-decompressor.patch;patch=1 \
	file://squashfs-lzma-0006-add-LZMA-compression.patch;patch=1 \
	file://squashfs-lzma-0007-Make-unlzma-available-to-non-initramfs-ini.patch;patch=1 \
	http://download.filesystems.org/unionfs/unionfs-2.x/unionfs-2.5.4_for_2.6.32.9.diff.gz;patch=1 \
	file://squashfs-lzma-as-module-buildfix.patch;patch=1"
