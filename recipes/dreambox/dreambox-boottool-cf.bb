DESCRIPTION = "CF mount tool"
SECTION = "base"
PRIORITY = "optional"
MAINTAINER = "Mike Looijmans <MiLo@pli-images.org>"

PV = "1.0"
PR = "r1.3"
DEPENDS = "klcc-cross"

SRC_URI = "file://boottool-${MACHINE}.c"

S = "${WORKDIR}/"

do_install_append() {
	install -d ${D}/boot/bin
	install ${S}/boottool ${D}/boot/bin/initcf
	install -d ${D}/boot/dev
}

do_compile_append() {
	${STAGING_BINDIR_CROSS}/klcc ${S}/boottool-${MACHINE}.c -o ${S}/boottool
}

pkg_postinst() {
	mknod -m 660 $D/boot/dev/hdc b 22 0
	mknod -m 660 $D/boot/dev/hdc1 b 22 1
	mv $D/boot/bin/init $D/boot/bin/initflash
	ln $D/boot/bin/initcf $D/boot/bin/init
}

PACKAGE_ARCH := "${MACHINE_ARCH}"
FILES_${PN} = "/boot/bin/initcf /boot/dev"
