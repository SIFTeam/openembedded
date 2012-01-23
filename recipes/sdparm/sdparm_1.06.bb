DESCRIPTION = "The sdparm utility accesses SCSI device parameters"
HOMEPAGE = "http://sg.danny.cz/sg/sdparm.html"
SECTION = "console/network"
PRIORITY = "optional"
LICENSE = "BSD"
PR = "r0"

SRC_URI = "http://sg.danny.cz/sg/p/sdparm-${PV}.tgz"

inherit autotools

SRC_URI[md5sum] = "5e19913658bb4f9849ddd909e0f47cde"
SRC_URI[sha256sum] = "e011a7a9e87e4e24c9c115f456ac4916fdc314d36ae99167979175782ea2f6b4"
