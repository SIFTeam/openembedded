# dreambox-mipsel.inc

MACHINE_FEATURES += "alsa"

MACHINE_KERNEL_PR = "r6"

PREFERRED_VERSION_linux-${MACHINE} = "2.6.18"
PREFERRED_VERSION_linux-libc-headers = "2.6.18"

PREFERRED_VERSION_mtd-utils = "1.1.0"

PREFERRED_VERSION_madwifi-ng = "r3314-20080131"

PREFERRED_PROVIDER_virtual/showiframe = "dreambox-dvb-tools-v3"

OLDEST_KERNEL = "2.6.12"

TARGET_ARCH = "mipsel"
TARGET_FPU ?= "soft"

GLIBC_EXTRA_OECONF = "--with-tls"

require conf/machine/include/dreambox.inc
require conf/machine/include/tune-mips32.inc
