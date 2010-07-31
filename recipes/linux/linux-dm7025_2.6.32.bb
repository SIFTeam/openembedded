PATCHLEVEL = ".16"

require linux-opendreambox.inc

SRCREV = "3b5bab90dc6d57b80ed0bbbdf745e6532207e61f"
PR = "r0"

SRC_URI_append = "\
	file://squashfs-lzma-0001-move-zlib-decompression-wrapper-code-into.patch \
	file://squashfs-lzma-0002-Factor-out-remaining-zlib-dependencies-int.patch \
	file://squashfs-lzma-0003-add-a-decompressor-framework.patch \
	file://squashfs-lzma-0004-add-decompressor-entries-for-lzma-and-lzo.patch \
	file://squashfs-lzma-0005-add-an-extra-parameter-to-the-decompressor.patch \
	file://squashfs-lzma-0006-add-LZMA-compression.patch \
	file://squashfs-lzma-0007-Make-unlzma-available-to-non-initramfs-ini.patch \
	file://unionfs-2.5.3_for_2.6.32-pre.diff"
