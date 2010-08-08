DESCRIPTION = "SysV init scripts for VuPlus"
SECTION = "base"
PRIORITY = "required"
DEPENDS = "makedevs"
RDEPENDS = "makedevs"
LICENSE = "GPL"
PR = "r23"

#FILESPATH = "${@base_set_filespath([ '${FILE_DIRNAME}/${P}', '${FILE_DIRNAME}/initscripts-${PV}', '${FILE_DIRNAME}/files', '${FILE_DIRNAME}' ], d)}"
#deprecated
FILESPATHPKG = "initscripts-${PV}/vuplus:initscripts-${PV}:initscripts:files"


SRC_URI = "file://halt \
			file://umountfs \
			file://devpts.sh \
			file://devpts \
			file://turnoff_power \
			file://reboot \
			file://single \
			file://sendsigs \
			file://rmnologin \
			file://umountnfs.sh \
			file://netmount.sh \
			file://var.tar.gz.default \
			file://sysfs.sh \
			file://hotplug_br \
			file://make_mac_sector \
			file://bootup"


do_install () {
#
# Create directories and install device independent scripts
#
	install -d ${D}${sysconfdir}/init.d \
		   ${D}${sysconfdir}/rcS.d \
		   ${D}${sysconfdir}/rc0.d \
		   ${D}${sysconfdir}/rc1.d \
		   ${D}${sysconfdir}/rc2.d \
		   ${D}${sysconfdir}/rc3.d \
		   ${D}${sysconfdir}/rc4.d \
		   ${D}${sysconfdir}/rc5.d \
		   ${D}${sysconfdir}/rc6.d \
		   ${D}/usr/bin \
		   ${D}${sysconfdir}/default

	install -m 0755    ${WORKDIR}/halt		${D}${sysconfdir}/init.d
	install -m 0755    ${WORKDIR}/turnoff_power	${D}/usr/bin
	install -m 0755    ${WORKDIR}/hotplug_br	${D}/usr/bin
	install -m 0755    ${WORKDIR}/make_mac_sector	${D}/usr/bin
	install -m 0755    ${WORKDIR}/reboot		${D}${sysconfdir}/init.d
	install -m 0755    ${WORKDIR}/rmnologin	${D}${sysconfdir}/init.d
	install -m 0755    ${WORKDIR}/sendsigs		${D}${sysconfdir}/init.d
	install -m 0755    ${WORKDIR}/single		${D}${sysconfdir}/init.d
	install -m 0755    ${WORKDIR}/bootup  ${D}${sysconfdir}/init.d
	install -m 0755    ${WORKDIR}/devpts.sh ${D}${sysconfdir}/init.d
	install -m 0755    ${WORKDIR}/devpts            ${D}${sysconfdir}/default
	install -m 0755    ${WORKDIR}/sysfs.sh          ${D}${sysconfdir}/init.d
	install -m 0755    ${WORKDIR}/var.tar.gz.default ${D}${sysconfdir}/var.tar.gz

#
# Install device dependent scripts
#
	install -m 0755 ${WORKDIR}/umountfs	${D}${sysconfdir}/init.d/umountfs
	install -d ${D}${sysconfdir}/network/if-up.d
	install -m 0755 ${WORKDIR}/netmount.sh	${D}${sysconfdir}/network/if-up.d/02netmount
	install -d ${D}${sysconfdir}/network/if-down.d
	install -m 0755 ${WORKDIR}/umountnfs.sh	${D}${sysconfdir}/network/if-down.d/02umountnfs

	echo "[ -f /etc/shadow ] && /bin/true || touch /etc/shadow" >> ${D}${sysconfdir}/init.d/bootup

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
	ln -sf          	../init.d/sysfs.sh      ${D}${sysconfdir}/rcS.d/S02sysfs
	ln -sf          	../init.d/devpts.sh     ${D}${sysconfdir}/rcS.d/S38devpts.sh
	ln -sf          	../init.d/bootup        ${D}${sysconfdir}/rcS.d/S05bootup

}

