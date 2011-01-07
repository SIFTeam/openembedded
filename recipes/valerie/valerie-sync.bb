DEPENDS = "python-native"
PACKAGES = "${PN}"
PACKAGE_ARCH = "all"
MODULE = "pyvalerie/src"
require valerie.inc
PLUGINDIR = "/usr/lib/enigma2/python/Plugins/Extensions/ProjectValerieSync"

do_compile() {
	python -O -m compileall ${S}
}

do_install() {
	install -d ${D}${PLUGINDIR}
	install -m 644 *.py* ${D}${PLUGINDIR}
	install -d ${D}${PLUGINDIR}/cache
	install -d ${D}${PLUGINDIR}/db
	install -d ${D}${PLUGINDIR}/db/episodes
	install -d ${D}${PLUGINDIR}/dl
}

