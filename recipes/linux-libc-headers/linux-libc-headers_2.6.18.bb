require linux-libc-headers.inc

INHIBIT_DEFAULT_DEPS = "1"
DEPENDS += "unifdef-native"
PR = "r6"

SRC_URI = "${KERNELORG_MIRROR}/pub/linux/kernel/v2.6/linux-2.6.18.tar.bz2 \
				file://arm-syscall-define.patch \
				file://Makefile.patch \
				"

SRC_URI_append_mipsel = " file://mips-add-missing-headers.patch \
				file://mips-fix-ptrace-header.patch \
				"

DVBAPI3_PATCH = "file://fix-dvb-headers.patch"
DVBAPI5_PATCH = "file://dvb-api-2.6.18-5.3.patch"

SRC_URI_append_openpli = " file://mips-brcm-add-missing-syscalls.patch \
				${@base_contains("MACHINE_FEATURES", "dvbapi3", "${DVBAPI3_PATCH}", "", d)} \
				${@base_contains("MACHINE_FEATURES", "dvbapi5", "${DVBAPI5_PATCH}", "", d)} \
				"

S = "${WORKDIR}/linux-2.6.18"

do_configure() {
	oe_runmake allnoconfig ARCH=${ARCH}
}

do_compile () {
}

do_install() {
	oe_runmake headers_install INSTALL_HDR_PATH=${D}${exec_prefix} ARCH=${ARCH}
	# Add UTS_RELEASE to version.h. UTS_RELEASE was moved from version.h to
	# utsrelease.h in order to avoid recompiling a kernel every time a localversion
	# changed. Since the our headers are static and we're not compiling an
	# actual kernel, re-adding UTS_RELEASE does't hurt, and it allows uclibc to
	# compile with kernel headers that work with EABI on ARM
	echo '#define UTS_RELEASE "2.6.18"' >> ${D}${exec_prefix}/include/linux/version.h
}

do_install_append_arm() {
	cp include/asm-arm/procinfo.h ${D}${includedir}/asm
}

SRC_URI[md5sum] = "296a6d150d260144639c3664d127d174"
SRC_URI[sha256sum] = "c95280ff6c5d2a17788f7cc582d23ae8a9a7ba3f202ec6e4238eaadfce7c163d"
