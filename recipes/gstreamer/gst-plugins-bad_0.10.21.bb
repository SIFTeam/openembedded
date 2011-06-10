require gst-plugins.inc

SRC_URI_append_openpli += "\
	file://mpegpsdemux-speedup.diff;striplevel=0 \
	file://mpegtsdemux-fix-bd-streamtype-detection.diff \
	file://audioparsers-change-rank.diff \
	"

SRC_URI[archive.md5sum] = "f501336ab1d18d2565f47c36ce653a82"
SRC_URI[archive.sha256sum] = "422badacbda37ac33cb446c6751dabcd0b223c308dbb01024a34ded682fa47e3"

DEPENDS += "opencv orc-native orc libcdaudio gst-plugins-base openssl directfb libmodplug librsvg"
DEPENDS_openpli += "gst-plugins-base"

RCONFLICTS_gst-plugin-mpegdemux = "gst-plugin-fluendo-mpegdemux"
RREPLACES_gst-plugin-mpegdemux = "gst-plugin-fluendo-mpegdemux"

PR = "${INC_PR}.0"

do_configure_prepend() {
    sed -i -e s:docs::g Makefile.am
}

# We don't have vdpau headers in OE and it creates crosscompile badness.
# Also, mpeg2enc and mplex from mjpegtools don't build, because of AC_TRY_RUN.
EXTRA_OECONF += " \
        --disable-mpeg2enc \
        --disable-mplex \
        --disable-vdpau \
"
EXTRA_OECONF_openpli += " \
		--disable-apexsink \
		--disable-dvdnav \
		--disable-cdaudio \
		--disable-mpeg2enc \
		--disable-mplex \
		--disable-librfb \
		--disable-vdpau \
"
