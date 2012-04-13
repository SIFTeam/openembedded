require libpng.inc

PR = "${INC_PR}.1"

SRC_URI[libpng.md5sum] = "f4395eaf426bdd870446c246df307aae"
SRC_URI[libpng.sha256sum] = "4d044852d3fbe5c0fad51f80f3f9bd519a6d5e1997079b165aa2292bd706cd5d"

PACKAGES =+ "${PN}15-dbg ${PN}15 ${PN}15-dev"

FILES_${PN}15-dbg = "${libdir}/.debug/libpng15*"
FILES_${PN}15 = "${libdir}/libpng15.so.*"
FILES_${PN}15-dev = "${libdir}/libpng15.* ${includedir}/libpng15 ${libdir}/pkgconfig/libpng15.pc"
