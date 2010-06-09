DESCRIPTION = "Interface definitions for Zope products"
SECTION = "console/network"
PRIORITY = "optional"
LICENSE = "ZPL"

PACKAGES =+ "${PN}-tests"
RDEPENDS_${PN}-tests = "${PN}"

RPROVIDES_${PN} += " zope-interfaces"

inherit setuptools

PR = "r2"

SRC_URI = "http://pypi.python.org/packages/source/z/zope.interface/zope.interface-${PV}.tar.gz"
S = "${WORKDIR}/zope.interface-${PV}"

FILES_${PN}-dev += "${PYTHON_SITEPACKAGES_DIR}/zope/interface/*.c"
FILES_${PN}-doc += "${PYTHON_SITEPACKAGES_DIR}/*.egg-info/"
FILES_${PN}-doc += "${PYTHON_SITEPACKAGES_DIR}/zope/interface/*.txt"
FILES_${PN}-tests = " \
	${PYTHON_SITEPACKAGES_DIR}/zope/interface/tests \
	${PYTHON_SITEPACKAGES_DIR}/zope/interface/common/tests \
	"

SRC_URI[md5sum] = "685fc532550abb07cca6190c9cd2b901"
SRC_URI[sha256sum] = "b81d898532c47f73475c2d0c23580e289eed25923d007eca8e62750c0998bba0"
