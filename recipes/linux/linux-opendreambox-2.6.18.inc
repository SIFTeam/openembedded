require linux-opendreambox.inc

# Change MACHINE_KERNEL_PR in conf/machine/dm*.conf, when something structural changes in the kernelconfig, and all (external) modules need to be rebuilt

SRCREV = "ac6cc9511a5f70eaa584c63fc5c3de33cae1d0e7"
# When changing SRCREV without incrementing MACHINE_KERNEL_PR, increment INCREMENTAL_PR instead, to force a package version increment
INCREMENTAL_PR = "5"

PR_INC = "+git${INCREMENTAL_PR}+${SRCREV}"

SRC_URI += " http://trappist.elis.ugent.be/~mronsse/cdfs/download/cdfs-2.6.18.tar.bz2 \
	file://stblinux-2.6.18-cdfs.patch;apply=no \
	file://stblinux-2.6.18-extra-version-7.4.patch \
	file://stblinux-2.6.18-brcmnand-oob-raw-write-fix.patch \
	file://linux-2.6.18-fix-mips-crosscompile.patch \
	file://linux-2.6.18-fix-proc-cputype.patch \
	file://dvb-api-2.6.18-5.3.patch \
	file://linux-2.6.18-dvb-core-headers-20100904.patch \
	file://linux-2.6.18-dvb-frontends-headers-20100904.patch \
	${@base_contains('MACHINE', 'dm7020hd', '', 'file://stblinux-2.6.18-hw-ecc-compatibility.patch', d)} \
	file://stblinux-2.6.18-fixed-brcmnand-buffer-overflow.patch \
	file://stblinux-2.6.18-brcmnand-fixed-dm7020hd-oob-write-op.patch \
	file://linux-2.6.18-Makefile.patch \
	file://linux-2.6.18-ftdi_triplereader.patch \
	"

do_configure_prepend() {
	if [ -d ${WORKDIR}/cdfs-${PV} ]; then
		mv ${WORKDIR}/cdfs-${PV} ${S}/fs/cdfs
		cd ${S} ; patch -p1 < ${WORKDIR}/stblinux-2.6.18-cdfs.patch
	fi;
}
