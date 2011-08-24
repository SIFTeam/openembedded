DESCRIPTION = "mounts a DVD using libdvdread"
HOMEPAGE = "http://www.jspenguin.org/software/dvdfs/"
DEPENDS = "fuse libdvdread"
PR = "r1"

SRC_URI = "http://www.jspenguin.org/software/${PN}/${PN}-${PV}.tar.gz \
	file://crosscompile.patch \
	file://defaultdevicesr0.patch \
	"


do_install() {
	install -d ${D}/usr/bin
	install -m 755 ${S}/${PN} ${D}/usr/bin/${PN}
}

