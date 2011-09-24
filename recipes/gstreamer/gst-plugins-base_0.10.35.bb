require gst-plugins.inc

SRC_URI += " \
            file://ivorbis-thumb.patch \
"
SRC_URI_append_openpli = "\
	file://samihack.patch \
	file://d3a44541749f413343d5717c464083cef22a74f1.patch \
	file://1e0ddb12aa1c2b13c4bc4a13712ebd7f06a6346e.patch \
"

PR = "${INC_PR}.1"

PROVIDES += "gst-plugins"

# gst-plugins-base only builds the alsa plugin
# if alsa has been built and is present.  You will
# not get an error if this is not present, just
# a missing alsa plugin
DEPENDS += "udev cdparanoia pango libtheora alsa-lib libsm virtual/libx11 freetype gnome-vfs libxv libxrandr gtk+"
DEPENDS_openpli += "alsa-lib"

def get_gstreamer_fpu_setting(bb, d):
    if bb.data.getVar('TARGET_FPU', d, 1) in [ 'soft' ]:
        return "--with-audioresample-format=int"
    return ""

# Needs a udev that enabled gudev, which isn't the default
EXTRA_OECONF += "--with-gudev --disable-gnome_vfs ${@get_gstreamer_fpu_setting(bb, d)}"
EXTRA_OECONF_openpli += "--disable-theora -disable-gnome_vfs --disable-pango --with-audioresample-format=int \
			--disable-examples --disable-oggtest --disable-vorbistest --disable-freetypetest --disable-x"

do_configure_prepend() {
	sed -i -e s:QtGui:NoQtGui:g ${S}/configure.ac
}

PACKAGES_DYNAMIC = "\
gst-plugin-adder.* \
gst-plugin-alsa.* \
gst-plugin-app.* \
gst-plugin-audioconvert.* \
gst-plugin-audiorate.* \
gst-plugin-audioresample.* \
gst-plugin-audiotestsrc.* \
gst-plugin-cdparanoia.* \
gst-plugin-decodebin2.* \
gst-plugin-decodebin.* \
gst-plugin-encodebin.* \
gst-plugin-ffmpegcolorspace.* \
gst-plugin-gdp.* \
gst-plugin-gio.* \
gst-plugin-ivorbisdec.* \
gst-plugin-libvisual.* \
gst-plugin-ogg.* \
gst-plugin-pango.* \
gst-plugin-playbin.* \
gst-plugin-subparse.* \
gst-plugin-tcp.* \
gst-plugin-theora.* \
gst-plugin-typefindfunctions.* \
gst-plugin-video4linux.* \
gst-plugin-videorate.* \
gst-plugin-videoscale.* \
gst-plugin-videotestsrc.* \
gst-plugin-volume.* \
gst-plugin-vorbis.* \
gst-plugin-ximagesink.* \
gst-plugin-xvimagesink.* \
"
