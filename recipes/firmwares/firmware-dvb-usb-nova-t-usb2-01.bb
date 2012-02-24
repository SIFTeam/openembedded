require linux-firmware.inc

DESCRIPTION = "Firmware for dvb-usb-nova-t-usb2-01.fw"

SRCREV = "2716dd2183038fde8274bb9796a4f70f4dc37efe"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 dvb-usb-nova-t-usb2-01.fw ${D}${base_libdir}/firmware
}
