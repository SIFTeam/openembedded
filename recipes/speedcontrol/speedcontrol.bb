DESCRIPTION = "Controls CD/DVD drive speed"
LICENSE = "GPLv2"
SRC_URI = "file://*"

S = "${WORKDIR}"

do_compile() {
	${CC} speedcontrol.c -o ${PN}
}

do_install() {
	install -d ${D}/usr/bin
	install -m 755 ${PN} ${D}/usr/bin/${PN}
}
