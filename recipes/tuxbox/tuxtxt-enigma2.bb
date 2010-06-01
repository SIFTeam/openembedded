DEPENDS = "freetype dreambox-dvbincludes libtuxtxt-enigma2"
DESCRIPTION = "tuxbox tuxtxt for enigma2"
MAINTAINER = "Sven Karschewski <seddi@i-have-a-dreambox.com>"

# we use a fixed SRCDATE, our patches don't allow a flexible SRCDATE controlled by the distro
SRCDATE = "20090130"

PV = "0.0+cvs${SRCDATE}"
PR = "r2"

SRC_URI = "cvs://anoncvs@cvs.tuxbox.org/cvs/tuxbox;module=apps/tuxbox/plugins/tuxtxt;method=ext \
	file://makefiles.diff \
	file://nonblocking.diff \
	file://32bpp.diff \
	file://add_new_default_conf.diff \
	file://add_advanced_rc.diff \
	file://makelib.diff \
	file://allow_different_demux.diff \
	file://ignore_unused_keys.diff \
	file://ignore_missing_dboxlcd.patch \
	file://plugin.py"

FILES_${PN} = "/usr/lib/libtuxtxt32bpp.so* /usr/share/fonts /usr/lib/enigma2/python/Plugins/Extensions/Tuxtxt /etc/tuxtxt"

S = "${WORKDIR}/tuxtxt"

CFLAGS_append = " -DHAVE_DREAMBOX_HARDWARE -DDREAMBOX"

inherit autotools pkgconfig

bindir = "/usr/bin"
sbindir = "/usr/sbin"

EXTRA_OECONF = "--with-target=native"

do_configure_prepend() {
	touch ${S}/python/__init__.py
	install -m 0644 ${WORKDIR}/plugin.py ${S}/python
}

do_stage() {
	oe_libinstall -so -C .libs libtuxtxt32bpp ${STAGING_LIBDIR}
	install -m 0644 tuxtxt.h ${STAGING_INCDIR}/
}
