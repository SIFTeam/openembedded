DESCRIPTION = "gstreamer dvbmediasink plugin"
SECTION = "multimedia"
PRIORITY = "optional"
DEPENDS = "gstreamer gst-plugins-base libdca"

RREPLACES_${PN} = "gst-plugin-etmediasink"
RCONFLICTS_${PN} = "gst-plugin-etmediasink"

SRC_URI = "git://openpli.git.sourceforge.net/gitroot/openpli/gst-plugin-dvbmediasink;protocol=git"

S = "${WORKDIR}/git"

inherit gitpkgv

SRCREV_pn-${PN} ?= "${AUTOREV}"
PV = "0.10.1+git${SRCPV}"
PKGV = "0.10.1+git${GITPKGV}"
PR = "r0"

inherit autotools pkgconfig

FILES_${PN} = "${libdir}/gstreamer-0.10/*.so*"
FILES_${PN}-dev += "${libdir}/gstreamer-0.10/*.la ${libdir}/gstreamer-0.10/*.a"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug"

PACKAGE_ARCH = "${MACHINE_ARCH}"

EXTRA_OECONF = "${DVBMEDIASINK_CONFIG}"

do_stage() {
	autotools_stage_all
}
