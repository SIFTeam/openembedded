require gst-plugins.inc

SRC_URI_append_openpli = " file://dvdsubdec-addproperty-singlebuffer.patch"

PR = "${INC_PR}.0"

DEPENDS += "gst-plugins-base libsidplay"
DEPENDS_openpli += "gst-plugins-base libsidplay"

python() {
	# Don't build, if we are building an ENTERPRISE distro
	enterprise = bb.data.getVar("ENTERPRISE_DISTRO", d, 1)
	if enterprise == "1":
		raise bb.parse.SkipPackage("gst-plugins-ugly will only build if ENTERPRISE_DISTRO != 1")
}

