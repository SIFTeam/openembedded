PR = "r0"

DEPENDS = "fuse"
RDEPENDS_${PN} = "fuse"

SCONS_FIX_ENV = "1"
inherit scons

SRC_URI = "http://exfat.googlecode.com/files/fuse-exfat-0.9.5.tar.gz"

do_install() {
	install -d ${D}/${sbindir}
	install -m 0755 ${S}/fuse/mount.exfat-fuse ${D}/${sbindir}/mount.exfat-fuse
}
