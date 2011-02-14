DESCRIPTION = "frontpanel update"
SECTION = "base"
PRIORITY = "optional"

PV = "1.0"
PR = "r1"

FPVERSION = "10"

SRC_URI = " \
	http://www.et-view.com/download/fpupdate-${PV}.zip \
	http://www.et-view.com/download/avrmain-${FPVERSION}.hex \
	"
S = "${WORKDIR}"

inherit update-rc.d

INITSCRIPT_NAME = "fpupdate"
INITSCRIPT_PARAMS = "start 9 S ."

FILES_${PN} = "/${bindir} /lib/firmware /etc/init.d"

do_install() {
	install -d ${D}/${bindir}
	install -d ${D}/lib/firmware
	install -m 0755 ${S}/fpupdate ${D}/${bindir}
	install -m 0644 ${S}/avrmain-${FPVERSION}.hex ${D}/lib/firmware/avrmain.hex

	echo "#!/bin/sh" > ${S}/fpupdate.sh
	echo "if ! [ -f /lib/firmware/avrmain.hex ]; then" >> ${S}/fpupdate.sh
	echo "	exit 0" >> ${S}/fpupdate.sh
	echo "fi" >> ${S}/fpupdate.sh
	echo "if ! [ -x ${bindir}/fpupdate ]; then" >> ${S}/fpupdate.sh
	echo "	exit 0" >> ${S}/fpupdate.sh
	echo "fi" >> ${S}/fpupdate.sh
	echo "${bindir}/fpupdate /lib/firmware/avrmain.hex ${FPVERSION} && rm /lib/firmware/avrmain.hex" >> ${S}/fpupdate.sh

	install -d ${D}/etc/init.d
	install -m 0755 ${S}/fpupdate.sh ${D}/etc/init.d/fpupdate
}

PACKAGE_ARCH := "${MACHINE_ARCH}"
