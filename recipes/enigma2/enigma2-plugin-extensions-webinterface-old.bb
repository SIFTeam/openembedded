DESCRIPTION = "Old webinterface (avoid license issues)"
DEPENDS = "enigma2 python-pyopenssl python-twisted"
RDEPENDS_${PN} = "python-twisted-web python-pyopenssl python-crypt python-unixadmin aio-grab"
PACKAGES = "${PN}-src ${PN}"
RPROVIDES = "enigma2-plugin-extensions-webinterface"

SRCREV = "1f1bfc9149684caccbdfeaed3f8169b6d0213db8"
inherit gitpkgv

PV = "experimental-git${SRCPV}"
PKGV = "experimental-git${GITPKGV}"
PR = "r10"

SRC_URI="git://openpli.git.sourceforge.net/gitroot/openpli/plugins-enigma2;protocol=git \
	 file://buildonlywebif.patch \
	 file://webiftpm.patch\
"

EXTRA_OECONF = " \
	BUILD_SYS=${BUILD_SYS} \
	HOST_SYS=${HOST_SYS} \
	STAGING_INCDIR=${STAGING_INCDIR} \
	STAGING_LIBDIR=${STAGING_LIBDIR} \
	--without-debug \
"

inherit autotools

S = "${WORKDIR}/git"

PLUGINPATH = "/usr/lib/enigma2/python/Plugins/Extensions/WebInterface"


FILES_${PN}-src = "${PLUGINPATH}/*.py ${PLUGINPATH}/*/*.py ${PLUGINPATH}/*/*/*.py"
FILES_${PN} = "/usr/lib/enigma2/python/Plugins/Extensions/WebInterface"

do_install_append() {
	rm -rf ${D}/usr/share
	# remove unused .pyc files
	find ${D}/usr/lib/enigma2/python/ -name '*.pyc' -exec rm {} \;
}
