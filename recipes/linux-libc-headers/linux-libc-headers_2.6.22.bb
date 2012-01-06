require linux-libc-headers.inc

INHIBIT_DEFAULT_DEPS = "1"
DEPENDS += "unifdef-native"
DEPENDS += "make-native"
PR = "r7"

SRC_URI = "${KERNELORG_MIRROR}/pub/linux/kernel/v2.6/linux-${PV}.tar.bz2 \
           file://procinfo.h"

SRC_URI_azboxhd = "http://downloads.sourceforge.net/project/rticoree2/kernel/azboxhd-linux-2.6.22.tar.gz \
                   file://unifdef.patch \
                   file://azboxhd-dvb-api.patch"

SRC_URI_azboxme = "http://downloads.sourceforge.net/project/rticoree2/kernel/azboxme-linux-2.6.22.tar.gz \
                   file://unifdef.patch \
                   file://azboxhd-dvb-api.patch"


S = "${WORKDIR}/linux-${PV}"
S_azboxhd = "${WORKDIR}/linux-2.6.22.19"
S_azboxme = "${WORKDIR}/linux-2.6.22.19"

do_configure() {
	oe_runmake allnoconfig ARCH=$ARCH
}

do_compile () {
}

do_install() {
	oe_runmake headers_install INSTALL_HDR_PATH=${D}${exec_prefix} ARCH=$ARCH
}

do_install_append_arm() {
	cp ${WORKDIR}/procinfo.h ${D}${includedir}/asm/
}

SRC_URI[md5sum] = "2e230d005c002fb3d38a3ca07c0200d0"
SRC_URI[sha256sum] = "73c10604c53f1a6ee65ef805293d23903696f8cef864f42d7de9506f0d2ba4c7"
