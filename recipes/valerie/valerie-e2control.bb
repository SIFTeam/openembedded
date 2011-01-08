MODULE="stb/tools/e2control"

require valerie.inc

# no working Makefile...
do_compile() {
	${CC} ${CCFLAGS} -lpthread e2control.c -o e2control
}

do_install() {
	install -d ${D}${PLUGINDIR}
	install -m 755 e2control ${D}${PLUGINDIR}/
}

FILES_${PN}-dbg += "${PLUGINDIR}/.debug"
