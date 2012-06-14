PR = "r0"

# DEPENDS = "fuse"
# RDEPENDS_${PN} = "fuse"

SCONS_FIX_ENV = "1"
inherit scons

SRC_URI = "http://exfat.googlecode.com/files/${PN}-${PV}.tar.gz"

PACKAGES =+ "exfat-mkfs exfat-label exfat-fsck exfat-dump"

# utils is a meta package that installs them all
RDEPENDS_${PN} = "exfat-mkfs exfat-label exfat-fsck exfat-dump"
FILES_exfat-mkfs = "${sbindir}/mkexfatfs"
FILES_exfat-label = "${sbindir}/exfatlabel"
FILES_exfat-fsck = "${sbindir}/exfatfsck"
FILES_exfat-dump = "${sbindir}/dumpexfat"

do_install() {
	install -d ${D}/${sbindir}
	install -m 0755 ${S}/dump/dumpexfat ${D}/${sbindir}/
	install -m 0755 ${S}/fsck/exfatfsck ${D}/${sbindir}/
	install -m 0755 ${S}/label/exfatlabel ${D}/${sbindir}/
	install -m 0755 ${S}/mkfs/mkexfatfs ${D}/${sbindir}/
}

SRC_URI[md5sum] = "1bd94db19fc556701b16a66f6291b4ca"
SRC_URI[sha256sum] = "4bf3dc727540736b404c266ed0b91f7f0fe45df97fd2e581c58cb8abfc46c18a"
