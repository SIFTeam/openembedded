DESCRIPTION = "Compatibility for packages that link to libcrypto or libssl 0.9.7"
HOMEPAGE = "http://www.openssl.org/"
LICENSE = "openssl"
SECTION = "libs/network"
PACKAGES = "${PN}"
PACKAGE_ARCH = "all"
RDEPENDS = "libcrypto"
RPROVIDES_${PN} = "libcrypto${PV} libssl${PV}"
PR = "r2"
SRC_URI = ""
S = "${WORKDIR}"

do_configure () {
	true
}

do_compile () {
	true
}

do_install () {
	install -d ${D}/usr/lib
	ln -s libcrypto.so ${D}/usr/lib/libcrypto.so.${PV}
	ln -s libssl.so ${D}/usr/lib/libssl.so.${PV}
}
