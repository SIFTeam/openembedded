DESCRIPTION = "Handle & repair many DVB radio & television stream types."
MAINTAINER = "Andreas Frisch <andreas.frisch@multimedia-labs.de>"
LICENSE="GPL-2"
SECTION = "optional"
DEPENDS = ""
RDEPENDS_${PN} = ""
PN = "projectx"
PV = "0.90.4.00.b32"
PR = "r0"
SRCDATE = "20090824"

SRC_URI = "cvs://anonymous@project-x.cvs.sourceforge.net/cvsroot/project-x;module=Project-X;method=pserver\
           file://dreambox-headless.patch \
           file://Makefile"

PRECOMPILED_N = "${PN}-mipsel-bin-20100218-${PV}-1.6.0-${TARGET_FPU}.tar.bz2"
PRECOMPILED_URI = "http://dreamboxupdate.com/download/opendreambox/${PRECOMPILED_N}"

do_unpack_extra() {
	mv ${WORKDIR}/Project-X ${S}
	mv ${WORKDIR}/Makefile ${S}/src
}
addtask unpack_extra after do_unpack before do_patch

do_compile_prepend() {
	export CROSS_LIBDIR="${CROSS_DIR}/${TARGET_SYS}/lib"
	export GCJ_ARCH=""
	for gcjarchives in libgcj.a libgij.a; do
		if test -e ${CROSS_LIBDIR}/$gcjarchives; then
			export GCJ_ARCHIVES="${GCJ_ARCHIVES} ${CROSS_LIBDIR}/$gcjarchives"
		fi
	done
	if [ "${GCJ_ARCHIVES}" = "" ]; then
		echo gcj not found, downloading statically linked binary
		exit
	else
		echo ${GCJ_ARCHIVES} found, compiling...
	fi
}

do_compile() {
	export JFLAGS="-g0 -O2 -march=mips32 -encoding \"ISO-8859-1\""
	export CROSS_COMPILE=${TARGET_PREFIX}
	cd ${S}/src
	make projectx-static
}

do_download_precompiled_binary() {
	if ! test -e ${S}/src/projectx; then
		cd ${S}/src
		wget ${PRECOMPILED_URI}
		tar -xjf ${PRECOMPILED_N}
	fi
}
addtask download_precompiled_binary after do_compile before do_install

do_install() {
	install -d ${D}/${bindir}
	install -m 755 ${S}/src/projectx ${D}/${bindir}/
}
