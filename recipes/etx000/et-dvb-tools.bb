DESCRIPTION = "Small dvb utilities"
SECTION = "base"
PRIORITY = "optional"
LICENSE = "GPL"

PROVIDES += "virtual/showiframe"
RPROVIDES_${PN} += "virtual/showiframe"

PV = "1.0"
PR = "r0"

SRC_URI = "file://showiframe.c"

SRC_URI += "file://showiframe.pro"

inherit qmake

UTILS = "showiframe"

do_configure_prepend() {
	cd ${S}/
	echo "TEMPLATE=subdirs" > dmutils.pro
	echo "CONFIG=console" >> dmutils.pro
	echo "SUBDIRS=${UTILS}" >> dmutils.pro
	install -d ${S}/showiframe
	install -m 0644 ${WORKDIR}/showiframe.c ${S}/showiframe/showiframe.c
	install -m 0644 ${WORKDIR}/showiframe.pro ${S}/showiframe/showiframe.pro
}

do_install() {
	install -d ${D}/${bindir}/
	for u in ${UTILS}
	do
		install -m 0755 ${S}/${u}/${u} ${D}/${bindir}/
	done
}

PACKAGE_ARCH := "${MACHINE_ARCH}"
