require avahi.inc

PR = "${INC_PR}.1"

DEPENDS += "intltool-native"

PACKAGES =+ "libavahi-gobject"

EXTRA_OECONF += "--disable-gtk3"

SRC_URI[md5sum] = "2f22745b8f7368ad5a0a3fddac343f2d"
SRC_URI[sha256sum] = "8372719b24e2dd75de6f59bb1315e600db4fd092805bd1201ed0cb651a2dab48"
