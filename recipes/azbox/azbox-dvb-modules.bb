DESCRIPTION = "Hardware drivers for azbox"
SECTION = "base"
PRIORITY = "required"
LICENSE = "proprietary"

SRCDATE = "20110825"
PACKAGE_STRIP = "no"

KV = "2.6.22.19-25-the-ripper"
PV = "2.6.22.19-25+${SRCDATE}"
PR = "r2"
MACHINE_KERNEL_PR_append = ".0"

RDEPENDS = "kernel (${KV})"

SRC_URI = "http://openee.sifteam.eu/azbox/drivers_${SRCDATE}.zip \
	   file://bootup"

S = "${WORKDIR}/drivers_${SRCDATE}"

INHIBIT_PACKAGE_STRIP = "1"

do_compile() {
}

do_install() {
	install -d ${D}/etc/init.d
	install -d ${D}/etc/rcS.d
	install ${S}/etc/init.d/wifi ${D}/etc/init.d
	install ${WORKDIR}/bootup ${D}/etc/init.d
	install ${WORKDIR}/bootup ${D}/etc/rcS.d/S04bootup
	install -d ${D}/lib/
	install ${S}/lib/lib*.so ${D}/lib
	install -d ${D}/lib/firmware
	install ${S}/lib/firmware/*.fw ${D}/lib/firmware
	install -d ${D}/usr/bin
	install ${S}/usr/bin/* ${D}/usr/bin
	install -d ${D}/lib/modules/${KV}/extra
	install -m 0755 ${S}/lib/modules/${KV}/extra/*.ko ${D}/lib/modules/${KV}/extra
}

PACKAGE_ARCH := "${MACHINE_ARCH}"
FILES_${PN} = "/"
