PR = "${INC_PR}.0"

require klibc.inc
require klibc-checksums_${PV}.inc
DEPENDS = "klibc"

FILESPATHPKG =. "klibc-${PV}:"

export KLCC_INST=${STAGING_DIR_TARGET}/lib/klibc

inherit cross

do_install() {
         install -d ${STAGING_BINDIR_CROSS}/
         install -m 0755 klcc/klcc ${STAGING_BINDIR_CROSS}/klcc
}

PACKAGES = "${PN}"
FILES_${PN} = "${STAGING_BINDIR_CROSS}/klcc"
