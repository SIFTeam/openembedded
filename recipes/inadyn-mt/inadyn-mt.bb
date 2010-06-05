PV = "v.02.18.24+cvs${SRCDATE}"
PR = "r0"

SRC_URI = "cvs://anonymous@inadyn-mt.cvs.sourceforge.net/cvsroot/inadyn-mt;module=${PN};tag=unicows;date=${SRCDATE} \
	file://inadyn-mt.sh"

S = "${WORKDIR}/${PN}"

inherit update-rc.d

INITSCRIPT_NAME = "inadyn-mt"

do_compile() {
	make -f makefile-deprecated
}

do_install() {
	install -d ${D}/usr/bin
	${STRIP} ${S}/bin/linux/inadyn-mt
	install -m 755 ${S}/bin/linux/inadyn-mt ${D}/usr/bin
	install -d ${D}/etc/init.d
	install -m 755 ${WORKDIR}/inadyn-mt.sh ${D}/etc/init.d/inadyn-mt
}
