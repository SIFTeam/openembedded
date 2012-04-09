DESCRIPTION = "ET LiveStream Importer"
SECTION = "base"
PRIORITY = "optional"

SRC_URI = "git://github.com/et-plugins/et-live-links.git;protocol=git"
S = "${WORKDIR}/git"

inherit gitpkgv

SRCREV_pn-${PN} ?= "${AUTOREV}"
PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
PR = "r0"

RDEPENDS_${PN} = "gst-plugin-asf gst-plugin-mms gst-plugin-rtsp gst-plugin-flv gst-plugin-rtmp gst-plugin-libxt"

FILES_${PN} = "/usr/lib/enigma2/python/Plugins/Extensions/EtLiveStream"

do_install_append() {
	install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/EtLiveStream
	install -m 0644 ${S}/__init__.py ${D}/usr/lib/enigma2/python/Plugins/Extensions/EtLiveStream
	install -m 0644 ${S}/plugin.py ${D}/usr/lib/enigma2/python/Plugins/Extensions/EtLiveStream
}
