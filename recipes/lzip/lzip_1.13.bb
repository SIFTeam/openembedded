DESCRIPTION = "Lzip is a lossless data compressor based on the LZMA algorithm"
HOMEPAGE = "http://lzip.nongnu.org/lzip.html"
LICENSE = "GPLv3+"
PR = "1"

SRC_URI = "${SAVANNAH_MIRROR}/releases/lzip/lzip-${PV}.tar.gz"
SRC_URI[md5sum] = "2f401e995c36cca05bd1805aa9c28231"
SRC_URI[sha256sum] = "c73d36c0a926b71d484eacc192262a1d209674e3f903016f2c74a2bcbc5c28ac"

CONFIGUREOPTS = "\
    '--srcdir=${S}' \
    '--prefix=${prefix}' \
    '--exec-prefix=${exec_prefix}' \
    '--bindir=${bindir}' \
    '--datadir=${datadir}' \
    '--infodir=${infodir}' \
    '--sysconfdir=${sysconfdir}' \
    'CXX=${CXX}' \
    'CPPFLAGS=${CPPFLAGS}' \
    'CXXFLAGS=${CXXFLAGS}' \
    'LDFLAGS=${LDFLAGS}' \
"
EXTRA_OEMAKE = ""

B = "${S}/obj"
do_configure () {
    ${S}/configure ${CONFIGUREOPTS}
}

do_install () {
    oe_runmake 'DESTDIR=${D}' install
}

BBCLASSEXTEND += "native nativesdk"
