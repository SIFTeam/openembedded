DESCRIPTION = "OpenDreambox: W-LAN Task for the OpenDreambox Distribution"
SECTION = "opendreambox/base"
LICENSE = "MIT"
PR = "r3"

inherit task

#
# task-opendreambox-wlan
#
DESCRIPTION_${PN} = "OpenDreambox: W-LAN Support"
DEPENDS_${PN} = "enigma2-plugins"
RDEPENDS_${PN} = "\
  enigma2-plugin-systemplugins-wirelesslan \
  wireless-tools \
  wpa-supplicant \
"

WLAN_CRYPTO_MODULES = "\
  kernel-module-aes-generic \
  kernel-module-arc4 \
  kernel-module-ecb \
  kernel-module-cryptomgr \
  kernel-module-crypto-hash \
  kernel-module-aead \
  kernel-module-pcompress \
  kernel-module-crypto-blkcipher \
  kernel-module-crypto-algapi \
"

WLAN_PCI_MODULES = "\
  kernel-module-ath5k \
"

WLAN_USB_MODULES = "\
  kernel-module-rt73usb \
  kernel-module-zd1211rw \
  rt73-firmware \
  zd1211-firmware \
"

WLAN_USB_MODULES_2_6_18 = "\
  zd1211b \
  wlan-rt73 \
"

RDEPENDS_${PN}_append_dm500hd = "\
  ${@base_contains('PREFERRED_VERSION_linux-dm500hd', '2.6.18', '${WLAN_USB_MODULES_2_6_18}', '${WLAN_CRYPTO_MODULES} ${WLAN_USB_MODULES}', d)} \
"

RDEPENDS_${PN}_append_dm800 = "\
  ${@base_contains('PREFERRED_VERSION_linux-dm800', '2.6.18', '${WLAN_USB_MODULES_2_6_18}', '${WLAN_CRYPTO_MODULES} ${WLAN_USB_MODULES}', d)} \
"

RDEPENDS_${PN}_append_dm800se = "\
  ${@base_contains('PREFERRED_VERSION_linux-dm800se', '2.6.18', '${WLAN_USB_MODULES_2_6_18}', '${WLAN_CRYPTO_MODULES} ${WLAN_USB_MODULES}', d)} \
"

RDEPENDS_${PN}_append_dm8000 = "\
  ${@base_contains('PREFERRED_VERSION_linux-dm8000', '2.6.18', 'task-opendreambox-madwifi', '${WLAN_CRYPTO_MODULES} ${WLAN_PCI_MODULES}', d)} \
"

RDEPENDS_${PN}_append_dm7025 = "\
  ${WLAN_CRYPTO_MODULES} \
  ${WLAN_USB_MODULES} \
"

PACKAGE_ARCH = "${MACHINE_ARCH}"

