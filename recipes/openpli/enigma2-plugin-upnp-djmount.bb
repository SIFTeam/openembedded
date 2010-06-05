DESCRIPTION = "UPnP media client"
RDEPENDS_${PN} = "djmount gst-plugin-neonhttpsrc"

PN = "enigma2-plugin-upnp-djmount"
PV = "0.71"
PR = "r2"

S = "${WORKDIR}"

ALLOW_EMPTY_${PN} = "1"
