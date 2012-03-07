DESCRIPTION = "Mediacenter for azbox"

inherit gitpkgv

SRCDATE = "20120306"
PV = "${SRCDATE}"
PR = "r0"

SRC_URI = "http://openee.sifteam.eu/azbox/mediacenter-${MACHINE}-${SRCDATE}.zip"

FILES_${PN} = "/usr/"

S = "${WORKDIR}/mediacenter"

do_compile() {
}

do_install() {
	install -d ${D}/usr/bin
	install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter
	install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/RTiSubtitleConverter
	install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/RTiSubtitler
	cp -rp ${S}/plugin/* ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter
	cp -rp ${S}/RTiSubtitleConverter/* ${D}/usr/lib/enigma2/python/Plugins/Extensions/RTiSubtitleConverter
	cp -rp ${S}/RTiSubtitler/* ${D}/usr/lib/enigma2/python/Plugins/Extensions/RTiSubtitler
	cp -rp ${S}/bin/* ${D}/usr/bin
	chmod 755 ${D}/usr/bin/*
}
