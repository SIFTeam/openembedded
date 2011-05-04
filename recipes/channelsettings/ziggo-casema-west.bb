SRC_URI = "file://*"

DESCRIPTION = "Hoeba settings Ziggo/Casema West"

MAINTAINER ?= "Mike Looijmans"
PACKAGE_ARCH = "all"

#keep SRCDATE here rather than in the distro file, as the setting files reside in a local subdirectory
SRCDATE = "20110326"
PV = "${SRCDATE}"
PR = "r0"
PN = "enigma2-plugin-settings-ziggo-casema-west"
PACKAGES = "${PN}"
PROVIDES="virtual/enigma2-settings"

FILES_${PN} = "/etc/enigma2/*"
S="${WORKDIR}"

do_install() {
	install -d ${D}/etc/enigma2
	for f in services bouquets* userbouquet*
	do
		install -m 644 ${f} ${D}/etc/enigma2/${f}
	done
}

