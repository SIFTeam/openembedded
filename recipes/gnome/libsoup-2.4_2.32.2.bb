DESCRIPTION = "An HTTP library implementation in C"
SECTION = "x11/gnome/libs"
LICENSE = "GPL"
DEPENDS = "libproxy glib-2.0 gnutls libxml2 sqlite3 gnome-keyring"
DEPENDS_openpli = "glib-2.0 gnutls libxml2 sqlite3"

#inherit gnome
inherit autotools
AUTOTOOLS_STAGE_PKGCONFIG = "1"

SRC_URI = "${GNOME_MIRROR}/libsoup/${@gnome_verdir("${PV}")}/libsoup-${PV}.tar.bz2;name=libsoup"
SRC_URI_openpli = "${GNOME_MIRROR}/libsoup/${@'.'.join((bb.data.getVar('PV', d, 1)).split('.')[:2])}/libsoup-${PV}.tar.bz2"
SRC_URI[libsoup.md5sum] = "03f37350a2a31046ebabb8470e75abcc"
SRC_URI[libsoup.sha256sum] = "96e6973c8b7459523c0f44e7aec69528ff2fbd388e8ddc415f91bcc42f50777f"

S = "${WORKDIR}/libsoup-${PV}"

EXTRA_OECONF_openpli += "--without-gnome"

PACKAGES =+ "libsoup-gnome"
FILES_libsoup-gnome = "${libdir}/libsoup-gnome*.so.*"
FILES_${PN} = "${libdir}/libsoup-2*.so.*"
FILES_${PN}-dev = "${includedir}/ ${libdir}/"
FILES_${PN}-doc = "${datadir}/"
