require gst-plugins.inc

SRC_URI_append_openpli += "\
	file://mpegtsdemux-fix-bd-streamtype-detection.diff \
	file://mpegpsdemux-speedup.diff;striplevel=0 \
	file://mpegtsmux_indexing_alignment.diff \
	"

DEPENDS += "opencv orc-native orc libcdaudio gst-plugins-base openssl directfb libmodplug librsvg"
DEPENDS_openpli += "gst-plugins-base librtmp"

RCONFLICTS_gst-plugin-mpegdemux = "gst-plugin-fluendo-mpegdemux"
RREPLACES_gst-plugin-mpegdemux = "gst-plugin-fluendo-mpegdemux"

PR = "${INC_PR}.4"

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
		--disable-examples \
		--disable-sdltest \
		--disable-curl \
		--disable-rsvg \
"

PACKAGES_DYNAMIC = "\
gst-plugin-adpcmdec.* \
gst-plugin-adpcmenc.* \
gst-plugin-aiff.* \
gst-plugin-apexsink.* \
gst-plugin-asfmux.* \
gst-plugin-autoconvert.* \
gst-plugin-bayer.* \
gst-plugin-bz2.* \
gst-plugin-camerabin.* \
gst-plugin-cdxaparse.* \
gst-plugin-cog.* \
gst-plugin-coloreffects.* \
gst-plugin-colorspace.* \
gst-plugin-dataurisrc.* \
gst-plugin-dccp.* \
gst-plugin-debugutilsbad.* \
gst-plugin-dfbvideosink.* \
gst-plugin-dtmf.* \
gst-plugin-dtsdec.* \
gst-plugin-dvb.* \
gst-plugin-dvbsuboverlay.* \
gst-plugin-dvdspu.* \
gst-plugin-faac.* \
gst-plugin-faad.* \
gst-plugin-fbdevsink.* \
gst-plugin-festival.* \
gst-plugin-freeze.* \
gst-plugin-frei0r.* \
gst-plugin-gaudieffects.* \
gst-plugin-geometrictransform.* \
gst-plugin-gsettingselements.* \
gst-plugin-gsm.* \
gst-plugin-h264parse.* \
gst-plugin-hdvparse.* \
gst-plugin-id3tag.* \
gst-plugin-interlace.* \
gst-plugin-invtelecine.* \
gst-plugin-ivfparse.* \
gst-plugin-jp2k.* \
gst-plugin-jp2kdecimator.* \
gst-plugin-jpegformat.* \
gst-plugin-legacyresample.* \
gst-plugin-liveadder.* \
gst-plugin-mms.* \
gst-plugin-modplug.* \
gst-plugin-mpeg4videoparse.* \
gst-plugin-mpegdemux.* \
gst-plugin-mpegpsmux.* \
gst-plugin-mpegtsmux.* \
gst-plugin-mpegvideoparse.* \
gst-plugin-mve.* \
gst-plugin-mxf.* \
gst-plugin-nsf.* \
gst-plugin-nuvdemux.* \
gst-plugin-opencv.* \
gst-plugin-pcapparse.* \
gst-plugin-pnm.* \
gst-plugin-qtmux.* \
gst-plugin-rawparse.* \
gst-plugin-rfbsrc.* \
gst-plugin-rtmp.* \
gst-plugin-rsvg.* \
gst-plugin-rtpmux.* \
gst-plugin-scaletempoplugin.* \
gst-plugin-schro.* \
gst-plugin-sdl.* \
gst-plugin-sdpelem.* \
gst-plugin-segmentclip.* \
gst-plugin-shm.* \
gst-plugin-siren.* \
gst-plugin-sndfile.* \
gst-plugin-speed.* \
gst-plugin-stereo.* \
gst-plugin-subenc.* \
gst-plugin-tta.* \
gst-plugin-vcdsrc.* \
gst-plugin-videomaxrate.* \
gst-plugin-videomeasure.* \
gst-plugin-videosignal.* \
gst-plugin-vmnc.* \
gst-plugin-vp8.* \
gst-plugin-y4mdec.* \
"
