DESCRIPTION = "Generic USB CCID smart card reader driver"
HOMEPAGE = "http://pcsclite.alioth.debian.org/ccid.html"
LICENSE = "GPL"
PR = "r0"

DEPENDS = "pcsc-lite"
RDEPENDS = "pcsc-lite"

SRC_URI = "https://alioth.debian.org/frs/download.php/3672/ccid-${PV}.tar.bz2"

inherit autotools

EXTRA_OECONF = "--enable-udev  --enable-embedded"

do_install_append () {
	install -d "${D}/etc/udev/rules.d"
	install -m 644 "${S}/src/92_pcscd_ccid.rules" "${D}/etc/udev/rules.d/85-pcscd_ccid.rules"
}

FILES_${PN} += "${libdir}/pcsc/"
FILES_${PN}-dbg += "${libdir}/pcsc/drivers/*/*/*/.debug"
