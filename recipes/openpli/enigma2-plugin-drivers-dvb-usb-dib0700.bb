DESCRIPTION = "USB DVB driver for dib0700 chipset"

DVBPROVIDER ?= "v4l-dvb"

RDEPENDS_${PN} = " \
	${DVBPROVIDER}-module-dib0070 \
	${DVBPROVIDER}-module-dib0090 \
	${DVBPROVIDER}-module-dib3000mb \
	${DVBPROVIDER}-module-dib3000mc \
	${DVBPROVIDER}-module-dib7000m \
	${DVBPROVIDER}-module-dib7000p \
	${DVBPROVIDER}-module-dib8000 \
	${DVBPROVIDER}-module-dibx000-common \
	${DVBPROVIDER}-module-dvb-usb \
	${DVBPROVIDER}-module-dvb-usb-dib0700 \
	${DVBPROVIDER}-module-dvb-usb-dibusb-common \
	${DVBPROVIDER}-module-dvb-usb-dibusb-mb \
	${DVBPROVIDER}-module-dvb-usb-dibusb-mc \
	${DVBPROVIDER}-module-lgdt3305 \
	${DVBPROVIDER}-module-mt2060 \
	${DVBPROVIDER}-module-mt2266 \
	${DVBPROVIDER}-module-mxl5007t \
	${DVBPROVIDER}-module-s5h1411 \
	${DVBPROVIDER}-module-tuner-xc2028 \
	${DVBPROVIDER}-module-xc5000 \
	v4l-dvb-firmware \
	"

PV = "1.0"
PR = "r1"

ALLOW_EMPTY_${PN} = "1"
