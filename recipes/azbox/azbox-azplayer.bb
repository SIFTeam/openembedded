DESCRIPTION = "Azbox AZplayer app plugin"
RDEPENDS = "enigma2"


PR = "r3"

SRC_URI = "file://bin \
	   file://lib \
	   file://img \
	   file://plugin \
	  "

do_install_azboxhd() {
	install -d ${D}/usr/bin/
	install -m 0755 ${WORKDIR}/bin/rmfp_player-ForHD ${D}/usr/bin/rmfp_player

	install -d ${D}/usr/lib/
	install -m 0755 ${WORKDIR}/lib/lib* ${D}/usr/lib/

	install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/AZPlay/
	install -m 0755 ${WORKDIR}/plugin/*.pyo ${D}/usr/lib/enigma2/python/Plugins/Extensions/AZPlay/

        install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/AZPlay/img/
        install -m 0755 ${WORKDIR}/img/*.png ${D}/usr/lib/enigma2/python/Plugins/Extensions/AZPlay/img/
}

do_install_azboxme() {
	install -d ${D}/usr/bin/
	install -m 0755 ${WORKDIR}/bin/rmfp_player ${D}/usr/bin/

	install -d ${D}/usr/lib/
	install -m 0755 ${WORKDIR}/lib/lib* ${D}/usr/lib/

	install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/AZPlay/
	install -m 0755 ${WORKDIR}/plugin/*.pyo ${D}/usr/lib/enigma2/python/Plugins/Extensions/AZPlay/

        install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/AZPlay/img/
        install -m 0755 ${WORKDIR}/img/*.png ${D}/usr/lib/enigma2/python/Plugins/Extensions/AZPlay/img/
}

do_install_azboxminime() {
do_install_azboxme
}

FILES_${PN} = "/usr/bin/"
FILES_${PN} += "/usr/lib/"
FILES_${PN} += "/usr/lib/enigma2/python/Plugins/Extensions/AZPlay/"
FILES_${PN} += "/usr/lib/enigma2/python/Plugins/Extensions/AZPlay/img/"



