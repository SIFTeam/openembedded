DESCRIPTION = "Hardware drivers for azboxme"
SECTION = "base"
PRIORITY = "required"
LICENSE = "proprietary"

SRCDATE = "20111211"

KV = "2.6.22.19-44-opensat"
PV = "2.6.22.19-44-opensat-${SRCDATE}"

PR = "r1"

RDEPENDS = "kernel (${KV})"

DEPENDS = " module-init-tools"
RDEPENDS_append = " module-init-tools-depmod"

SRC_URI = "http://openee.sifteam.eu/azbox/azboxme-dvb-modules_${SRCDATE}.zip \
           file://bootup"

S = "${WORKDIR}"

GCC ?= ""

do_install_mipsel() {
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
	
	install -d ${D}/lib/firmware
	install -m 0644 ${WORKDIR}/files/firmware/dvb-fe-avl2108.fw ${D}/lib/firmware/dvb-fe-avl2108.fw

	cd ${WORKDIR}/files/
	if [ -f settings ];
	then
		install -d ${D}/etc/enigma2
		install -m 0644 ${WORKDIR}/files/settings ${D}/etc/enigma2/settings;
	fi
	
	install -d ${D}/etc/init.d
	install -m 0755 ${WORKDIR}/bootup ${D}/etc/init.d/bootup
	
	install -d ${D}/etc/rcS.d
	ln -s /etc/init.d/bootup ${D}/etc/rcS.d/S04bootup
}

pkg_postinst_azboxme-dvb-modules () {
	if [ -d /proc/stb ]; then
		depmod -ae
		update-modules
	fi
	true
}

pkg_postrm_azboxme-dvb-modules () {
	if [ -d /proc/stb ]; then
		update-modules
	fi
	true
}

PACKAGE_ARCH := "${MACHINE_ARCH}"
FILES_${PN} = "/"
