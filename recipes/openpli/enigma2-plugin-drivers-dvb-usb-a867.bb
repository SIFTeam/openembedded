DESCRIPTION = "USB DVB driver for AVerMedia a867 chipset"

DVBPROVIDER ?= "v4l-dvb"

RDEPENDS_${PN} = " \
	${DVBPROVIDER}-module-dvb-usb-a867 \
	"

PV = "1.0"
PR = "r0"

ALLOW_EMPTY_${PN} = "1"
