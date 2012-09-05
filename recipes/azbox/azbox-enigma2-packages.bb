DESCRIPTION = "Azbox Specific plugin"
RDEPENDS = "enigma2"
DEPENDS = "python-native"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRCREV = "56d482eb5eba395c275f6d80c1bda3f7a7145c6b"
inherit gitpkgv

PR = "r0"


SRC_URI = "git://azboxopenpli.git.sourceforge.net/gitroot/azboxopenpli/RtiSYS;protocol=git;tag=${SRCREV}"

S = "${WORKDIR}/git"

do_install() {
	install -d  ${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/RtiSYS
	
	install -m 0644 ${S}/*.py \
	${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/RtiSYS

	python -O -m compileall ${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/RtiSYS

	install -m 0755 ${S}/ntpdate \
	${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/RtiSYS
	
}

FILES_enigma2-plugin-systemplugins-rtisys = "/usr/lib/enigma2/python/Plugins/SystemPlugins/RtiSYS"

PACKAGES = "\
	enigma2-plugin-systemplugins-rtisys \	
	"

PROVIDES="${PACKAGES}"

