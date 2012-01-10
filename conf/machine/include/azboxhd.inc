# azboxhd.inc

MACHINE_FEATURES += "wifi alsa pci kernel26 usbhost nl80211 wifiusblegacy hdtv textlcd"

TARGET_ARCH = "mipsel"
TARGET_FPU ?= "soft"

DISTRO_FEATURES += " mplt"

EXTRA_IMAGEDEPENDS += "azboxhd-buildimage"

GLIBC_ADDONS ?= "ports,nptl,libidn"
GLIBC_EXTRA_OECONF ?= "--disable-profile --with-tls --without-fp --with-__thread"

#MACHINE_ESSENTIAL_EXTRA_RDEPENDS += "azboxhd-dvb-modules"
MACHINE_FEATURES += "kernel26 textlcd"

PREFERRED_PROVIDER_virtual/kernel = "linux-${MACHINE}"
PREFERRED_PROVIDER_xserver = "xserver-kdrive"
PREFERRED_PROVIDER_virtual/showiframe = "dreambox-dvb-tools-v3"

require conf/machine/include/tune-mips32.inc
