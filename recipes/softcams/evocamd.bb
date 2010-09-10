CAMNAME = "evocamd"
DESCRIPTION = "evocamd ${PV} softcam"

PN = "enigma2-plugin-softcams-evocamd"
PV = "2.17"
PR = "r1"

SRC_URI = "http://downloads.pli-images.org/softcams/evocamd-${PV}.zip"

S = "${WORKDIR}/evocamd-${PV}"

INHIBIT_PACKAGE_STRIP = "1"

require softcam.inc

CONFFILES = "/usr/keys/feynman.cfg /usr/keys/ignore.list /usr/keys/priority.list /usr/keys/replace.list"

do_install() {
	install -d ${D}/usr/bin
	install -m 0755 ${S}/evocamd.mips ${D}/usr/bin/${CAMNAME}
	install -d ${D}/usr/keys
	install -m 0644 ${S}/camd_cfg ${D}/usr/keys/camd_cfg
	install -m 0644 ${S}/feynman.cfg ${D}/usr/keys/feynman.cfg
	install -m 0644 ${S}/ignore.list ${D}/usr/keys/ignore.list
	install -m 0644 ${S}/priority.list ${D}/usr/keys/priority.list
	install -m 0644 ${S}/replace.list ${D}/usr/keys/replace.list
}

FILES_${PN} += "/usr/keys"
