DESCRIPTION = "A Pure Python SNMP Package"
SECTION = "devel/python"
PRIORITY = "optional"
RDEPENDS_${PN} = "python-core"
LICENSE = "LGPL"


SRCNAME = "pysnmp-se"
SRC_URI = "http://downloads.sourceforge.net/twistedsnmp/${SRCNAME}-${PV}.tar.gz"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit distutils
