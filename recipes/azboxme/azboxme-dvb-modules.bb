DESCRIPTION = "Hardware drivers for azboxme"
SECTION = "base"
PRIORITY = "required"
LICENSE = "proprietary"

SRCDATE = "20120125"

KV = "2.6.22.19-44-opensat"
PV = "2.6.22.19-44-opensat-${SRCDATE}"

PR = "r8"

RDEPENDS = "kernel (${KV})"

DEPENDS = " module-init-tools"
RDEPENDS_append = " module-init-tools-depmod"

SRC_URI = "http://openee.sifteam.eu/azbox/azboxme-dvb-modules_${SRCDATE}.zip \
	   file://staticdevices.tar.gz.install \
           file://setoutputports"

S = "${WORKDIR}"

GCC ?= ""

PACKAGE_STRIP = "no"

inherit module

do_compile() {
}

do_install_mipsel() {
	install -d ${D}/lib/modules/${KV}/extra
	install -d ${D}/${sysconfdir}/modutils
	
	cd ${WORKDIR}/files/modules
	for f in smp8655i2c avl2108 mxl241sf nimdetect 865xdvb sci; do
		install -m 0644 ${WORKDIR}/files/modules/$f.ko ${D}/lib/modules/${KV}/extra/$f.ko
		echo $f >> ${D}/${sysconfdir}/modutils/_${MACHINE}
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
	install -m 0755 ${WORKDIR}/setoutputports ${D}/etc/init.d/setoutputports

	install -d ${D}/etc/mdev
	install -m 0755 ${WORKDIR}/staticdevices.tar.gz.install ${D}/etc/mdev/staticdevices.tar.gz
	
	install -d ${D}/etc/rcS.d
	ln -s ../init.d/setoutputports ${D}/etc/rcS.d/S019setoutputports
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
