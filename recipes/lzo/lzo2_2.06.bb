DESCRIPTION = "Lossless data compression library"
HOMEPAGE = "http://www.oberhumer.com/opensource/lzo/"
LICENSE = "GPLv2+"
SECTION = "libs"
PRIORITY = "optional"

SRC_URI = "http://www.oberhumer.com/opensource/lzo/download/lzo-${PV}.tar.gz"
SRC_URI[md5sum] = "95380bd4081f85ef08c5209f4107e9f8"
SRC_URI[sha256sum] = "ff79e6f836d62d3f86ef6ce893ed65d07e638ef4d3cb952963471b4234d43e73"

S = "${WORKDIR}/lzo-${PV}"

inherit autotools

EXTRA_OECONF = "--enable-shared"

do_configure() {
	gnu-configize --force
	oe_runconf
}

BBCLASSEXTEND = "native"

