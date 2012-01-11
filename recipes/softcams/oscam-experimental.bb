SRCREV = "${AUTOREV}"
#SRCREV = "6145"
MODULE = "trunk"
OSCAMBIN = "oscam-experimental"
OSCAMDEPENS = "openssl"
OSCAMRDEPENDS ="openssl"
URI = "svn://oscam.to/svn/oscam;module=trunk;proto=http;scmdata=keep"
SSL = "DWITH_SSL=1"
PCSC = "DHAVE_PCSC=0"
ALTERNATIVE_PRIORITY = "20"
require oscam-bin.inc

