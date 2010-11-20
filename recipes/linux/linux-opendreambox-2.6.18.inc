require linux-opendreambox.inc

# Change MACHINE_KERNEL_PR in conf/machine/dm*.conf, when something structural changes in the kernelconfig, and all (external) modules need to be rebuilt

SRCREV = "c59d7210272be29e3850aca3abbff76443f65d6f-fixed"
# When changing SRCREV without incrementing MACHINE_KERNEL_PR, increment INCREMENTAL_PR instead, to force a package version increment
INCREMENTAL_PR = "1"

MACHINE_KERNEL_PR_append = "+git${INCREMENTAL_PR}+${SRCREV}"

SRC_URI += " http://trappist.elis.ugent.be/~mronsse/cdfs/download/cdfs-2.6.18.tar.bz2 \
	file://stblinux-2.6.18-cdfs.patch;apply=no \
	file://linux-2.6.18-fix-mips-crosscompile.patch \
	file://linux-2.6.18-fix-proc-cputype.patch \
	file://dvb-api-2.6.18-5.3.patch \
	file://linux-2.6.18-dvb-core-headers-20100904.patch \
	file://linux-2.6.18-dvb-frontends-headers-20100904.patch \
	"

do_configure_prepend() {
	if [ -d ${WORKDIR}/cdfs-${PV} ]; then
		mv ${WORKDIR}/cdfs-${PV} ${S}/fs/cdfs
		cd ${S} & patch -p1 < ${WORKDIR}/stblinux-2.6.18-cdfs.patch
	fi;
}
