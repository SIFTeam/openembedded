DESCRIPTION = "yEnc module for Python"
SECTION = "devel/python"
DEPENDS = "python"
PRIORITY = "optional"
LICENSE = "GPL"
SRCNAME = "yenc"
PR = "ml1"

SRC_URI = "http://www.golug.it/pub/yenc/${SRCNAME}-${PV}.tar.gz"
S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit distutils

SRC_URI[md5sum] = "5bdcad71607e4bf7874c9d5fa203c7c7"
