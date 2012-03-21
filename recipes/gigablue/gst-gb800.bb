CAMNAME = "gst gigablue fix"
DESCRIPTION = "gst crash workaround"

PV = "0.10"
PR = "R0"

SRC_URI = "file://gst-fix.tar.gz \
		   "

S = "${WORKDIR}"


FILES_${PN} = "/usr/* "

do_install() {
	cp -rp ${S}/usr ${D}/
}

pkg_postinst_append () {
	rm -Rf $D/usr/lib/gstreamer-0.10
	mv -f $D/usr/lib/gstreamer-0.10_ $D/usr/lib/gstreamer-0.10
}

