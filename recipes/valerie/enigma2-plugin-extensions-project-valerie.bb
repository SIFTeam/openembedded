# we cannot use PACKAGES_DYNAMIC = "enigma2-plugin-*"  here, because enigma2-plugins already has it,
# so we only publish enigma2-plugin-pli-* here (as a result, only those can occur in any RDEPENDS)
DESCRIPTION = "Project Valerie - Media Center"
DEPENDS = "python-native"
PACKAGES = "${PN}"
PACKAGE_ARCH = "all"
MODULE = "ValerieMediaCenter"
require valerie.inc

RDEPENDS_${PN} = "valerie-libshowiframe valerie-sync valerie-e2control"
RREPLACES_${PN} = "project-valerie"

do_compile() {
	python -O -m compileall ${S}
	echo "oe16" > oe.txt
}

do_install() {
	install -d ${D}${PLUGINDIR}
	install -m 644 *.py* *.xml *.png *.txt ${D}${PLUGINDIR}
	# Should i use "install" here?
	cp -r skins ${D}${PLUGINDIR}/
	rm -rf `find ${D}${PLUGINDIR}/skins -name '.svn' -type d`;
}

