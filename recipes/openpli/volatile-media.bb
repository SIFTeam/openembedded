DESCRIPTION = "Mounts and populates a tmpfs over /media"
LICENSE = "GPL"

PV="1.1"

SRC_URI = "file://*"

PACKAGES = "${PN}"

FILES_${PN} = "/etc"

do_compile() {
}

do_install() {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/volatile-media.sh ${D}${sysconfdir}/init.d
	install -d ${D}${sysconfdir}/rcS.d
	ln -sf ../init.d/volatile-media.sh ${D}${sysconfdir}/rcS.d/S03volatile-media.sh
}
