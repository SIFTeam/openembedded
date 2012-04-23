# libdreamdvd.bb build file
DESCRIPTION = "libdvdnav wrapper for dream multimedia stbs"
LICENSE = "GPL"
DEPENDS = "libdvdnav"
RDEPENDS = "libdvdnav"

inherit gitpkgv

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
PR = "r1"

SRC_URI = "git://schwerkraft.elitedvb.net/libdreamdvd/libdreamdvd.git;protocol=git"
SRC_URI_append_dm7025 = ";branch=7025"
SRC_URI_append = " file://skip_c.patch"

CFLAGS_append = "${@base_contains('MACHINE_FEATURES', 'lpcm', ' -DHARDWARE_SUPPORT_LPCM' , '', d)}"

S = "${WORKDIR}/git"

inherit autotools pkgconfig
