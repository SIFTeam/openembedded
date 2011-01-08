MODULE="stb/tools/libshowiframe"

require valerie.inc

# no working Makefile...
do_compile() {
	${CC} -fPIC -c showiframe.c
	${CC} -shared -Wl,-soname,libshowiframe.so.0 -o libshowiframe.so.0.0.0 showiframe.o
}

do_install() {
	install -d ${D}${PLUGINDIR}
	install -m 755 libshowiframe.so.0.0.0 ${D}${PLUGINDIR}/
	ln -s libshowiframe.so.0.0.0 ${D}${PLUGINDIR}/libshowiframe.so.0
}

FILES_${PN}-dbg += "${PLUGINDIR}/.debug"
