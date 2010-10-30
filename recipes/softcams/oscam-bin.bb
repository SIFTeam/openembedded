DESCRIPTION = "Oscam binary, freshly compiled"
SVNREV = "3665"
PV = "svn${SVNREV}"
PR = "r0"
DEPENDS = "libusb openssl"

SRC_URI = "svn://streamboard.gmc.to/svn/oscam;module=trunk;proto=http;rev=${SVNREV}"

S = "${WORKDIR}/trunk"

# Could not figure out how to link to libusb, so that's disabled for now
EXTRA_OECMAKE = "\
	-DOSCAM_SYSTEM_NAME=Tuxbox \
	-DWEBIF=1 \
	-DWITH_STAPI=1 \
	-DHAVE_LIBUSB=0 \
	"

inherit cmake

