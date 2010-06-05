DESCRIPTION ?= "${OWNER} settings ${SATNAMES}"
MAINTAINER ?= "Mike Looijmans"
PACKAGE_ARCH = "all"

PV = "${SRCDATE}"
PR = "r1"
PN = "enigma2-plugin-settings-${OWNER}-${SATNAMES}"
PACKAGES = "${PN}"

FILES_${PN} = "/etc/enigma2/*"
S="${WORKDIR}/Rytec_E2_${SATNAMES}"

do_install() {
	install -d ${D}/etc/enigma2
	for f in lamedb bouquets.* userbouquet.*
	do
		install -m 644 ${f} ${D}/etc/enigma2/${f}
	done
}

