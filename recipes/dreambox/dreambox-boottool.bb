DESCRIPTION = "Squashfs jffs2 unionfs mount tool"
SECTION = "base"
PRIORITY = "required"
LICENSE = "proprietary"
MAINTAINER = "Felix Domke <tmbinc@elitedvb.net>"

PV = "1.0"
PR = "r1.1"
DEPENDS = "klcc-cross"

SRC_URI = "file://boottool-${MACHINE}.c"

S = "${WORKDIR}/"

do_install_append() {
	install -d ${D}/boot/bin
	install ${S}/boottool ${D}/boot/bin/init
}

do_compile_append() {
	${STAGING_BINDIR_CROSS}/klcc ${S}/boottool-${MACHINE}.c -o ${S}/boottool
}

PACKAGE_ARCH := "${MACHINE_ARCH}"
FILES_${PN} = "/boot/bin/init"
