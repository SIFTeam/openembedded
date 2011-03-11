DESCRIPTION = "USB DVB driver for dib0700 chipset"
RDEPENDS_${PN} = " \
	v4l-dvb-module-dib0070 \
	v4l-dvb-module-dib0090 \
	v4l-dvb-module-dib3000mb \
	v4l-dvb-module-dib3000mc \
	v4l-dvb-module-dib7000m \
	v4l-dvb-module-dib7000p \
	v4l-dvb-module-dib8000 \
	v4l-dvb-module-dibx000-common \
	v4l-dvb-module-dvb-usb \
	v4l-dvb-module-dvb-usb-dib0700 \
	v4l-dvb-module-dvb-usb-dibusb-common \
	v4l-dvb-module-dvb-usb-dibusb-mb \
	v4l-dvb-module-dvb-usb-dibusb-mc \
	v4l-dvb-module-lgdt3305 \
	v4l-dvb-module-mt2060 \
	v4l-dvb-module-mt2266 \
	v4l-dvb-module-mxl5007t \
	v4l-dvb-module-s5h1411 \
	v4l-dvb-module-tuner-xc2028 \
	v4l-dvb-module-xc5000 \
	v4l-dvb-firmware \
	"

PV = "1.0"
PR = "r1"

ALLOW_EMPTY_${PN} = "1"
