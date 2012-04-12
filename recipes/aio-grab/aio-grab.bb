DESCRIPTION="AiO screenshot grabber"
MAINTAINER = "PLi team"
LICENSE = "GPL"

DEPENDS = "jpeg libpng zlib"

inherit gitpkgv

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
PR = "r1"

SRC_URI = "git://openpli.git.sourceforge.net/gitroot/openpli/aio-grab;protocol=git"
SRC_URI_azboxhd = "git://openpli.git.sourceforge.net/gitroot/openpli/aio-grab;rev=d024e177217f0102e9c3c57881c2cac8ea2d7016;protocol=git \
		   file://azbox_support.patch"
SRC_URI_azboxme = "git://openpli.git.sourceforge.net/gitroot/openpli/aio-grab;rev=d024e177217f0102e9c3c57881c2cac8ea2d7016;protocol=git \
		   file://azbox_support.patch"



S = "${WORKDIR}/git"

inherit autotools pkgconfig
