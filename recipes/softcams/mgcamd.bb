DESCRIPTION = "mgcamd ${PV} softcam"
CAMNAME = "mgcamd"
RDEPENDS = "libcrypto-compat"
PN = "enigma2-plugin-softcams-mgcamd"
PV = "1.30d"
PR = "r5"

SRC_URI = "http://downloads.pli-images.org/softcams/mgcamd${PV}.zip \
	http://downloads.pli-images.org/softcams/newcamd.conf"

S = "${WORKDIR}/"

INHIBIT_PACKAGE_STRIP = "1"

CAMSTART = "sleep 3 ; start-stop-daemon -S -b -x /usr/bin/${CAMNAME}"

require softcam.inc

do_install() {
	install -d ${D}/usr/bin
	install -m 0755 ${S}/mgcamd.mips ${D}/usr/bin/mgcamd
	install -d ${D}/etc/tuxbox/config
	install -m 0644 ${S}/newcamd.list.example ${D}/etc/tuxbox/config/newcamd.list.example.mg
	install -m 0644 ${S}/newcamd.conf ${D}/etc/tuxbox/config/newcamd.conf.example.mg
	install -d ${D}/usr/keys
	install -m 0644 ${S}/mg_cfg ${D}/usr/keys/mg_cfg.example.mg
	install -m 0644 ${S}/ignore.list.example ${D}/usr/keys/ignore.list.example.mg
	install -m 0644 ${S}/priority.list.example ${D}/usr/keys/priority.list.example.mg
	install -m 0644 ${S}/replace.list.example ${D}/usr/keys/replace.list.example.mg
	install -m 0644 ${S}/peer.cfg.example ${D}/usr/keys/peer.cfg.example.mg
}

pkg_postinst () {
	[ -e $D/etc/tuxbox/config/newcamd.list ] || mv $D/etc/tuxbox/config/newcamd.list.example.mg $D/etc/tuxbox/config/newcamd.list
	[ -e $D/etc/tuxbox/config/newcamd.conf ] || mv $D/etc/tuxbox/config/newcamd.conf.example.mg $D/etc/tuxbox/config/newcamd.conf
	[ -e $D/usr/keys/mg_cfg ] || mv $D/usr/keys/mg_cfg.example.mg $D/usr/keys/mg_cfg
	[ -e $D/usr/keys/ignore.list ] || mv $D/usr/keys/ignore.list.example.mg $D/usr/keys/ignore.list
	[ -e $D/usr/keys/priority.list ] || mv $D/usr/keys/priority.list.example.mg $D/usr/keys/priority.list
	[ -e $D/usr/keys/replace.list ] || mv $D/usr/keys/replace.list.example.mg $D/usr/keys/replace.list
	[ -e $D/usr/keys/peer.cfg ] || mv $D/usr/keys/peer.cfg.example.mg $D/usr/keys/peer.cfg
}

FILES_${PN} += "/usr/keys"

