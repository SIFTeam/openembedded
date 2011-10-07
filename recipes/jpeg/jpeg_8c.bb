DESCRIPTION = "libjpeg is a library for handling the JPEG (JFIF) image format."
LICENSE = "jpeg"
SECTION = "libs"
PRIORITY = "required"
PR = "r1"

# TODO: really needed?
RPROVIDES_${PN} = "jpeg"

SRC_URI = "\
    http://www.ijg.org/files/jpegsrc.v${PV}.tar.gz \
    "
S = "${WORKDIR}/jpeg-${PV}"

inherit autotools

EXTRA_OECONF = "--enable-static --enable-shared"
CFLAGS_append = " -D_REENTRANT"

PACKAGES =+ "${PN}-tools "
FILES_${PN}-tools = "${bindir}/*"

BBCLASSEXTEND = "native"

SRC_URI[md5sum] = "a2c10c04f396a9ce72894beb18b4e1f9"
SRC_URI[sha256sum] = "edfc0b3e004b2fe58ffeeec89f96e3a3c28972c46725ec127d01edf8a1cc7c9a"
