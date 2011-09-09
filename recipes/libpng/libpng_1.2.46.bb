require libpng.inc

PR = "${INC_PR}.0"

SRC_URI += "file://makefile_fix.patch"

SRC_URI[libpng.md5sum] = "e8b43dc78ef95b3949af7f961d76874b"
SRC_URI[libpng.sha256sum] = "a5e796e1802b2e221498bda09ff9850bc7ec9068b6788948cc2c42af213914d8"


PACKAGES =+ "${PN}12-dbg ${PN}12 ${PN}12-dev"

FILES_${PN}12-dbg = "${libdir}/.debug/libpng12*"
FILES_${PN}12 = "${libdir}/libpng12.so.*"
FILES_${PN}12-dev = "${libdir}/libpng12.* ${includedir}/libpng12 ${libdir}/pkgconfig/libpng12.pc"

