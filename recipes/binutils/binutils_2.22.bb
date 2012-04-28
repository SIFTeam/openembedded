require binutils.inc

PR = "${INC_PR}.1"
DEPENDS = "flex-native bison-native zlib-native"

SRC_URI = "\
     ${GNU_MIRROR}/binutils/binutils-${PV}.tar.bz2 \
     file://binutils-uclibc-100-uclibc-conf.patch \
     file://binutils-uclibc-300-001_ld_makefile_patch.patch \
     file://binutils-uclibc-300-006_better_file_error.patch \
     file://binutils-uclibc-300-012_check_ldrunpath_length.patch \
     file://binutils-uclibc-gas-needs-libm.patch \
     file://binutils-x86_64_i386_biarch.patch \
     file://libtool-2.4-update.patch \
     file://binutils-2.19.1-ld-sysroot.patch \
     file://libiberty_path_fix.patch \
     file://libtool-rpath-fix.patch \
     file://clone-shadow.patch \
     file://binutils-powerpc-e5500.patch \
     file://binutils-armv5e.patch \
     "

SRC_URI[md5sum] = "ee0f10756c84979622b992a4a61ea3f5"
SRC_URI[sha256sum] = "6c7af8ed1c8cf9b4b9d6e6fe09a3e1d3d479fe63984ba8b9b26bf356b6313ca9"

FILES_${PN}-symlinks += " \
	${bindir}/ld.bfd \
	${bindir}/elfedit \
	"	

FILES_${PN}-dbg += "${prefix}/${TARGET_SYS}/bin/.debug"

EXTRA_OECONF = "--program-prefix=${TARGET_PREFIX} \
                --enable-install-libbfd \
                --enable-shared \
                --disable-werror \
	"


