DESCRIPTION = "C++ parsing library for Service Information (SI) in DVB systems"
AUTHOR = "Andreas Oberritter"
SECTION = "libs/multimedia"
PRIORITY = "optional"
LICENSE = "LGPLv2.1"

SRC_URI = "http://www.saftware.de/${PN}/${P}.tar.bz2"
SRC_URI[md5sum] = "79fe791fcfc316a2e13af2b6913a89fb"
SRC_URI[sha256sum] = "f00f17ea53e51ebe273dd2521b47d63b2e001b1c14bc5d2767e245d331f3b2ed"

inherit autotools pkgconfig
