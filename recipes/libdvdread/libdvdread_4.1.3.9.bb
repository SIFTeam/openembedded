DESCRIPTION = "libdvdread provides a simple foundation for reading DVD video disks. \
It provides the functionality that is required to access many DVDs. \
It parses IFO files, reads NAV-blocks, and performs CSS authentication and descrambling."
HOMEPAGE = "http://www.dtek.chalmers.se/groups/dvd/development.shtml"
LICENSE = "GPL"
DEPENDS = "libdvdcss"
RRECOMMENDS_${PN} = "libdvdcss"
SECTION = "libs/multimedia"

# debian/4.1.3-9
SRCREV = "8d2745c6e61cbcf70112d5bb0d15685090e20af1"

PR = "r0"

SRC_URI = "git://git.debian-maintainers.org/git/daniel/libdvdread.git;protocol=git;branch=debian;tag=${SRCREV}"
S = "${WORKDIR}/git"

inherit autotools pkgconfig

EXTRA_OEMAKE = "DESTDIR=${D}"

do_munge() {
	for i in `ls debian/patches | grep ".patch" | sort -n`; do
		oenote "Applying debian patch '$i'";
		patch -p1 < debian/patches/$i;
	done;
}

addtask munge before do_configure after do_patch

do_stage() {
	autotools_stage_all
	cat ${S}/misc/dvdread-config | sed -e "s,-I/usr/include,-I${STAGING_INCDIR}," \
				     | sed -e "s,-L/usr/lib,-L${STAGING_LIBDIR}," \
		> ${STAGING_BINDIR_CROSS}/dvdread-config
	chmod a+rx ${STAGING_BINDIR_CROSS}/dvdread-config
}
