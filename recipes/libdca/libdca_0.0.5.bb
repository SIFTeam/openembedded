DESCRIPTION = "Library for decoding dts audio to wav"
LICENSE = "GPL"
SECTION = "libs"
PRIORITY = "optional"
PR = "r2"

inherit autotools pkgconfig

SRC_URI = "http://download.videolan.org/pub/videolan/libdca/${PV}/libdca-${PV}.tar.bz2 \
	file://r83-mark-tables-as-static-constants.patch \
	file://r84-normalisation-factor-sqrt2+output-bias.patch \
	file://r87-sanity-check-for-subframes-and-prim_channels.patch \
	file://r88-fix-random-crashes-caused-by-invalid-32-bit-shifts.patch \
	file://r89-avoid-crashing-with-invalid-frames.patch \
"

S = "${WORKDIR}/${PN}-${PV}"

EXTRA_OECONF = " --with-gnu-ld"

PACKAGES =+ "dcadec dcadec-doc"

FILES_${PN} = " ${libdir}/libdca.s* "
FILES_${PN}-dev = " ${includedir}/*.h ${libdir}/libdca.* ${libdir}/pkgconfig/* "
FILES_${PN}-dbg += " ${libdir}/.debug/*"
FILES_dcadec = " ${bindir}/* "
FILES_dcadec-dbg += " ${bindir}/.debug/* "
FILES_dcadec-doc = " ${mandir}/man1/* "


do_stage() {
	autotools_stage_all
}

# Quick optimization: double -> float (about 25% faster)
do_patchfloat() {
	sed -i~ -e 's/double/sample_t/g' ${S}/libdca/*.h
	sed -i~ -e 's/double/sample_t/g' ${S}/libdca/*.c
}

addtask patchfloat after do_patch before do_configure
