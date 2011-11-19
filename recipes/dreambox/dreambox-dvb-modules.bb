DESCRIPTION = "Hardware drivers for Dreambox"
SECTION = "base"
PRIORITY = "required"
LICENSE = "proprietary"
MAINTAINER = "Felix Domke <tmbinc@elitedvb.net>"

SRCDATE_dm7020 = "20060622"
SRCDATE_dm600pvr = "20090430"
SRCDATE_dm500plus = "20080822"
SRCDATE_dm7025 = "${@base_contains('PREFERRED_VERSION_linux-${MACHINE}', '2.6.12.6', '20110506', '20100727', d)}"
SRCDATE_dm500hd = "${@base_contains('PREFERRED_VERSION_linux-${MACHINE}', '2.6.18', '20110902', '20090727', d)}"
SRCDATE_dm800 = "${@base_contains('PREFERRED_VERSION_linux-${MACHINE}', '2.6.18', '20110902', '20090723', d)}"
SRCDATE_dm800se = "20110902"
SRCDATE_dm7020hd = "20111109"
SRCDATE_dm8000 = "${@base_contains('PREFERRED_VERSION_linux-${MACHINE}', '2.6.18', '20110902', '20090820', d)}"

KV_dm7020 = "2.6.9"
PV_dm7020 = "${KV}-${SRCDATE}"

KV_dm600pvr = "2.6.12"
PV_dm600pvr = "${KV}-${SRCDATE}"

KV_dm500plus = "2.6.12"
PV_dm500plus = "${KV}-${SRCDATE}"

KV_dm7025 = "${@base_contains('PREFERRED_VERSION_linux-${MACHINE}', '2.6.12.6', '2.6.12.6', '2.6.32-1.3-${MACHINE}', d)}"
PV_dm7025 = "${KV}-${SRCDATE}"
GCC_dm7025 = "${@base_contains('PREFERRED_VERSION_linux-${MACHINE}', '2.6.12.6', '-gcc4.4', '', d)}"

KV_dm500hd = "${@base_contains('PREFERRED_VERSION_linux-${MACHINE}', '2.6.18', '2.6.18-7.4-${MACHINE}', '2.6.30-${MACHINE}', d)}"
PV_dm500hd = "${KV}-20111109-revert-${SRCDATE}"

KV_dm800 = "${@base_contains('PREFERRED_VERSION_linux-${MACHINE}', '2.6.18', '2.6.18-7.4-${MACHINE}', '2.6.30-${MACHINE}', d)}"
PV_dm800 = "${KV}-20111109-revert-${SRCDATE}"

KV_dm800se = "2.6.18-7.4-${MACHINE}"
PV_dm800se = "${KV}-20111109-revert-${SRCDATE}"

KV_dm7020hd = "2.6.18-7.4-${MACHINE}"
PV_dm7020hd = "${KV}-${SRCDATE}"

KV_dm8000 = "${@base_contains('PREFERRED_VERSION_linux-${MACHINE}', '2.6.18', '2.6.18-7.4-${MACHINE}', '2.6.30-${MACHINE}', d)}"
PV_dm8000 = "${KV}-20111109-revert-${SRCDATE}"

RDEPENDS_${PN} = "kernel (${KV})"

RDEPENDS_${PN}_append_dm8000 = " dreambox-secondstage"
RDEPENDS_${PN}_append_dm800 = " dreambox-secondstage"
RDEPENDS_${PN}_append_dm500hd = " dreambox-secondstage"
RDEPENDS_${PN}_append_dm800se = " dreambox-secondstage"
RDEPENDS_${PN}_append_dm7020hd = " dreambox-secondstage"

MACHINE_KERNEL_PR_append = ".3"
GCC ?= ""

inherit module

do_compile() {
}

do_strip_modules() {
}

SRC_URI = "http://sources.dreamboxupdate.com/snapshots/dreambox-dvb-modules-${MACHINE}-${KV}-${SRCDATE}${GCC}.tar.bz2 \
			${@base_contains("MACHINE_FEATURES", "frontprocessor", "http://sources.dreamboxupdate.com/download/7020/fpupgrade-${MACHINE}-v7", "", d)} \
			"

S = "${WORKDIR}"

do_install_powerpc() {
	install -d ${D}/lib/modules/${KV}/extra
	for f in head; do
		install -m 0644 $f.ko ${D}/lib/modules/${KV}/extra/$f.ko;
	done
	install -d ${D}/${sysconfdir}/modutils
	echo head > ${D}/${sysconfdir}/modutils/dreambox
}

do_install_mipsel() {
	install -d ${D}/lib/modules/${KV}/extra
	for f in *.ko LICENSE; do
		install -m 0644 ${WORKDIR}/$f ${D}/lib/modules/${KV}/extra/$f;
	done
	install -d ${D}/${sysconfdir}/modutils
	for i in `ls | grep \\.ko | sed -e 's/.ko//g'`; do
		echo $i >> ${D}/${sysconfdir}/modutils/dreambox
	done
	${@base_contains("MACHINE_FEATURES", "frontprocessor", "install -d ${D}${sbindir}; install -m 0755 ${WORKDIR}/fpupgrade-${MACHINE}-v7 ${D}${sbindir}/fpupgrade", "", d)}
}

PACKAGE_ARCH := "${MACHINE_ARCH}"
FILES_${PN} += "${sbindir}"
