DESCRIPTION = "USB DVB driver for Siano chipset"

DVBPROVIDER ?= "v4l-dvb"

RDEPENDS_${PN} = " \
	${DVBPROVIDER}-module-smsusb \
	${DVBPROVIDER}-module-smsdvb \
	firmware-dvb-siano \
	"

PV = "1.0"
PR = "r4"

ALLOW_EMPTY_${PN} = "1"

do_install() {
	if [ "${DVBPROVIDER}" = "v4l-dvb" ]; then
		install -d ${D}/etc/modutils
		echo smsusb > ${D}/etc/modutils/v4l-dvb-usb-siano
		echo smsdvb >> ${D}/etc/modutils/v4l-dvb-usb-siano
	fi
}

pkg_postinst_append() {
if [ -n "$D" ]; then
	exit 1
else
	update-modules || true
fi
}

pkg_postrm_append() {
	update-modules || true
}
