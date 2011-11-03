DESCRIPTION = "USB DVB driver for Afatech 9015 chipset"

DVBPROVIDER ?= "v4l-dvb"

RDEPENDS_${PN} = " \
	${DVBPROVIDER}-module-dvb-usb-af9015 \
	${DVBPROVIDER}-module-af9013 \
	${DVBPROVIDER}-module-dvb-pll \
	${DVBPROVIDER}-module-mc44s803 \
	${DVBPROVIDER}-module-mt2060 \
	${DVBPROVIDER}-module-mxl5005s \
	${DVBPROVIDER}-module-mxl5007t \
	${DVBPROVIDER}-module-qt1010 \
	${DVBPROVIDER}-module-tda18218 \
	${DVBPROVIDER}-module-tda18271 \
	v4l-dvb-firmware \
	"

PV = "1.0"
PR = "r2"

ALLOW_EMPTY_${PN} = "1"
