DESCRIPTION = "twisted SNMP framework"
SECTION = "devel/python"
PRIORITY = "optional"
RDEPENDS_${PN} = "python-pysnmp-se"
LICENSE = "LGPL"

SRCNAME = "TwistedSNMP"
SRC_URI = "http://downloads.sourceforge.net/twistedsnmp/${SRCNAME}-${PV}.tar.gz"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit distutils
