require libpng.inc

PR = "${INC_PR}.1"

SRC_URI[libpng.md5sum] = "3270bf2990c3174ae939388398de751e"
SRC_URI[libpng.sha256sum] = "3b7c6c73c6e3a2468a43a8e09fa2c01d13283eb29d30dd83e32b361554b10c23"

PACKAGES =+ "${PN}15-dbg ${PN}15 ${PN}15-dev"

FILES_${PN}15-dbg = "${libdir}/.debug/libpng15*"
FILES_${PN}15 = "${libdir}/libpng15.so.*"
FILES_${PN}15-dev = "${libdir}/libpng15.* ${includedir}/libpng15 ${libdir}/pkgconfig/libpng15.pc"
