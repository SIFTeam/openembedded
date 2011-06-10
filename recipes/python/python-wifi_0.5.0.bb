DESCRIPTION = "Provides access to Linux Wireless Extensions"
HOMEPAGE = "http://pythonwifi.wikispot.org/"
SECTION = "devel/python"
LICENSE = "LGPLv2+"
LICENSE_${PN}-examples = "GPLv2+"
RDEPENDS_${PN} = "python-ctypes python-datetime"

SRC_URI = "http://download.berlios.de/pythonwifi/${P}.tar.bz2"
SRC_URI[md5sum] = "8fe7fd0a4edce1f9bedaff4acb7fd500"
SRC_URI[sha256sum] = "3e3f645d37ab20450f60c785cec5f21b330f28a6c46c7c1b0898305dd7a34b26"

inherit setuptools

FILES_${PN}-doc += "/usr/share/docs /usr/share/examples /usr/share/README /usr/share/INSTALL"
FILES_${PN}-dbg += "/usr/lib/python*/site-packages/pythonwifi/*.py"

PACKAGES =+ "${PN}-examples"

FILES_${PN}-examples = "${sbindir}"
