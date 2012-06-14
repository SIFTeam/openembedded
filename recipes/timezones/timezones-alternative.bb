DESCRIPTION = "Timezone data, alternative"
SECTION = "base"
PRIORITY = "optional"
MAINTAINER = "Felix Domke <tmbinc@elitedvb.net>"
LICENSE = "GPL"

PV = "2012b"
PR = "r1"

SRC_URI = "file://zoneinfo.tar.bz2"
S = "${WORKDIR}/zoneinfo"

FILES_${PN} = "usr/share/zoneinfo"
PACKAGE_ARCH = "all"

do_install() {
	install -d ${D}/usr/share/zoneinfo/

	cd ${S}
	cp -a * ${D}/usr/share/zoneinfo/
	true;
}
