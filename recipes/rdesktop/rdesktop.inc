DESCRIPTION = "Rdesktop rdp client for X"
HOMEPAGE = "http://www.rdesktop.org"
DEPENDS = "virtual/libx11 openssl"
SECTION = "x11/network"
LICENSE = "GPL"
LIC_FILES_CHKSUM  = "file://COPYING;md5=c9904273735f3141c1dd61533e02246a"

INC_PR = "r2"

SRC_URI = "${SOURCEFORGE_MIRROR}/rdesktop/rdesktop-${PV}.tar.gz"
