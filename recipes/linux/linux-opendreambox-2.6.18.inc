require linux-opendreambox.inc

# Change MACHINE_KERNEL_PR in conf/machine/dm*.conf, when something structural changes in the kernelconfig, and all (external) modules need to be rebuilt

SRCREV = "97b869e710d741d3eda4f7c915d6190b4054d290"
# When changing SRCREV without incrementing MACHINE_KERNEL_PR, increment INCREMENTAL_PR instead, to force a package version increment
INCREMENTAL_PR = "1"

MACHINE_KERNEL_PR_append = "+git${INCREMENTAL_PR}+${SRCREV}"

SRC_URI += " http://trappist.elis.ugent.be/~mronsse/cdfs/download/cdfs-2.6.18.tar.bz2 \
	file://stblinux-2.6.18-cdfs.patch \
	file://linux-2.6.18-fix-mips-crosscompile.patch \
	file://linux-2.6.18-fix-proc-cputype.patch \
	file://linux-2.6.18-hdd-detection-fix.patch"

do_configure_prepend() {
	if [ -d ${WORKDIR}/cdfs-${PV} ]; then
		mv ${WORKDIR}/cdfs-${PV} ${S}/fs/cdfs
	fi;
}
