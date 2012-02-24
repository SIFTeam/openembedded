DESCRIPTION = "rtiled for azbox"

SRCDATE = "20120222"
PV = "${SRCDATE}"
PR = "r0"

SRC_URI = "http://openee.sifteam.eu/azbox/rtiled-${MACHINE}-${SRCDATE}.zip"

FILES_${PN} = "/usr/"

S = "${WORKDIR}/RtiLED"

do_compile() {
}

do_install() {
	install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/RtiLED
	cp -rp ${S}/* ${D}/usr/lib/enigma2/python/Plugins/Extensions/RtiLED
}
