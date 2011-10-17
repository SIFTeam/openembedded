DESCRIPTION = "create Dreambox NAND boot images"
SECTION = "console/utils"
LICENSE = "GPL"

PV = "1.3"
SRC_URI = "git://git.opendreambox.org/git/buildimage.git;protocol=git"

SRCREV = "c208f6d5b1fe92de060a94bf327b0d897e64ba93"
S = "${WORKDIR}/git"

inherit autotools native

