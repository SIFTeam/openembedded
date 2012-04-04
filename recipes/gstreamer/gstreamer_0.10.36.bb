require gstreamer.inc
SRC_URI = "http://gstreamer.freedesktop.org/src/gstreamer/gstreamer-${PV}.tar.bz2;name=archive"

PR="r0"

RREPLACES_gstreamer = "gst-plugin-queue2 gst-plugin-selector"
RCONFLICTS_gstreamer = "gst-plugin-queue2 gst-plugin-selector"

EXTRA_OECONF += "ac_cv_func_register_printf_function=no"

do_configure_prepend() {
	sed -i -e s:docs::g Makefile.am
}
