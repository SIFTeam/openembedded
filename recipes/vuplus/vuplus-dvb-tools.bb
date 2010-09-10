DESCRIPTION = "Small utilities specific to the Vuplus dvb receiver (for DVB v3)"
SECTION = "base"
PRIORITY = "optional"
LICENSE = "GPL"

PV = "1.7"
PR = "r1"

PROVIDES += "virtual/showiframe"
RPROVIDES_${PN} += "virtual/showiframe"

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
