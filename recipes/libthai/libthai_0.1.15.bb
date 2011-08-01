DESCRIPTION = "Thai language support routines"
LICENSE = "LGPL"
DEPENDS = "libdatrie-native libdatrie"

SRC_URI = "http://linux.thai.net/pub/thailinux/software/libthai/${PN}-${PV}.tar.gz"

inherit autotools

SRC_URI[md5sum] = "3bc6d925eae36317aac8676be49a5741"
SRC_URI[sha256sum] = "e23eebe92b806b58ab92ddb1bf263253f1b9c4c36d1df04416fe1647dd31c4ed"

