DESCRIPTION = "Mjpeg tools is a suite of programs which support video capture, editting, playback, and compression to MPEG of MJPEG video."
LICENSE = "GPL"
SECTION = "optional"

SRC_URI = "${SOURCEFORGE_MIRROR}/mjpeg/mjpegtools-${PV}.tar.gz \
	file://mjpegtools-fix-include.patch \
	file://mjpegtools-remove-sdl-dependency.patch \
	file://mjpegtools-v4l-doesnt-mean-x11.patch \
	"

inherit autotools

EXTRA_OECONF = "--without-x"
