DESCRIPTION = "Dreambox second stage bootloader"
SECTION = "base"
PRIORITY = "required"
MAINTAINER = "Felix Domke <tmbinc@elitedvb.net>"
PV = "84"
PV_dm7020hd = "85"
PV_dm7025 = "83"
PV_dm7020 = "35"
PV_dm600pvr = "66"
PV_dm500plus = "66"
PR = "r0"

SRC_URI = "http://sources.dreamboxupdate.com/download/7020/secondstage-${MACHINE}-${PV}.bin"

WRITENFI = "writenfi-mipsel-2.6.18-r2"

SECONDSTAGE_UPDATE_SRC = "http://sources.dreamboxupdate.com/download/7020/secondstage-${MACHINE}-${PV}.nfi \
	http://sources.dreamboxupdate.com/download/7020/${WRITENFI}"

SRC_URI_append_mipsel = " ${SECONDSTAGE_UPDATE_SRC}"

S = "${WORKDIR}"

do_stage() {
	install -d ${STAGING_LIBDIR}/dreambox-secondstage
	gzip -c ${S}/secondstage-${MACHINE}-${PV}.bin > ${STAGING_LIBDIR}/dreambox-secondstage/main.bin.gz
}

# secondstage >80 is already compressed
do_stage_mipsel() {
	install -d ${STAGING_LIBDIR}/dreambox-secondstage
	cp ${S}/secondstage-${MACHINE}-${PV}.bin ${STAGING_LIBDIR}/dreambox-secondstage/main.bin.gz
}

do_install_mipsel() {
	install -d ${D}/tmp
	install ${WORKDIR}/secondstage-${MACHINE}-${PV}.nfi ${D}/tmp/secondstage.nfi
	install -m 0755 ${WORKDIR}/${WRITENFI} ${D}/tmp/writenfi
}

FILES_${PN} = "/tmp"
PACKAGE_ARCH := "${MACHINE_ARCH}"

pkg_postinst() {
	if [ -d /proc/stb ]; then
		if [ -f /tmp/writenfi ]; then
			if [ "$(cat /proc/stb/info/model)" = "dm8000" ]; then
				/tmp/writenfi /tmp/secondstage.nfi;
				rm /tmp/writenfi /tmp/secondstage.nfi;
			else
				#hack for broken mtd layer in linux kernel 2.6.18-r13.0
				mv /tmp/writenfi /usr/bin/writenfi;
				mv /tmp/secondstage.nfi /usr/bin/secondstage.nfi;
				echo "#!/bin/sh" > /etc/rcS.d/S05UpdateSecondstage;
				echo "/usr/bin/writenfi /usr/bin/secondstage.nfi || /bin/true" > /etc/rcS.d/S05UpdateSecondstage;
				echo "rm -f /usr/bin/writenfi /usr/bin/secondstage.nfi /etc/rcS.d/S05UpdateSecondstage" >> /etc/rcS.d/S05UpdateSecondstage;
				chmod ugo+x /etc/rcS.d/S05UpdateSecondstage;
			fi
		fi
	fi
}
