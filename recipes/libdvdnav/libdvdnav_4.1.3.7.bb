# libdvdnav.bb build file
HOMEPAGE="http://git.debian-maintainers.org/"
DESCRIPTION="DVD navigation multimeda library"
LICENSE = "GPL"
DEPENDS = "libdvdread"
RDEPENDS_${PN} = "libdvdread"
SECTION = "libs/multimedia"

# debian/4.1.3-7
SRCREV = "850e513d4fea29b40879378b13003cd677e5214b"

PR = "r0"

SRC_URI = "git://git.debian-maintainers.org/git/daniel/libdvdnav.git;protocol=git;branch=debian"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

do_munge() {
	for i in `ls debian/patches | grep ".patch" | sort -n`; do
		oenote "Applying debian patch '$i'";
		patch -p1 < debian/patches/$i;
	done;
}

addtask munge before do_configure after do_patch
