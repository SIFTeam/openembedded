DESCRIPTION = "SysV init scripts (stripped) for opendreambox"
MAINTAINER = "Felix Domke <tmbinc@elitedvb.net>"
SECTION = "base"
PRIORITY = "required"
PROVIDES = "initscripts"
RPROVIDES_${PN} = "initscripts"
LICENSE = "GPL"
PR = "r44"
PR_dm7025 = "r31"

FILESPATHPKG = "initscripts-${PV}:initscripts:files"
PACKAGE_ARCH = "${MACHINE_ARCH}"

BOOTUP_dm7025 = "${@base_contains('PREFERRED_VERSION_linux-dm7025', '2.6.12.6', 'bootup_old', 'bootup', d)}"
BOOTUP ?= "bootup"

SRC_URI = "file://halt \
           file://umountfs \
           file://devpts.sh \
           file://devpts \
           file://reboot \
           file://single \
           file://sendsigs \
           file://rmnologin \
           file://umountnfs.sh \
           file://sysfs.sh \
           file://netmount.sh \
           file://var.tar.gz.default \
           file://${BOOTUP}"

SRC_URI_append_dm8000 = " file://fscking.raw"
SRC_URI_append_bm750 = " file://hotplug_br \
			file://turnoff_power \
			file://make_mac_sector"
SRC_URI_append_vusolo = " file://hotplug_br \
			file://turnoff_power \
			file://make_mac_sector"

FILES_${PN} = "${sysconfdir} /usr"

do_install () {
#
# Create directories and install device independent scripts
#
	install -d ${D}/usr/keys
	install -d ${D}${sysconfdir}/init.d
	install -d ${D}${sysconfdir}/rcS.d
	install -d ${D}${sysconfdir}/rc0.d
	install -d ${D}${sysconfdir}/rc1.d
	install -d ${D}${sysconfdir}/rc2.d
	install -d ${D}${sysconfdir}/rc3.d
	install -d ${D}${sysconfdir}/rc4.d
	install -d ${D}${sysconfdir}/rc5.d
	install -d ${D}${sysconfdir}/rc6.d
	install -d ${D}${sysconfdir}/default
	install -d ${D}${sysconfdir}/default/volatiles

	install -m 0755    ${WORKDIR}/halt		${D}${sysconfdir}/init.d
	install -m 0755    ${WORKDIR}/reboot		${D}${sysconfdir}/init.d
	install -m 0755    ${WORKDIR}/rmnologin		${D}${sysconfdir}/init.d
	install -m 0755    ${WORKDIR}/sendsigs		${D}${sysconfdir}/init.d
	install -m 0755    ${WORKDIR}/single		${D}${sysconfdir}/init.d
	install -m 0755    ${WORKDIR}/devpts.sh		${D}${sysconfdir}/init.d
	install -m 0755    ${WORKDIR}/devpts		${D}${sysconfdir}/default
	install -m 0755    ${WORKDIR}/sysfs.sh		${D}${sysconfdir}/init.d
	install -m 0755    ${WORKDIR}/${BOOTUP}		${D}${sysconfdir}/init.d/bootup
	install -m 0755    ${WORKDIR}/var.tar.gz.default ${D}${sysconfdir}/var.tar.gz
#
# Install device dependent scripts
#
	if [ "${MACHINE}" = "dm600pvr" -o "${MACHINE}" = "dm500plus" ]; then
		head -n 4 ${WORKDIR}/umountfs > ${D}${sysconfdir}/init.d/umountfs
		echo "cd /tmp" >> ${D}${sysconfdir}/init.d/umountfs
		tail -n 14 ${WORKDIR}/umountfs >> ${D}${sysconfdir}/init.d/umountfs
		chmod 0755 ${D}${sysconfdir}/init.d/umountfs
		ln -sf /usr/bin/showshutdownpic ${D}${sysconfdir}/rc0.d/S89showshutdownpic
	else
		if [ "${MACHINE}" = "vusolo" -o "${MACHINE}" = "bm750" ]; then
			install -d ${D}/usr/bin
			install -m 0755    ${WORKDIR}/hotplug_br	${D}/usr/bin
			install -m 0755    ${WORKDIR}/make_mac_sector	${D}/usr/bin
			install -m 0755    ${WORKDIR}/turnoff_power	${D}/usr/bin
		fi
		install -m 0755 ${WORKDIR}/umountfs	${D}${sysconfdir}/init.d/umountfs
		install -d ${D}${sysconfdir}/network/if-up.d
		install -m 0755 ${WORKDIR}/netmount.sh  ${D}${sysconfdir}/network/if-up.d/02netmount
		install -d ${D}${sysconfdir}/network/if-down.d
		install -m 0755 ${WORKDIR}/umountnfs.sh	${D}${sysconfdir}/network/if-down.d/02umountnfs
	fi

	if [ "${MACHINE}" = "dm8000" ]; then
		install -m 0755 ${WORKDIR}/fscking.raw ${D}${sysconfdir}/
	fi

	#hack for non working "passwd" call when no /etc/shadow exist
	echo "[ -f /etc/shadow ] && /bin/true || touch /etc/shadow" >> ${D}${sysconfdir}/init.d/bootup

#
# Create runlevel links
#
	ln -sf		../init.d/rmnologin	${D}${sysconfdir}/rc2.d/S99rmnologin
	ln -sf		../init.d/rmnologin	${D}${sysconfdir}/rc3.d/S99rmnologin
	ln -sf		../init.d/rmnologin	${D}${sysconfdir}/rc4.d/S99rmnologin
	ln -sf		../init.d/rmnologin	${D}${sysconfdir}/rc5.d/S99rmnologin
	ln -sf		../init.d/sendsigs	${D}${sysconfdir}/rc6.d/S20sendsigs
	ln -sf		../init.d/umountfs	${D}${sysconfdir}/rc6.d/S40umountfs
	ln -sf		../init.d/reboot	${D}${sysconfdir}/rc6.d/S90reboot
	ln -sf		../init.d/sendsigs	${D}${sysconfdir}/rc0.d/S20sendsigs
	ln -sf		../init.d/umountfs	${D}${sysconfdir}/rc0.d/S40umountfs
	ln -sf		../init.d/halt		${D}${sysconfdir}/rc0.d/S90halt
	# udev will run at S03 if installed
	ln -sf		../init.d/sysfs.sh	${D}${sysconfdir}/rcS.d/S02sysfs
	ln -sf		../init.d/devpts.sh	${D}${sysconfdir}/rcS.d/S38devpts.sh
	ln -sf		../init.d/bootup	${D}${sysconfdir}/rcS.d/S04bootup
}
