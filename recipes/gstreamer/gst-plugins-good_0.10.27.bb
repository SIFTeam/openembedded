require gst-plugins.inc

PR = "${INC_PR}.0"

DEPENDS += "gst-plugins-base"
DEPENDS_openpli += "libsoup-2.4 flac gst-plugins-base"

do_configure_prepend() {
	sed -i -e s:docs::g Makefile.am
}
