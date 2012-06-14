DESCRIPTION="AiO screenshot grabber"
MAINTAINER = "PLi team"
LICENSE = "GPL"

DEPENDS = "jpeg libpng zlib"

inherit gitpkgv

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
PR = "r6"

SRC_URI = "git://openpli.git.sourceforge.net/gitroot/openpli/aio-grab;protocol=git"
SRC_URI_append_azboxme = " file://azbox.patch"
SRC_URI_append_azboxminime = " file://azbox.patch"
SRC_URI_append_azboxhd = " file://azbox.patch"

SRC_URI_powerpc += "file://no_ac_openmp.patch"

S = "${WORKDIR}/git"

inherit autotools pkgconfig
