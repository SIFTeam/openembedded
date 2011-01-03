DESCRIPTION = "An HTTP library implementation in C"
SECTION = "x11/gnome/libs"
LICENSE = "GPL"
DEPENDS = "libproxy glib-2.0 gnutls libxml2 sqlite3 gnome-keyring"
DEPENDS_openpli = " glib-2.0 gnutls libxml2 sqlite3"

#inherit gnome
inherit autotools
AUTOTOOLS_STAGE_PKGCONFIG = "1"

SRC_URI = "${GNOME_MIRROR}/libsoup/${@gnome_verdir("${PV}")}/libsoup-${PV}.tar.bz2;name=libsoup"
SRC_URI_openpli = "${GNOME_MIRROR}/libsoup/${@'.'.join((bb.data.getVar('PV', d, 1)).split('.')[:2])}/libsoup-${PV}.tar.bz2"
SRC_URI[libsoup.md5sum] = "900390c0ead254fbb23f3f0b84fd18bb"
SRC_URI[libsoup.sha256sum] = "626c88f6b87463cb092733d2bcd5672ca69529a766cc6c5cc817f34b49c821b1"

S = "${WORKDIR}/libsoup-${PV}"

EXTRA_OECONF_openpli += "--without-gnome"

PACKAGES =+ "libsoup-gnome"
FILES_libsoup-gnome = "${libdir}/libsoup-gnome*.so.*"
FILES_${PN} = "${libdir}/libsoup-2*.so.*"
FILES_${PN}-dev = "${includedir}/ ${libdir}/"
FILES_${PN}-doc = "${datadir}/"
