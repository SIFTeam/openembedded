DESCRIPTION = "Hardware drivers for azbox"
SECTION = "base"
PRIORITY = "required"
LICENSE = "proprietary"

SRCDATE = "20111208"
PACKAGE_STRIP = "no"

KV = "2.6.22.19-25-opensat"
PV = "2.6.22.19-25+${SRCDATE}"
PR = "r43"
MACHINE_KERNEL_PR_append = ".0"

RDEPENDS = "kernel (${KV})"

SRC_URI = "http://openee.sifteam.eu/azbox/azboxhd-dvb-modules_2011208.zip  \
	   file://bootup"

S = "${WORKDIR}/drivers_${SRCDATE}"

INHIBIT_PACKAGE_STRIP = "1"

do_compile() {
}

do_install() {
	install -d ${D}/etc/init.d
	install -d ${D}/etc/rcS.d
	install ${WORKDIR}/bootup ${D}/etc/init.d
	install ${WORKDIR}/bootup ${D}/etc/rcS.d/S04bootup
	install -d ${D}/lib/modules/${KV}/extra
	
	cd ${WORKDIR}/files/modules
	for f in *.ko; do
		install -m 0644 ${WORKDIR}/files/modules/$f ${D}/lib/modules/${KV}/extra/$f;
	done
	
	cd ${WORKDIR}/files/lib
	for f in *; do
		install -m 0755 ${WORKDIR}/files/lib/$f ${D}/lib/$f;
	done
	
	install -d ${D}/usr/bin
	cd ${WORKDIR}/files/bin
	for f in *; do
		install -m 0755 ${WORKDIR}/files/bin/$f ${D}/usr/bin/$f;
	done 
	
	install -d ${D}/etc/init.d
	install -m 0755 ${WORKDIR}/files/initd/wifi ${D}/etc/init.d/wifi
	
	install -d ${D}/lib/firmware
	install -m 0644 ${WORKDIR}/files/firmware/dvb-fe-cx24116.fw ${D}/lib/firmware/dvb-fe-cx24116.fw

}

PACKAGE_ARCH := "${MACHINE_ARCH}"
FILES_${PN} = "/"
