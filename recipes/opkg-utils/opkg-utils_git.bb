DESCRIPTION = "OPKG Package Manager Utilities"
SECTION = "base"
HOMEPAGE = "http://wiki.openmoko.org/wiki/Opkg"
PRIORITY = "optional"
RDEPENDS_${PN} = "python"
RDEPENDS_${PN}_virtclass-native = ""

GITREV = "16665959c330b5958c0f0f4624a9ca7f823f98de"
PV = "0.1.8+git-${GITREV}"
PR = "r8"

#SRC_URI = "git://git.yoctoproject.org/opkg-utils;protocol=git"
SRC_URI = "http://git.yoctoproject.org/cgit/cgit.cgi/opkg-utils/snapshot/opkg-utils-${GITREV}.tar.gz \
	    file://index-ignore-filenotfound.patch \
	    file://mtime-int.patch"

S = "${WORKDIR}/opkg-utils-${GITREV}"

# Avoid circular dependencies from package_ipk.bbclass
PACKAGES_virtclass-native = ""

do_install() {
        oe_runmake PREFIX=${prefix} DESTDIR=${D} install
}

BBCLASSEXTEND = "native"
TARGET_CC_ARCH += "${LDFLAGS}"
