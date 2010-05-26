# dreambox.inc

EXTRA_IMAGEDEPENDS += "dreambox-secondstage dreambox-buildimage-native"

MACHINE_ESSENTIAL_EXTRA_RDEPENDS += "dreambox-dvb-modules"

MACHINE_FEATURES += "kernel26"

# openpli specific features:
MACHINE_FEATURES += "dreambox"

PREFERRED_PROVIDER_virtual/kernel = "linux-${MACHINE}"
PREFERRED_PROVIDER_xserver = "xserver-kdrive"

