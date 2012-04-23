DESCRIPTION = "Hardware drivers for Dreambox"
SECTION = "base"
PRIORITY = "required"
LICENSE = "proprietary"
MAINTAINER = "Felix Domke <tmbinc@elitedvb.net>"

SRCDATE_dm7020 = "20060622"
SRCDATE_dm600pvr = "20090430"
SRCDATE_dm500plus = "20080822"
SRCDATE_dm7025 = "20110506"
SRCDATE_dm500hd = "20120322"
SRCDATE_dm800 = "20120216"
SRCDATE_dm800se = "20120322"
SRCDATE_dm7020hd = "20120322"
SRCDATE_dm8000 = "20120322"

KV_dm7020 = "2.6.9"
PV_dm7020 = "${KV}-${SRCDATE}"

KV_dm600pvr = "2.6.12"
PV_dm600pvr = "${KV}-${SRCDATE}"

KV_dm500plus = "2.6.12"
PV_dm500plus = "${KV}-${SRCDATE}"

KV_dm7025 = "2.6.12.6"
PV_dm7025 = "${KV}-${SRCDATE}"
GCC_dm7025 = "-gcc4.4"

KV_dm500hd = "2.6.18-7.4-${MACHINE}"
PV_dm500hd = "${KV}-${SRCDATE}"

KV_dm800 = "2.6.18-7.4-${MACHINE}"
PV_dm800 = "${KV}-${SRCDATE}"

KV_dm800se = "2.6.18-7.4-${MACHINE}"
PV_dm800se = "${KV}-${SRCDATE}"

KV_dm7020hd = "2.6.18-7.4-${MACHINE}"
PV_dm7020hd = "${KV}-${SRCDATE}"

KV_dm8000 = "2.6.18-7.4-${MACHINE}"
PV_dm8000 = "${KV}-${SRCDATE}"

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
