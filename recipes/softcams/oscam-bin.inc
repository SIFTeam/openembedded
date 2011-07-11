DESCRIPTION = "Oscam binary, freshly compiled"
PV = "svn${SRCPV}"
PR = "r0"
DEPENDS = "openssl"
RREPLACES_${PN} = "oscam-bin"
RCONFLICTS_${PN} = "oscam-bin"
RPROVIDES_${PN} = "oscam"

# SRC_URI = "svn://streamboard.gmc.to/svn/oscam;module=trunk;proto=http;rev=${SRCREV}"
# SRC_URI = "svn://oscam.ump2002.net/svn/oscam;module=trunk;proto=http;rev=${SRCREV}"
SRC_URI = "svn://www.oscam.to/svn/oscam;module=trunk;proto=http;rev=${SRCREV}"

S = "${WORKDIR}/trunk"

# Could not figure out how to link to libusb, so that's disabled for now
EXTRA_OECMAKE = "\
	-DOSCAM_SYSTEM_NAME=Tuxbox \
	-DWEBIF=1 \
	-DWITH_STAPI=1 \
	-DHAVE_LIBUSB=0 \
	"

inherit cmake update-alternatives

do_install_append() {
	mv "${D}${bindir}/oscam" "${D}${bindir}/${PN}"
}

ALTERNATIVE_NAME = "oscam"
ALTERNATIVE_PATH = "${bindir}/${PN}"

