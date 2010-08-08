DESCRIPTION = "Bootlogo support"
SECTION = "base"
PRIORITY = "required"
LICENSE = "proprietary"

IMAGES_VERSION = "1"
BINARY_VERSION = "1"
BINARY_VERSION_bm750 = "6"

PV = "${BINARY_VERSION}.${IMAGES_VERSION}"
PR = "r3"


SRC_URI = "file://bootlogo.mvi file://backdrop.mvi file://bootlogo_wait.mvi file://switchoff.mvi"

S = "${WORKDIR}/"

MVI = "bootlogo backdrop bootlogo_wait"

do_install() {
	install -d ${D}/boot
	install -m 0755 ${S}/bootlogo-${MACHINE}-${BINARY_VERSION}.elf ${D}/boot/bootlogo.elf
	for i in ${MVI}; do
		install -m 0755 ${S}/$i-${MACHINE}-${IMAGES_VERSION}.mvi ${D}/boot/$i.mvi;
	done;
}


do_install_vuplus() {
	install -d ${D}/boot
	install -d ${D}/usr/share
	for i in ${MVI}; do
		install -m 0755 ${S}/$i.mvi ${D}/usr/share/$i.mvi;
		ln -sf /usr/share/$i.mvi ${D}/boot/$i.mvi;
	done;
}

pkg_preinst() {
	[ -d /proc/stb ] && mount -o rw,remount /boot
}

pkg_postinst() {
	[ -d /proc/stb ] && mount -o ro,remount /boot
}

pkg_prerm() {
	[ -d /proc/stb ] && mount -o rw,remount /boot
}

pkg_postrm() {
	[ -d /proc/stb ] && mount -o ro,remount /boot
}

PACKAGE_ARCH := "${MACHINE_ARCH}"
FILES_${PN} = "/boot /usr/share"
