DESCRIPTION = "Firmware for zd1211 based USB wifi adaptors"
LICENSE = "unknown"

PR = "r0"

SRC_URI = "${SOURCEFORGE_MIRROR}/zd1211/zd1211-firmware-${PV}.tar.bz2"

S = "${WORKDIR}/zd1211-firmware"

do_install() {
	install -d ${D}/${base_libdir}/firmware/zd1211/
	install -m 0644 ${S}/zd1211_ub ${D}/${base_libdir}/firmware/zd1211/
	install -m 0644 ${S}/zd1211_uph ${D}/${base_libdir}/firmware/zd1211/
	install -m 0644 ${S}/zd1211_uphm ${D}/${base_libdir}/firmware/zd1211/
	install -m 0644 ${S}/zd1211_ur ${D}/${base_libdir}/firmware/zd1211/
	install -m 0644 ${S}/zd1211_uphr ${D}/${base_libdir}/firmware/zd1211/
	install -m 0644 ${S}/zd1211b_ub ${D}/${base_libdir}/firmware/zd1211/
	install -m 0644 ${S}/zd1211b_uph ${D}/${base_libdir}/firmware/zd1211/
	install -m 0644 ${S}/zd1211b_uphm ${D}/${base_libdir}/firmware/zd1211/
	install -m 0644 ${S}/zd1211b_ur ${D}/${base_libdir}/firmware/zd1211/
	install -m 0644 ${S}/zd1211_uphr ${D}/${base_libdir}/firmware/zd1211/
}

FILES_${PN} = "${base_libdir}/firmware/zd1211/"
PACKAGE_ARCH = "all"

SRC_URI[md5sum] = "19f28781d76569af8551c9d11294c870"
SRC_URI[sha256sum] = "866308f6f59f7075f075d4959dff2ede47735c751251fecd1496df1ba4d338e1"
