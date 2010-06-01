DESCRIPTION = "neon is an HTTP and WebDAV client library, with a C interface."
SECTION = "libs"
LICENSE = "LGPL"
DEPENDS = "zlib libxml2 expat time openssl"
PR = "r0"

SRC_URI = "http://www.webdav.org/${PN}/${P}.tar.gz"

inherit autotools binconfig lib_package pkgconfig

EXTRA_OECONF = "--with-ssl=openssl --with-libxml2 --with-expat --enable-shared"

do_stage () {
	autotools_stage_all
}

SRC_URI[md5sum] = "47599a328862ce64ac3c52726d6daa12"
SRC_URI[sha256sum] = "90dee51b4c70bc50ce2fa106ca945349b81cd86c90aa9d4dbff73abb284fcdc2"
