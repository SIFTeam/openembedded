PR = "r0"

DEPENDS = "fuse"
RDEPENDS_${PN} = "fuse"

SCONS_FIX_ENV = "1"
inherit scons

SRC_URI = "http://exfat.googlecode.com/files/${PN}-${PV}.tar.gz"

do_install() {
	install -d ${D}/${sbindir}
	install -m 0755 ${S}/fuse/mount.exfat-fuse ${D}/${sbindir}/mount.exfat-fuse
}

SRC_URI[md5sum] = "ea8eddcc68ec8881cb8a280086297e39"
SRC_URI[sha256sum] = "7db7035fe25cb46aa4ac553705726b3c0ef1546e60080a59792de9e5b7f5b919"
