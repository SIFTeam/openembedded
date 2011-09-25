require gst-plugins.inc

PR = "${INC_PR}.2"

inherit gconf

RREPLACES_gst-plugin-audioparsers = "gst-plugin-audioparsersbad"
RCONFLICTS_gst-plugin-audioparsers = "gst-plugin-audioparsersbad"
RREPLACES_gst-plugin-isomp4 = "gst-plugin-qtdemux"
RCONFLICTS_gst-plugin-isomp4 = "gst-plugin-qtdemux"

DEPENDS += "hal pulseaudio speex libsoup-2.4 flac gst-plugins-base openssl popt esound libv4l"
DEPENDS_openpli += "libsoup-2.4 flac gst-plugins-base"

# disable plugins that depend on GTK or X, and examples and tests
EXTRA_OECONF_openpli += "\
	--disable-examples \
	--disable-cairo \
	--disable-x \
	--disable-esd \
	--disable-gdk_pixbuf \
	--disable-shout2test \
"

PACKAGES =+ "gst-plugin-gconfelements"
FILES_gst-plugin-gconfelements += "${sysconfdir}/gconf"

do_configure_prepend() {
	    sed -i -e s:docs::g Makefile.am
}

PACKAGES_DYNAMIC = "\
gst-plugin-alaw.* \
gst-plugin-alpha.* \
gst-plugin-alphacolor.* \
gst-plugin-annodex.* \
gst-plugin-apetag.* \
gst-plugin-audiofx.* \
gst-plugin-audioparsers.* \
gst-plugin-auparse.* \
gst-plugin-autodetect.* \
gst-plugin-avi.* \
gst-plugin-cairo.* \
gst-plugin-cutter.* \
gst-plugin-debug.* \
gst-plugin-deinterlace.* \
gst-plugin-efence.* \
gst-plugin-effectv.* \
gst-plugin-equalizer.* \
gst-plugin-esd.* \
gst-plugin-flac.* \
gst-plugin-flv.* \
gst-plugin-flxdec.* \
gst-plugin-gconfelements.* \
gst-plugin-gdkpixbuf.* \
gst-plugin-goom.* \
gst-plugin-goom2k1.* \
gst-plugin-halelements.* \
gst-plugin-icydemux.* \
gst-plugin-id3demux.* \
gst-plugin-imagefreeze.* \
gst-plugin-interleave.* \
gst-plugin-isomp4.* \
gst-plugin-jack.* \
gst-plugin-jpeg.* \
gst-plugin-level.* \
gst-plugin-matroska.* \
gst-plugin-mulaw.* \
gst-plugin-multifile.* \
gst-plugin-multipart.* \
gst-plugin-navigationtest.* \
gst-plugin-oss4audio.* \
gst-plugin-ossaudio.* \
gst-plugin-png.* \
gst-plugin-pulse.* \
gst-plugin-qtdemux.* \
gst-plugin-replaygain.* \
gst-plugin-rtp.* \
gst-plugin-rtpmanager.* \
gst-plugin-rtsp.* \
gst-plugin-shapewipe.* \
gst-plugin-smpte.* \
gst-plugin-souphttpsrc.* \
gst-plugin-spectrum.* \
gst-plugin-speex.* \
gst-plugin-udp.* \
gst-plugin-video4linux2.* \
gst-plugin-videobox.* \
gst-plugin-videocrop.* \
gst-plugin-videofilter.* \
gst-plugin-videomixer.* \
gst-plugin-wavenc.* \
gst-plugin-wavpack.* \
gst-plugin-wavparse.* \
gst-plugin-ximagesrc.* \
gst-plugin-y4menc.* \
"
