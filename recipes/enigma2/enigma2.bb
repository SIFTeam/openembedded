DESCRIPTION = "Enigma2 is an experimental, but useful framebuffer-based frontend for DVB functions"
MAINTAINER = "Felix Domke <tmbinc@elitedvb.net>"
DEPENDS = "jpeg libungif libmad libpng libsigc++-1.2 gettext-native \
	dreambox-dvbincludes freetype libdvbsi++ python python-twisted swig-native  \
	dvd+rw-tools dvdauthor mjpegtools cdrkit python-imaging replex \
	libfribidi libxmlccwrap libdreamdvd libdvdcss tuxtxt-enigma2 ethtool \
	gstreamer gst-plugins-bad gst-plugins-good gst-plugins-ugly"
RDEPENDS_${PN} = "python-codecs python-core python-lang python-re python-threading \
	python-xml python-fcntl gst-plugin-decodebin gst-plugin-decodebin2 python-stringold \
	python-pickle gst-plugin-app \
	gst-plugin-id3demux gst-plugin-mad gst-plugin-ogg gst-plugin-playbin \
	gst-plugin-typefindfunctions gst-plugin-audioconvert gst-plugin-audioresample \
	gst-plugin-wavparse python-netclient gst-plugin-mpegstream gst-plugin-selector \
	gst-plugin-flac gst-plugin-mpegdemux \
	gst-plugin-neonhttpsrc gst-plugin-mpegaudioparse gst-plugin-subparse \
	gst-plugin-apetag gst-plugin-icydemux gst-plugin-autodetect \
	python-twisted-core python-elementtree \
	glibc-gconv-iso8859-15 ethtool"

RDEPENDS_${PN} += "${@base_contains("MACHINE_FEATURES", "blindscan", "virtual/blindscanutils" , "", d)}"

# Magic is the default skin, so we should depend on it.
DEPENDS += "enigma2-plugin-skins-magic"
RDEPENDS_${PN} += "enigma2-plugin-skins-magic"

# We depend on the font which we use for TXT subtitles (defined in skin_subtitles.xml)
DEPENDS += "font-valis-enigma"
RDEPENDS_${PN} += "font-valis-enigma"

RDEPENDS_enigma2_append_dm7020 = " gst-plugin-ossaudio gst-plugin-ivorbisdec"
RDEPENDS_enigma2_append_dm7025 = " gst-plugin-alsa alsa-conf gst-plugin-ivorbisdec"
RDEPENDS_enigma2_append_dm8000 = " gst-plugin-alsa alsa-conf gst-plugin-avi gst-plugin-matroska \
	gst-plugin-qtdemux gst-plugin-cdxaparse gst-plugin-cdio gst-plugin-vcdsrc gst-plugin-vorbis"
RDEPENDS_enigma2_append_dm800 = " gst-plugin-alsa alsa-conf gst-plugin-matroska gst-plugin-qtdemux gst-plugin-ivorbisdec"
RDEPENDS_enigma2_append_dm500hd = " gst-plugin-alsa alsa-conf gst-plugin-avi gst-plugin-matroska \
	gst-plugin-qtdemux gst-plugin-cdxaparse gst-plugin-cdio gst-plugin-vcdsrc gst-plugin-vorbis"
RDEPENDS_enigma2_append_vuduo = " gst-plugin-alsa alsa-conf gst-plugin-avi gst-plugin-matroska \
	gst-plugin-qtdemux gst-plugin-cdxaparse gst-plugin-cdio gst-plugin-vcdsrc gst-plugin-vorbis"

RRECOMMENDS_enigma2 ?= ""
# These should be able to downmix DTS
# Put in recommends it's not required but should be added on update
RRECOMMENDS_enigma2_append_dm8000 = " gst-plugin-dtsdec"
RRECOMMENDS_enigma2_append_dm500hd = " gst-plugin-dtsdec"
RRECOMMENDS_enigma2_append_vuduo = " gst-plugin-dtsdec"

# 'forward depends' - no two providers can have the same PACKAGES_DYNAMIC, however both
# enigma2 and enigma2-plugins produce enigma2-plugin-*.
#DEPENDS += "enigma2-plugins"
#PACKAGES_DYNAMIC = "enigma2-plugin-*"

DESCRIPTION_append_enigma2-plugin-extensions-cutlisteditor = "enables you to cut your movies."
RDEPENDS_enigma2-plugin-extensions-cutlisteditor = "aio-grab"
DESCRIPTION_append_enigma2-plugin-extensions-graphmultiepg = "shows a graphical timeline EPG."
DESCRIPTION_append_enigma2-plugin-extensions-pictureplayer = "displays photos on the TV."
DESCRIPTION_append_enigma2-plugin-systemplugins-frontprocessorupdate = "keeps your frontprocessor up to date."
DESCRIPTION_append_enigma2-plugin-systemplugins-positionersetup = "helps you installing a motorized dish."
DESCRIPTION_append_enigma2-plugin-systemplugins-satelliteequipmentcontrol = "allows you to fine-tune DiSEqC-settings."
DESCRIPTION_append_enigma2-plugin-systemplugins-satfinder = "helps you to align your dish."
DESCRIPTION_append_enigma2-plugin-systemplugins-skinselector = "shows a menu with selectable skins."
DESCRIPTION_append_enigma2-plugin-systemplugins-videomode = "selects advanced video modes"
RDEPENDS_enigma2-plugin-extensions-dvdplayer = "libdreamdvd"
RRECOMMENDS_enigma2-plugin-extensions-dvdplayer = "libdvdcss"
RDEPENDS_enigma2-plugin-systemplugins-nfiflash = "python-twisted-web"
RDEPENDS_enigma2-plugin-systemplugins-softwaremanager = "python-twisted-web"
DESCRIPTION_append_enigma2-plugin-systemplugins-crashlogautosubmit = "automatically send crashlogs to Dream Multimedia"
RDEPENDS_enigma2-plugin-systemplugins-crashlogautosubmit = "python-twisted-mail python-twisted-names python-compression python-mime python-email"
DESCRIPTION_append_enigma2-plugin-systemplugins-cleanupwizard = "informs you on low internal memory on system startup."
DESCRIPTION_append_enigma2-plugin-extensions-modem = "opens a menu to connect to internet via builtin modem."
RDEPENDS_enigma2-plugin-extensions-modem = "dreambox-modem-ppp-scripts"
DESCRIPTION_append_enigma2-plugin-systemplugins-wirelesslan = "helps you configuring your wireless lan"
RDEPENDS_enigma2-plugin-systemplugins-wirelesslan = "wpa-supplicant wireless-tools python-wifi"
DESCRIPTION_append_enigma2-plugin-systemplugins-networkwizard = "provides easy step by step network configuration"
RDEPENDS_enigma2-plugin-extensions-dvdburn = "dvd+rw-tools dvdauthor mjpegtools cdrkit python-imaging replex"

inherit gitpkgv

PV = "2.7+git${SRCPV}"
PKGV = "2.7+git${GITPKGV}"
PR = "r22"

SRC_URI = "git://openpli.git.sourceforge.net/gitroot/openpli/enigma2;protocol=git \
           file://enigma2.sh"

S = "${WORKDIR}/git"

FILES_${PN} += "${datadir}/fonts ${datadir}/keymaps"
FILES_${PN}-meta = "${datadir}/meta"
PACKAGES += "${PN}-meta"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit autotools pkgconfig

bindir = "/usr/bin"
sbindir = "/usr/sbin"

EXTRA_OECONF = "--enable-maintainer-mode --with-target=native --with-libsdl=no --with-boxtype=${MACHINE}"

EXTRA_OECONF += "${@base_contains("MACHINE_FEATURES", "textlcd", "--with-textlcd" , "", d)}"

# Swig generated 200k enigma.py file has no purpose for end users, nor the unused .pyc files.
FILES_${PN}-dbg += "/usr/lib/enigma2/python/enigma.py /usr/lib/enigma2/python/*.pyc /usr/lib/enigma2/python/*/*.pyc /usr/lib/enigma2/python/*/*/*.pyc /usr/lib/enigma2/python/*/*/*/*.pyc"
# Save some space on the 7025
FILE_${PN}-dbg_append_dm7025 = "/usr/lib/enigma2/python/*/*.py /usr/lib/enigma2/python/*/*/*.py /usr/lib/enigma2/python/*/*/*/*.py"

RADIOMVI = "${@base_contains("MACHINE_FEATURES", "hdtv", "radio-hd.mvi" , "radio.mvi", d)}"

SRC_URI += " \
	file://${RADIOMVI} \
	"

do_openpli_preinstall() {
	install -m 0644 ${WORKDIR}/${RADIOMVI} ${S}/data/radio.mvi
	install -d ${D}${sysconfdir}/enigma2
}

addtask openpli_preinstall after do_compile before do_install

do_install_append() {
	install -d ${D}/usr/share/keymaps
	install -d ${D}/usr/bin
	install -m 0755 ${WORKDIR}/enigma2.sh ${D}/usr/bin/enigma.sh
}

python populate_packages_prepend () {
	enigma2_plugindir = bb.data.expand('${libdir}/enigma2/python/Plugins', d)

	do_split_packages(d, enigma2_plugindir, '(.*?/.*?)/.*', 'enigma2-plugin-%s', '%s ', recursive=True, match_path=True, prepend=True, extra_depends="enigma2")
}

do_stage() {
	install -d ${STAGING_INCDIR}/enigma2
	install -m 0644 ${S}/include/*.h ${STAGING_INCDIR}/enigma2
	for dir in actions base components driver dvb dvb/lowlevel dvb_ci gdi gui mmi nav python service network; do
		install -d ${STAGING_INCDIR}/enigma2/lib/$dir;
		install -m 0644 ${S}/lib/$dir/*.h ${STAGING_INCDIR}/enigma2/lib/$dir;
	done
}
