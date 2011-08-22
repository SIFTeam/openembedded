DESCRIPTION = "Linux kernel for vuplus"
LICENSE = "GPL"
KV = "2.6.18-7.3"
PV = "2.6.18"

MODULE = "stblinux-2.6.18"

DEPENDS = "mtd-minimal-nand-utils"
RDEPENDS_kernel-image = "mtd-minimal-nand-utils"


SRC_URI_GCC44_PATCH = ${@base_contains('PREFERRED_GCC_VERSION', '4.4', 'file://linux_vuplus_gcc_4.4.patch;striplevel=1', '', d)} 


SRC_URI = "http://archive.vuplus.com/download/stblinux-${KV}.tar.bz2 \
	http://trappist.elis.ugent.be/~mronsse/cdfs/download/cdfs-2.6.18.tar.bz2 \
	file://stblinux-2.6.18-cdfs.patch;apply=no \	
        file://linux_vuplus_resource.patch;striplevel=0 \
        file://linux_vuplus_arch_makefile.patch;striplevel=0 \
        file://linux_vuplus_kobject.patch;striplevel=0 \
        file://linux-2.6.18-dvb-frontends.patch;striplevel=0 \
        file://linux-2.6.18-dvb-core.patch;striplevel=0 \
        file://dvb-include-2.6.18-5.3.patch;striplevel=0 \
        file://linux_vuplus_ata.patch;striplevel=1 \
        "   

SRC_URI += ${SRC_URI_GCC44_PATCH}

SRC_URI[md5sum] = "953c4d97b28f19d957e54e71bb01f674"
SRC_URI[sha256sum] = "0cbdf0e243edcd2dc6ae74145689a8e5b06d83f776758220a04a62f316fe984e"

S = "${WORKDIR}/stblinux-2.6.18"

inherit kernel

export OS = "Linux"
KERNEL_OBJECT_SUFFIX = "ko"
KERNEL_OUTPUT = "vmlinux"
KERNEL_IMAGETYPE = "vmlinux"
KERNEL_IMAGEDEST = "tmp"

FILES_kernel-image = "/${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz"

do_configure_prepend() {
        oe_machinstall -m 0644 ${WORKDIR}/${MACHINE}_defconfig ${S}/.config
        if [ -d ${WORKDIR}/cdfs-${PV} ]; then
                mv ${WORKDIR}/cdfs-${PV} ${S}/fs/cdfs
                cd ${S} ; patch -p1 < ${WORKDIR}/stblinux-2.6.18-cdfs.patch
        fi;
        oe_runmake oldconfig
}

kernel_do_install_append() {
        install -d ${D}/${KERNEL_IMAGEDEST}
        install -m 0755 ${KERNEL_OUTPUT} ${D}/${KERNEL_IMAGEDEST}
        gzip ${D}/${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}
}

pkg_postinst_kernel-image () {
        if [ -d /proc/stb ] ; then
                flash_eraseall -j /dev/mtd1
                nandwrite -p /dev/mtd1 /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz
        fi
        rm -f /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz
        true
}

