require linux-opendreambox.inc

DESCRIPTION = "Linux kernel for Dreambox DM7025"
PN = "linux-dm7025"
KV = "2.6.12"
PV = "2.6.12.6"

MACHINE_KERNEL_PR_append = ".1"

DEPENDS = "zlib-native zlib"

# note, the rX in the filename is *NOT* the packet revision - it's the patch revision.
SRC_URI = "${KERNELORG_MIRROR}/pub/linux/kernel/v2.6/linux-${PV}${PATCHLEVEL}.tar.bz2 \
	file://defconfig \
	http://sources.dreamboxupdate.com/download/kernel-patches/linuxmips-${KV}-dream-r6.patch.bz2;striplevel=1 \
	http://sources.dreamboxupdate.com/download/kernel-patches/linux-${KV}-update_dvbapi-r1.patch.bz2;striplevel=1 \
	http://sources.dreamboxupdate.com/download/kernel-patches/linux-2.6.12-dvb-multipid-r4.patch.bz2;striplevel=1 \
	http://sources.dreamboxupdate.com/download/kernel-patches/linux-2.6.12-dvb-core-fix-several-locking-problems.patch.bz2;striplevel=1 \
	http://sources.dreamboxupdate.com/download/kernel-patches/linux-2.6.12-dvbapi-pilot-rolloff-extension-r0.patch.bz2;striplevel=1 \
	http://sources.dreamboxupdate.com/download/kernel-patches/linux-2.6.12-update-wireless.patch.bz2;striplevel=1 \
	file://linux-2.6-trailing-whitespaces-in-params.patch \
	file://linuxmips-2.6.12-gcc433-compile-fix.patch;striplevel=1 \
	file://linuxmips-2.6.12-gcc44-compile-fixes.patch;striplevel=1 \
	file://linuxmips-2.6.12-revert-fadvise-fix.patch;striplevel=1 \
	file://linuxmips-2.6.12-add-cpu-feature-overrides.patch;striplevel=1 \
	http://www.kernel.org/pub/linux/kernel/people/rml/inotify/v2.6/0.23/inotify-0.23-rml-2.6.12-15.patch;striplevel=1 \
	file://sysctlwrite.patch \
#squashfs-lzma stuff
	ftp://ftp.slax.org/source/slax/sqlzma/old-versions-unsupported/sqlzma443/sqlzma3.2-r2b.tar.bz2;apply=no \
	http://dreamboxupdate.com/download/kernel-patches/sqlzma2k-3.2-r2-2.6.12.6.patch.bz2;apply=no \
	http://dreamboxupdate.com/download/patches/fix_lzma_squashfs_makefiles_for_oe-r2.patch.bz2;apply=no \
	${SOURCEFORGE_MIRROR}/squashfs/squashfs3.2-r2.tar.gz \
	${SOURCEFORGE_MIRROR}/sevenzip/lzma443.tar.bz2 \
	file://${WORKDIR}/squashfs-lzma/kernel-patches/linux-2.6.12/squashfs3.2-patch;striplevel=1;apply=yes \
	file://linux-2.6.12-dvb-core-fix-frontend-shutdown-timeout.patch \
"

inherit kernel

KERNEL_OBJECT_SUFFIX = "ko"

addtask mungesquash after do_unpack before do_patch

do_mungesquash () {
	if [ -d ${WORKDIR}/squashfs3.2-r2 ]; then
		mv ${WORKDIR}/squashfs3.2-r2/* ${WORKDIR}
		rm -R ${WORKDIR}/squashfs3.2-r2
		for i in sqlzma1-443.patch sqlzma2u-3.2-r2.patch fix_lzma_squashfs_makefiles_for_oe-r2.patch; 
		do
	    		echo "Applying $i"
			patch -d ${WORKDIR} -p1 < ${WORKDIR}/$i
		done
	fi
	CUR=`pwd`
	cd ${WORKDIR}
	install -d ${WORKDIR}/squashfs-lzma
	for i in `find -maxdepth 1 | grep -v linux | xargs`; 
	do
		case $i in
			".");;
			"./squashfs-lzma");;
			"./temp");;
			"./image");;
			"./install");;
			"./defconfig");;
			*)mv $i ${WORKDIR}/squashfs-lzma;;
		esac
	done
	cd $CUR
}

do_compile_prepend () {
	if [ -f ${S}/.patched ];
	then
		patch -R -d ${S} -p1 < ${WORKDIR}/squashfs-lzma/sqlzma2k-3.2-r2-2.6.12.6.patch
		rm ${S}/.patched
	fi
}

do_compile_kernelmodules_append () {
	patch -d ${S} -p1 < ${WORKDIR}/squashfs-lzma/sqlzma2k-3.2-r2-2.6.12.6.patch
	touch ${S}/.patched
	oe_runmake -C ${WORKDIR}/squashfs-lzma KDir=${S} BUILD_CC="${CC}" BUILD_CXX="${CXX}" BUILD_LD="${LD}" BUILD_AR="${AR}" BUILD_LDFLAGS="${TARGET_LDFLAGS}" BUILD_CFLAGS="${TARGET_CFLAGS}" BUILD_CXXFLAGS="${TARGET_CXXFLAGS}"
	for i in mksquashfs unsquashfs; 
	do
		mv ${WORKDIR}/squashfs-lzma/squashfs-tools/$i ${WORKDIR}/squashfs-lzma/squashfs-tools/$i-${ARCH}
	done
	oe_runmake -C ${WORKDIR}/squashfs-lzma KDir=${S} clean
	oe_runmake -C ${WORKDIR}/squashfs-lzma KDir=${S}
	patch -R -d ${S} -p1 < ${WORKDIR}/squashfs-lzma/sqlzma2k-3.2-r2-2.6.12.6.patch
	rm ${S}/.patched
}

do_install_append () {
	for i in sqlzma.ko unlzma.ko; 
	do 
		install -m 0644 ${WORKDIR}/squashfs-lzma/C/7zip/Compress/LZMA_C/kmod/$i ${D}/lib/modules/2.6.12.6/kernel/fs/squashfs
	done;
	install -d ${D}/usr/bin
	for i in mksquashfs unsquashfs;
	do
		install ${WORKDIR}/squashfs-lzma/squashfs-tools/$i-${ARCH} ${D}/usr/bin/$i
	done;
	install ${WORKDIR}/squashfs-lzma/C/7zip/Compress/LZMA_Alone/lzma ${STAGING_BINDIR_NATIVE}
	install ${WORKDIR}/squashfs-lzma/C/7zip/Compress/LZMA_C/lzmadec ${STAGING_BINDIR_NATIVE}
	install ${WORKDIR}/squashfs-lzma/squashfs-tools/mksquashfs ${STAGING_BINDIR_NATIVE}
	install ${WORKDIR}/squashfs-lzma/squashfs-tools/unsquashfs ${STAGING_BINDIR_NATIVE}
}

PACKAGES_append = " unsquashfs mksquashfs"
FILES_mksquashfs = "/usr/bin/mksquashfs"
FILES_unsquashfs = "/usr/bin/unsquashfs"

