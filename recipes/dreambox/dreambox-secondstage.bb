DESCRIPTION = "Dreambox second stage bootloader"
SECTION = "base"
PRIORITY = "required"
MAINTAINER = "Felix Domke <tmbinc@elitedvb.net>"
PV_mipsel = "82"
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
			/tmp/writenfi /tmp/secondstage.nfi;
			rm /tmp/writenfi /tmp/secondstage.nfi;
		fi
	fi
}
