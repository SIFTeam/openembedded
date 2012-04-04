DESCRIPTION = "AirTunes protocol server"
DEPENDS = "openssl"

inherit gitpkgv

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
PR = "r0"
SRCREV = ${AUTOREV}

SRC_URI = "git://github.com/skaman/shairport.git;protocol=git"

S = "${WORKDIR}/git"

FILES_${PN} = "${bindir}/*"

do_compile() {
	make -f Makefile.alsa hairtunes
	${STRIP} ${S}/hairtunes
}

do_install() {
	install -d ${D}/${bindir}
	install -m 755 ${S}/hairtunes ${D}/${bindir}
}

