SRC_URI = "http://repository.slacky.eu/slackware-12.2/multimedia/${PN}/${PV}/src/${PN}-${PV}.tar.gz"

CFLAGS = '-D_LARGEFILE_SOURCE -D_FILE_OFFSET_BITS=64 -D_LARGEFILE64_SOURCE -DVERSION=\\"${PV}\\"'

S = "${WORKDIR}/${PN}-${PV}"

do_install() {
  install -d 0755 ${D}/${bindir}
  install -m 0755 ${S}/replex ${D}/${bindir}
}

FILES_${PN} = "${bindir}/replex"