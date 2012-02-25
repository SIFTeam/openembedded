DESCRIPTION = "Hardware drivers for azboxhd"
SECTION = "base"
PRIORITY = "required"
LICENSE = "proprietary"

SRCDATE = "20120225"

KV = "2.6.22.19-25-opensat"
PV = "2.6.22.19-25-opensat-${SRCDATE}"

PR = "r0"

RDEPENDS = "kernel (${KV})"

DEPENDS = " module-init-tools"
RDEPENDS_append = " module-init-tools-depmod"

SRC_URI = "http://openee.sifteam.eu/azbox/azboxhd-dvb-modules_${SRCDATE}.zip \
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
	for f in llad em8xxx; do
		install -m 0644 ${WORKDIR}/files/modules/$f.ko ${D}/lib/modules/${KV}/extra/$f.ko
	done
	for f in sci usbserial ftdi_sio smp8634i2c cx24116 mxl201rf tda10023 mxl5007t zl10353 stv6110x stv090x nimdetect  863xdvb rt61; do
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
	
	install -d ${D}/etc/init.d
	install -m 0755 ${WORKDIR}/files/initd/wifi ${D}/etc/init.d/wifi
	install -m 0755 ${WORKDIR}/setoutputports ${D}/etc/init.d/setoutputports
	
	install -d ${D}/etc/mdev
	install -m 0755 ${WORKDIR}/staticdevices.tar.gz.install ${D}/etc/mdev/staticdevices.tar.gz
	
	install -d ${D}/etc/rcS.d
	ln -s ../init.d/setoutputports ${D}/etc/rcS.d/S19setoutputports
	
	install -d ${D}/lib/firmware
	install -m 0644 ${WORKDIR}/files/firmware/dvb-fe-cx24116.fw ${D}/lib/firmware/dvb-fe-cx24116.fw
}

pkg_postinst_azboxhd-dvb-modules () {
	if [ -d /proc/stb ]; then
		depmod -a
		update-modules
	fi
	true
}

pkg_postrm_azboxhd-dvb-modules () {
	if [ -d /proc/stb ]; then
		update-modules
	fi
	true
}

PACKAGE_ARCH := "${MACHINE_ARCH}"
FILES_${PN} = "/"
