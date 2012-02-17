require gst-plugins.inc

SRC_URI_append_openpli = " file://dvdsubdec-addproperty-singlebuffer.patch"

PR = "${INC_PR}.1"

DEPENDS += "gst-plugins-base libsidplay"
DEPENDS_openpli += "gst-plugins-base libsidplay opencore-amr"

python() {
	# Don't build, if we are building an ENTERPRISE distro
	enterprise = bb.data.getVar("ENTERPRISE_DISTRO", d, 1)
	if enterprise == "1":
		raise bb.parse.SkipPackage("gst-plugins-ugly will only build if ENTERPRISE_DISTRO != 1")
}

PACKAGES_DYNAMIC = "\
gst-plugin-a52dec.* \
gst-plugin-asf.* \
gst-plugin-cdio.* \
gst-plugin-dvdlpcmdec.* \
gst-plugin-dvdread.* \
gst-plugin-dvdsub.* \
gst-plugin-iec958.* \
gst-plugin-lame.* \
gst-plugin-mad.* \
gst-plugin-mpeg2dec.* \
gst-plugin-mpegaudioparse.* \
gst-plugin-mpegstream.* \
gst-plugin-rmdemux.* \
gst-plugin-sid.* \
gst-plugin-x264.* \
gst-plugin-amrnb.* \
gst-plugin-amrwbdec.* \
"
