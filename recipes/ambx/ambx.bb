DEPENDS = "libusb"
DESCIPTION = "For 7025/8000 only. Connect amBX kit to USB and run ambxd to get some effects."
MAINTAINER = "PLi team"
PACKAGE_ARCH = "${MACHINE}"
LICENSE = "GPLv2"

PACKAGES =+ "${PN}-test"

inherit gitpkgv

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
PR = "r0"

SRC_URI = "git://openpli.git.sourceforge.net/gitroot/openpli/plugin-ambx;protocol=git"

S = "${WORKDIR}/git"

# Dunno why, but it won't build in parallel
PARALLEL_MAKE = ""

# Only provide --with-machine on supported platforms..
EXTRA_OECONF_dm7025 = "--with-machine=${MACHINE}"
EXTRA_OECONF_dm8000 = "--with-machine=${MACHINE}"
EXTRA_OECONF_dm800  = "--with-machine=${MACHINE}"
EXTRA_OECONF_dm500hd= "--with-machine=${MACHINE}"
EXTRA_OECONF_vuduo  = "--with-machine=${MACHINE}"
inherit autotools

FILES_${PN}-test = "/usr/bin/unittest /usr/bin/grabvid /usr/bin/testambx"
FILES_${PN}-dbg += "/usr/lib/python2.5/site-packages/.debug"

# link against local so's instead of the staged ones
EXTRA_OEMAKE = 'LDFLAGS="-L${S} -L${STAGING_DIR}/${TARGET_SYS}/lib"'
