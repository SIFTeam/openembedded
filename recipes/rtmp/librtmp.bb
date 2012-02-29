DESCRIPTION = "librtmp Real-Time Messaging Protocol API"
LICENSE = "LGPL"

DEPENDS = "openssl"

inherit gitpkgv

PKGV = "2.4+git${GITPKGV}"
PV = "2.4+git${SRCPV}"
PR = "r1"

SRC_URI = "git://git.ffmpeg.org/rtmpdump;protocol=git"

S = "${WORKDIR}/git/librtmp"

do_compile() {
	oe_runmake CROSS_COMPILE=${TARGET_PREFIX} CFLAGS="${CFLAGS} -fPIC" LDFLAGS="${LDFLAGS}"
}

do_install() {
	oe_runmake DESTDIR=${D} install
}
