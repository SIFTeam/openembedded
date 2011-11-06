DESCRIPTION = "sifteam stb libraries"
HOMEPAGE = "http://forum.sifteam.eu/"
SECTION = "sifteam"
DEPENDS = "sockets curl python (=2.6) opkg swig-native libpcre libxml2"
RDEPENDS = "sockets curl python opkg libpcre libxml2"

inherit gitpkgv
SRCREV = ""
PV = "1.6-git${SRCPV}"
PKGV = "1.6-git${GITPKGV}"
PR = "r0"

MENU_URL = "http://openee.sifteam.eu/panel/menu.xml"

SRC_URI = "git://github.com/SIFTeam/libsif.git;protocol=git \
	file://mountsif \
	file://mountsifall \
	file://swap \
	file://modules"

S = "${WORKDIR}/git"

SRCREV_pn-${PN} ?= "${AUTOREV}"

FILES_${PN} = "${bindir}/* ${libdir}/* ${sbindir}/* /etc/*"

CXXFLAGS_append = " -I${STAGING_INCDIR}/libopkg/ -I${STAGING_INCDIR}/Sockets/ -I${STAGING_INCDIR}/python2.6/ -I${STAGING_INCDIR}/libxml2/ "

do_compile() {
	oe_runmake
	${STRIP} libsif.so
	${STRIP} _libsif.so
	${STRIP} emud
	${STRIP} emu
	${STRIP} cs
}

do_install() {
	install -d ${D}/${bindir}
	install -d ${D}/${sbindir}
	install -d ${D}/${libdir}
	install -d ${D}/etc/init.d
	install -d ${D}/etc/rcS.d
	install -d ${D}/etc/rc3.d
	install -d ${D}/etc/rc6.d
	install -d ${D}/${libdir}/python2.6/lib-dynload/

	install -m 644 libsif.so ${D}/${libdir}
	install -m 755 emud ${D}/${sbindir}
	install -m 755 emu ${D}/${bindir}
	install -m 755 cs ${D}/${bindir}
	install -m 755 emud.init ${D}/etc/init.d/emud
	ln -s /etc/init.d/emud ${D}/etc/rc3.d/S90emud
	ln -s /etc/init.d/emud ${D}/etc/rc6.d/K90emud
	install -d ${D}/etc/emud/cs
	install -d ${D}/etc/emud/emu
	install -m 755 _libsif.so ${D}/${libdir}/python2.6/lib-dynload/
	install -m 644 src/libsif.py ${D}/${libdir}/python2.6/
	
	install -m 0755 ${WORKDIR}/mountsif ${D}/${bindir}
	install -m 0755 ${WORKDIR}/mountsifall ${D}/etc/init.d
	install -m 0755 ${WORKDIR}/swap ${D}/etc/init.d
	install -m 0755 ${WORKDIR}/modules ${D}/etc/init.d
	ln -s /etc/init.d/mountsifall ${D}/etc/rcS.d/S50mountsifall
	ln -s /etc/init.d/swap ${D}/etc/rcS.d/S60swap
	ln -s /etc/init.d/modules ${D}/etc/rcS.d/S80modules
	
	install -m 0644 menu_default.xml ${D}/etc
	echo "stb_type = ${MACHINE}" > ${D}/etc/libsif.conf
	echo "menu_url = ${MENU_URL}" >> ${D}/etc/libsif.conf
}
