DESCRIPTION = "USB DVB driver for Afatech 9015 chipset"
RDEPENDS_${PN} = " \
	v4l-dvb-module-af9013 \
	v4l-dvb-module-dvb-pll \
	v4l-dvb-module-dvb-usb \
	v4l-dvb-module-dvb-usb-af9015 \
	v4l-dvb-module-mc44s803 \
	v4l-dvb-module-mt2060 \
	v4l-dvb-module-mxl5005s \
	v4l-dvb-module-mxl5007t \
	v4l-dvb-module-qt1010 \
	v4l-dvb-module-tda18218 \
	v4l-dvb-module-tda18271 \
	v4l-dvb-firmware \
	"

PV = "1.0"
PR = "r1"

ALLOW_EMPTY_${PN} = "1"
