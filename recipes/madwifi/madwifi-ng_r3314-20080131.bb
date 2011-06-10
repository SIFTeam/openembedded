# Bitbake recipe for the madwifi-ng driver

require madwifi-ng_r.inc

SRC_URI += " \
	http://sources.dreamboxupdate.com/download/snapshots/openwrt_madwifi_patches_20080829.tar.bz2 \
	http://sources.dreamboxupdate.com/download/snapshots/ath_hal-20080815.tgz \
	file://40-fix-warnings.patch"

MACHINE_KERNEL_PR_append = ".1"

SRC_URI += " \
        svn://svn.openwrt.org/openwrt/trunk/package/madwifi;module=patches \
        http://mirror2.openwrt.org/sources/ath_hal-${HAL_VERSION}.tgz;name=hal \
        file://fix-target-mips32.patch \
        file://remove-wprobe.patch;apply=no \
        "
SRC_URI[md5sum] = "2c7352cbbdac995de8c3bce5b80db5f2"
SRC_URI[sha256sum] = "0599c75b95ba63bdc554cb8124192e62c75fbeb71b9e8a5a7bc351c8e0666758"

do_munge() {
	rm -rf ${S}/hal || /bin/true
	mv ${WORKDIR}/ath_hal-20080815 ${S}/hal
	CUR=`pwd`
	cd ${S}
	for i in `ls ${WORKDIR}/openwrt_madwifi_patches | grep ".patch" | sort -n | xargs`; do
		oenote "Applying openwrt madwifi patch '$i'";
		patch -p1 < ${WORKDIR}/openwrt_madwifi_patches/$i;
	done;
	cd $CUR
}

addtask munge before do_compile after do_patch

# We really must clear out LDFLAGS to get this to link.
do_compile() {
	unset LDFLAGS
	oe_runmake all
}
