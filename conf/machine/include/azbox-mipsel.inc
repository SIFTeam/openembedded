# azbox-mipsel.inc

MACHINE_FEATURES += "alsa"

#MACHINE_KERNEL_PR = "r6"

PREFERRED_VERSION_linux-${MACHINE} = "2.6.22.19"
PREFERRED_VERSION_linux-libc-headers = "2.6.22"

TARGET_ARCH = "mipsel"
TARGET_FPU ?= "soft"

#DISTRO_FEATURES = "nfs smbfs wifi nl80211 ext2 vfat ipv4 ipv6 usbhost alsa pci mplt"
DISTRO_FEATURES = " ext2 vfat ipv4 usbhost alsa pci mplt"

PREFERRED_PROVIDER_virtual/kernel = "linux-${MACHINE}"
PREFERRED_PROVIDER_xserver = "xserver-kdrive"


GLIBC_ADDONS ?= "ports,nptl,libidn"
GLIBC_EXTRA_OECONF ?= "--disable-profile --with-tls --without-fp --with-__thread"


