DESCRIPTION = "Fixup some miscompiled apps by making an extra symlink"
PR = "r1"
LICENSE = "MIT"

PACKAGE_ARCH = "all"
ALLOW_EMPTY_${PN} = "1"

pkg_postinst_${PN}() {
ln -sf libc.so.6 $D/lib/libc.so
}
