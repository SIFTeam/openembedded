SRCREV = "${AUTOREV}"
MODULE = "1.10"
OSCAMBIN = "oscam-unstable"
OSCAMDEPENS = "openssl"
OSCAMRDEPENDS ="openssl"
SUBURI = "svn/oscam/tags;module=1.10;proto=http;scmdata=keep"
SSL = "DWITH_SSL=1"
PCSC = "DHAVE_PCSC=0"
ALTERNATIVE_PRIORITY = "10"
require oscam-bin.inc

