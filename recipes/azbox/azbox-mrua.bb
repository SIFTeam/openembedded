DESCRIPTION = "Hardware user space LIBs for ${MACHINE}"
SECTION = "libs"
PRIORITY = "required"
LICENSE = "proprietary"

PR = "r0"

SRC_URI_azboxme = "http://azbox-enigma2-project.googlecode.com/files/${MACHINE}-mrua-3.11.tar.gz"
SRC_URI_azboxminime = "http://azbox-enigma2-project.googlecode.com/files/${MACHINE}-mrua-3.11.tar.gz"
SRC_URI_azboxhd = "http://azbox-enigma2-project.googlecode.com/files/${MACHINE}-mrua-2.8.tar.gz"

S = "${WORKDIR}"

INHIBIT_PACKAGE_STRIP = "1"

do_install() {
    install -d ${D}${libdir}
    for f in *.so; do
        oe_libinstall -s -C ${S}/ ${f%\.*} ${D}${libdir};
    done

}
FILES_${PN} += "${libdir}/lib*"
