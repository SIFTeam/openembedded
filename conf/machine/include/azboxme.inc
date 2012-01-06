# azboxme.inc

MACHINE_FEATURES += "wifi alsa pci kernel26 usbhost nl80211 wifiusblegacy"

TARGET_ARCH = "mipsel"
TARGET_FPU ?= "soft"

DISTRO_FEATURES += " mplt"

GLIBC_ADDONS ?= "ports,nptl,libidn"
GLIBC_EXTRA_OECONF ?= "--disable-profile --with-tls --without-fp --with-__thread"

MACHINE_FEATURES += "kernel26"

PREFERRED_PROVIDER_virtual/kernel = "linux-${MACHINE}"
PREFERRED_PROVIDER_xserver = "xserver-kdrive"

require conf/machine/include/tune-mips32.inc
