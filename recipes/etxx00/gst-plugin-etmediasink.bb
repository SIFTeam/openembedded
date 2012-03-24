DESCRIPTION = "gstreamer dvbmediasink plugin"
SECTION = "multimedia"
PRIORITY = "optional"
DEPENDS = "gstreamer gst-plugins-base libdca"

RREPLACES_${PN} = "gst-plugin-dvbmediasink"
RCONFLICTS_${PN} = "gst-plugin-dvbmediasink"

SRC_URI = "http://www.et-view.com/download/dvbmediasink-1.0.tar.gz"

S = "${WORKDIR}/dvbmediasink"

PV = "0.10.0"
PR = "r0"

inherit autotools pkgconfig

FILES_${PN} = "${libdir}/gstreamer-0.10/*.so*"
FILES_${PN}-dev += "${libdir}/gstreamer-0.10/*.la ${libdir}/gstreamer-0.10/*.a"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug"

PACKAGE_ARCH = "${MACHINE_ARCH}"

EXTRA_OECONF = "--with-wma --with-wmv --with-pcm --with-dtsdownmix"

do_stage() {
	autotools_stage_all
}
