DESCRIPTION = "Mutagen is a Python module to handle audio metadata"
SECTION = "devel/python"
PRIORITY = "optional"
LICENSE = "GPLv2"
SRCNAME = "mutagen"

DEPENDS = "python"
RDEPENDS_${PN} = "python-shell"

SRC_URI = "http://mutagen.googlecode.com/files/mutagen-${PV}.tar.gz"

S = "${WORKDIR}/mutagen-${PV}"

inherit distutils
