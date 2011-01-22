DESCRIPTION = "Flash extender - Use CF card as extra flash space on DM7025"
MAINTAINER = "Mike Looijmans <dream@milosoftware>"
PACKAGE_ARCH = "dm7025"

PV = "1.08"
PR = "r0"

SRC_URI = 'file://*'

FILES_${PN} = "/etc/init.d/flashextender"

S = "${WORKDIR}"

DEPENDS = ""
RDEPENDS_${PN} = "unionfs-utils"

PACKAGES = "${PN}"

PLUGIN = "FlashExtender"


do_install () {
	install -d ${D}/etc/init.d
	install -m 755 ${S}/flashextender ${D}/etc/init.d
}

# Not using update-rc because the order in which these things happen is critical

pkg_postinst() {
	/etc/init.d/flashextender install
}

pkg_prerm() {
	/etc/init.d/flashextender uninstall
}

pkg_postrm() {
	rm -f $D/etc/rcS.d/S*flashextender
}

