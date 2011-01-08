DESCRIPTION = "Project Valerie - Media Center"
DEPENDS = "python"
PACKAGES = "${PN}"
PACKAGE_ARCH = "all"
MODULE = "ValerieMediaCenter"
require valerie.inc

RDEPENDS_${PN} = "python-ctypes valerie-libshowiframe valerie-sync valerie-e2control"
RREPLACES_${PN} = "project-valerie"
RCONFLICTS_${PN} = "project-valerie"

do_compile() {
	sed -i "s/\"r001\"/\"r${SRCPV}\"/g" __init__.py
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

