# libdvdnav.bb build file
HOMEPAGE="http://dvdnav.mplayerhq.hu"
DESCRIPTION="DVD navigation multimeda library"
LICENSE = "GPL"
DEPENDS = "libdvdread"
RDEPENDS_${PN} = "libdvdread"
SECTION = "libs/multimedia"

PR = "r0"

SRC_URI = "http://dvdnav.mplayerhq.hu/releases/libdvdnav-${PV}.tar.bz2"

inherit autotools pkgconfig

SRC_URI[md5sum] = "53be8903f9802e101929a3451203bbf6"
SRC_URI[sha256sum] = "8c971b08276c89ddcecd26fc44204460fd250dc57346f03476d3077188c47550"
