DESCRIPTION = "Azbox Specific plugin"
RDEPENDS = "enigma2"
DEPENDS = "python-native"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRCREV = "89cf85f894406a4838f8b9b579bc906bf8932439"
inherit gitpkgv

PR = "r1"


SRC_URI = "git://azboxopenpli.git.sourceforge.net/gitroot/azboxopenpli/RtiSYS;protocol=git;tag=${SRCREV}"

S = "${WORKDIR}/git"

do_install() {
	install -d  ${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/RtiSYS
	
	install -m 0644 ${S}/*.py \
	${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/RtiSYS

	install -m 0755 ${S}/ntpdate \
	${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/RtiSYS
	
}

FILES_enigma2-plugin-systemplugins-rtisys = "/usr/lib/enigma2/python/Plugins/SystemPlugins/RtiSYS"

PACKAGES = "\
	enigma2-plugin-systemplugins-rtisys \	
	"

PROVIDES="${PACKAGES}"

