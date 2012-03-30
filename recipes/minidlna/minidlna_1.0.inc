DESCRIPTION = "MiniDLNA (aka ReadyDLNA) is server software with the aim of being fully compliant with DLNA/UPnP-AV clients. \
               It is developed by a NETGEAR employee for the ReadyNAS product line. \
               So if you are looking for a NAS, please consider ReadyNAS first!"
HOMEPAGE = "http://minidlna.sourceforge.net/"
LICENSE = "GPL"
SECTION = "net"
DEPENDS = "libexif ffmpeg"

INC_PR = "r2"

SRC_URI = "http://downloads.sourceforge.net/project/minidlna/minidlna/${PV}/${PN}_${PV}_src.tar.gz file://init"

CONFFILES_${PN} = "${sysconfdir}/minidlna.conf"

INITSCRIPT_NAME = "minidlna"
INITSCRIPT_PARAMS = "defaults 90 10"

inherit update-rc.d

CFLAGS_prepend = "-D_GNU_SOURCE -D_FILE_OFFSET_BITS=64 -I${STAGING_INCDIR}/ffmpeg "

do_configure() {
    sed -i "s:/usr/include:${STAGING_INCDIR}:g" genconfig.sh
    sed -i "s/^CFLAGS =/#CFLAGS =/" Makefile
    sed -i "s/^CC =/#CC =/" Makefile
    sed -i "/^minidlna\:/ s/[$][(]LIBS[)]//" Makefile
    sed -i "s/--mode=0644//" Makefile
    sed -i "s:^media_dir=/opt:media_dir=/media:" minidlna.conf
    ./genconfig.sh
}

do_install() {
    oe_runmake 'DESTDIR=${D}' install install-conf
    install -d ${D}${sysconfdir}/init.d/
    install -m 755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/${PN}
}
