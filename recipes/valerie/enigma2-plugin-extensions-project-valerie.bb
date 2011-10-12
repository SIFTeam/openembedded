DESCRIPTION = "Project Valerie - Media Center"
DEPENDS = "python"
PACKAGES = "${PN}"
PACKAGE_ARCH = "all"
MODULE = "ValerieMediaCenter"
require valerie.inc
PR = "r4"

RDEPENDS_${PN} = "python-ctypes python-shell valerie-libshowiframe valerie-e2control"
RREPLACES_${PN} = "project-valerie"
RCONFLICTS_${PN} = "project-valerie"

do_compile() {
	# remove .svn dirs
	rm -rf `find . -name '.svn' -type d`
	# patch SVN version
	sed -i "s/\"r001\"/\"r${SRCPV}\"/g" __init__.py
	# sanitize file modes
	chmod a-x *.py */*.py
	# Compile python files (and blatantly ignore errors)
	python -O -m compileall ${S} || true
	# Create weird file, required for the plugin to work
	echo "oe16" > oe.txt
}

do_install() {
	install -d ${D}${PLUGINDIR}
	cp -r . ${D}${PLUGINDIR}/
}

