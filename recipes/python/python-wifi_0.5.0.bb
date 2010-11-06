DESCRIPTION = "Fusil is a Python library used to write fuzzing programs."
HOMEPAGE = "http://fusil.hachoir.org/"
SECTION = "devel/python"
LICENSE = "GPLv2"
SRCNAME = "python-wifi"
PR = "r1"

SRC_URI = "http://download.berlios.de/pythonwifi/${SRCNAME}-${PV}.tar.bz2"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools

FILES_${PN}-doc += "/usr/share/docs /usr/share/examples /usr/share/README /usr/share/INSTALL"
FILES_${PN}-dbg += "/usr/lib/python*/site-packages/pythonwifi/*.py"

RDEPENDS_${PN} = "\
  python-ctypes \
  python-datetime \
"



