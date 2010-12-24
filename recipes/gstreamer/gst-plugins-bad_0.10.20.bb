require gst-plugins.inc

PR = "${INC_PR}.1"

do_configure_prepend() {
    sed -i -e s:docs::g Makefile.am
}

DEPENDS += "gst-plugins-base"
DEPENDS_openpli += "gst-plugins-base"

EXTRA_OECONF_openpli += "--disable-apexsink --disable-dvdnav --disable-cdaudio --disable-mpeg2enc --disable-mplex --disable-librfb"

SRC_URI_append_openpli += "\
	file://mpegpsdemux-speedup.diff;striplevel=0 \
	file://mpegtsdemux-fix-bd-streamtype-detection.diff \
	file://aacparse-fix-rank.diff \
	file://ac3parse-fix-rank.diff \
	file://ac3parse-just-warn-unknown-ac3-bsid.diff \
	"

RCONFLICTS_gst-plugin-mpegdemux = "gst-plugin-fluendo-mpegdemux"
RREPLACES_gst-plugin-mpegdemux = "gst-plugin-fluendo-mpegdemux"
