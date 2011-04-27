PV = "1.0"
PR = "r2"

SRC_URI = "\
  file://staticdevices.tar.gz.install \
"

do_install() {
    install -d ${D}${sysconfdir}/mdev
    install -m 0755 ${WORKDIR}/staticdevices.tar.gz.install ${D}${sysconfdir}/mdev/staticdevices.tar.gz
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

