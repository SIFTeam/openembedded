DESCRIPTION = "CF mount tool"
SECTION = "base"
PRIORITY = "optional"
MAINTAINER = "Mike Looijmans <MiLo@pli-images.org>"

PV = "1.0"
PR = "r6"

inherit klibc

SRC_URI = "file://boottool-${MACHINE}.c file://root_to_cf.sh"

S = "${WORKDIR}/"

do_install() {
	install -d ${D}/boot/bin
	install ${S}/boottool ${D}/boot/bin/initcf
	install -d ${D}/boot/dev
	install -d ${D}/usr/bin
	install -m 755 ${WORKDIR}/root_to_cf.sh ${D}/usr/bin/
}

do_compile() {
	${CC} ${S}/boottool-${MACHINE}.c -o ${S}/boottool
}

# Need a read/write /boot filesystem
pkg_preinst() {
	if grep -q ' /boot ' /proc/mounts
	then
		mount -o remount,rw /boot
	fi
}

pkg_postinst() {
	mknod -m 660 $D/boot/dev/hdc b 22 0
	mknod -m 660 $D/boot/dev/hdc1 b 22 1
	if [ ! -f $D/boot/bin/initflash ]
	then
		ln $D/boot/bin/init $D/boot/bin/initflash
	fi
}

pkg_prerm() {
	if [ -f /boot/bin/initflash ]
	then
		rm -f /boot/bin/init
		mv /boot/bin/initflash /boot/bin/init
	fi
}

PACKAGE_ARCH := "${MACHINE_ARCH}"
FILES_${PN} = "/boot/bin/initcf /boot/dev /usr/bin"
