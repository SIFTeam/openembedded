DESCRIPTION = "Configuration files for 3rd-party feeds"
PR = "r1"

do_compile() {
    mkdir -p ${S}/${sysconfdir}/opkg
    echo "src/gz 3rd-party-feed http://extra.packages.sifteam.eu/openee/koala" > ${S}/${sysconfdir}/opkg/3rd-party-feed.conf
}

do_install () {
        install -d ${D}${sysconfdir}/opkg
        install -m 0644 ${S}/${sysconfdir}/opkg/* ${D}${sysconfdir}/opkg/
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

CONFFILES_${PN} += "${sysconfdir}/opkg/3rd-party-feed.conf"
