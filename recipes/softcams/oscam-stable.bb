SRCREV = "5741"
MODULE = "1.10"
OSCAMBIN = "oscam-stable"
OSCAMDEPENS = "openssl"
OSCAMRDEPENDS ="openssl"
SUBURI = "svn/oscam/tags;module=1.10;proto=http;scmdata=keep;rev=${SRCREV}"
SSL="DWITH_SSL=1"
PCSC = "DHAVE_PCSC=0"
ALTERNATIVE_PRIORITY = "10"
require oscam-bin.inc

