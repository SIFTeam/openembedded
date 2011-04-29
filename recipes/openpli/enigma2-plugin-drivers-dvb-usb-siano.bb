DESCRIPTION = "USB DVB driver for Siano chipset"
RDEPENDS_${PN} = " \
	v4l-dvb-module-smsmdtv \
	v4l-dvb-module-smsusb \
	v4l-dvb-module-smsdvb \
	v4l-dvb-firmware \
	"

PV = "1.0"
PR = "r1"

ALLOW_EMPTY_${PN} = "1"

do_install() {
	install -d ${D}/etc/modutils
	echo smsusb > ${D}/etc/modutils/v4l-dvb-usb-siano
	echo smsdvb >> ${D}/etc/modutils/v4l-dvb-usb-siano
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
