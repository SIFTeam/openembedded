require libpng.inc

PR = "${INC_PR}.1"

SRC_URI[libpng.md5sum] = "b43afe39237b69859522455b215f9e85"
SRC_URI[libpng.sha256sum] = "3954ed12794e7e70a4cb9b1d576af8869212a36abde33891b4876315dd36661d"

PACKAGES =+ "${PN}15-dbg ${PN}15 ${PN}15-dev"

FILES_${PN}15-dbg = "${libdir}/.debug/libpng15*"
FILES_${PN}15 = "${libdir}/libpng15.so.*"
FILES_${PN}15-dev = "${libdir}/libpng15.* ${includedir}/libpng15 ${libdir}/pkgconfig/libpng15.pc"
