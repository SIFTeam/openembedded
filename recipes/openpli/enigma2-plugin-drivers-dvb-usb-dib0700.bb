DESCRIPTION = "USB DVB driver for dib0700 chipset"

DVBPROVIDER ?= "v4l-dvb"

RDEPENDS_${PN} = " \
	${DVBPROVIDER}-module-dvb-usb-dib0700 \
	${DVBPROVIDER}-module-dvb-usb-dibusb-common \
	${DVBPROVIDER}-module-dvb-usb-dibusb-mb \
	${DVBPROVIDER}-module-dvb-usb-dibusb-mc \
	${DVBPROVIDER}-module-dib3000mb \
	${DVBPROVIDER}-module-lgdt3305 \
	${DVBPROVIDER}-module-mt2060 \
	${DVBPROVIDER}-module-mt2266 \
	${DVBPROVIDER}-module-mxl5007t \
	${DVBPROVIDER}-module-s5h1411 \
	${DVBPROVIDER}-module-tuner-xc2028 \
	${DVBPROVIDER}-module-xc5000 \
	v4l-dvb-firmware \
	"

RRECOMMENDS_${PN} = " \
	${DVBPROVIDER}-module-xc4000 \
	"

PV = "1.0"
PR = "r2"

ALLOW_EMPTY_${PN} = "1"
