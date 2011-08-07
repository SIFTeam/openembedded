DESCRIPTION = "Python support code for TPM checks"
DEPENDS = "python"
PACKAGES = "${PN}"
PACKAGE_ARCH = "all"
LICENSE = "GPL"
PV = "0.2"
PR = "r0"

SRC_URI = "file://*"

S="${WORKDIR}"

RDEPENDS_${PN} = "python-core"

FILES_${PN} = "/usr/lib/enigma2/python"

do_compile() {
	python -O -m compileall ${S}
}

do_install() {
	install -d ${D}/usr/lib/enigma2/python/
	install -m 644 ${S}/*.py* ${D}/usr/lib/enigma2/python/
}

