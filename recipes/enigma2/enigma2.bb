DESCRIPTION = "Enigma2 is an experimental, but useful framebuffer-based frontend for DVB functions"
MAINTAINER = "Felix Domke <tmbinc@elitedvb.net>"
DEMUXTOOL = "${@["replex","projectx"][bb.data.getVar("TARGET_FPU",d,1) == 'hard']}"
DEPENDS = "jpeg libungif libmad libpng libsigc++-1.2 gettext-native \
	dreambox-dvbincludes freetype libdvbsi++ python python-twisted swig-native  \
	dvd+rw-tools dvdauthor mjpegtools cdrkit python-imaging ${DEMUXTOOL} \
	libfribidi libxmlccwrap libdreamdvd libdvdcss tuxtxt-enigma2 ethtool \
	gstreamer gst-plugins-bad gst-plugins-good gst-plugins-ugly \
	python-wifi hotplug-e2-helper"
RDEPENDS_${PN} = "python-codecs python-core python-lang python-re python-threading \
	python-xml python-fcntl gst-plugin-decodebin gst-plugin-decodebin2 python-stringold \
	python-pickle gst-plugin-app \
	gst-plugin-id3demux gst-plugin-mad gst-plugin-ogg gst-plugin-playbin \
	gst-plugin-typefindfunctions gst-plugin-audioconvert gst-plugin-audioresample \
	gst-plugin-wavparse python-netclient gst-plugin-mpegstream \
	gst-plugin-flac gst-plugin-mpegdemux gst-plugin-dvdsub \
	gst-plugin-souphttpsrc gst-plugin-mpegaudioparse gst-plugin-subparse \
	gst-plugin-apetag gst-plugin-icydemux gst-plugin-autodetect gst-plugin-audioparsersbad \
	python-twisted-core python-elementtree \
	python-utf8-hack \
	enigma2-fonts \
	glibc-gconv-iso8859-15 ethtool"

# DVD playback is integrated, we need the libraries
RDEPENDS_${PN} += "libdreamdvd"
RRECOMMENDS_${PN} = "libdvdcss"

RDEPENDS_${PN} += "${@base_contains("MACHINE_FEATURES", "blindscan", "virtual/blindscanutils" , "", d)}"

# Magic is the default skin, so we should depend on it.
DEPENDS += "enigma2-plugin-skins-magic"
RDEPENDS_${PN} += "enigma2-plugin-skins-magic"

# We depend on the font which we use for TXT subtitles (defined in skin_subtitles.xml)
DEPENDS += "font-valis-enigma"
RDEPENDS_${PN} += "font-valis-enigma"

RDEPENDS_${PN} += "${@base_contains("MACHINE_FEATURES", "alsa", "gst-plugin-alsa alsa-conf", "", d)}"

# proper hdtv hardware should be able to playback these codecs (hmm, what about DVD/CD?)
# and rtsp support is not expected to be useful without h264 decoder
RDEPENDS_${PN} += "${@base_contains("MACHINE_FEATURES", "hdtv", "\
	gst-plugin-avi gst-plugin-matroska gst-plugin-qtdemux \
	gst-plugin-udp gst-plugin-rtsp gst-plugin-rtp gst-plugin-rtpmanager \
	gst-plugin-cdxaparse gst-plugin-cdio gst-plugin-vcdsrc", "", d)}"

# pick the vorbis decoder based on FPU capability
RDEPENDS_${PN} += "${@["gst-plugin-ivorbisdec","gst-plugin-vorbis"][bb.data.getVar("TARGET_FPU",d,1) == 'hard']}"

RDEPENDS_enigma2_append_dm7020 = " gst-plugin-ossaudio"

# FPU hardware should be able to downmix DTS
RRECOMMENDS_${PN} += "${@["","gst-plugin-dtsdec"][bb.data.getVar("TARGET_FPU",d,1) == 'hard']}"

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
RDEPENDS_enigma2-plugin-extensions-dvdburn = "dvd+rw-tools dvdauthor mjpegtools cdrkit python-imaging ${DEMUXTOOL}"
RDEPENDS_enigma2-plugin-systemplugins-hotplug = "hotplug-e2-helper"

inherit gitpkgv

PV = "2.7+git${SRCPV}"
PKGV = "2.7+git${GITPKGV}"
PR = "r26"

SRC_URI = "git://openpli.git.sourceforge.net/gitroot/openpli/enigma2;protocol=git"
# SRC_URI = "git://${HOME}/pli/enigma2;protocol=file"

S = "${WORKDIR}/git"

FILES_${PN} += "${datadir}/keymaps"
FILES_${PN}-meta = "${datadir}/meta"
PACKAGES =+ "${PN}-src"
PACKAGES += "${PN}-meta"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit autotools pkgconfig

# fonts: Rarely changed, but updated everytime. Put in separate package,
# so the dm7025 can keep them in squashfs. Also saves bandwidth...
PACKAGES =+ "enigma2-fonts"
PV_enigma2-fonts = "2010.11.14"
PR_enigma2-fonts = "r0"
PKGV_enigma2-fonts = "${PV_enigma2-fonts}"
FILES_enigma2-fonts = "${datadir}/fonts"

bindir = "/usr/bin"
sbindir = "/usr/sbin"

EXTRA_OECONF = "\
	--with-libsdl=no --with-boxtype=${MACHINE} \
	${@base_contains("MACHINE_FEATURES", "textlcd", "--with-textlcd" , "", d)} \
	${@base_contains("MACHINE_FEATURES", "colorlcd", "--with-colorlcd" , "", d)} \
	BUILD_SYS=${BUILD_SYS} \
	HOST_SYS=${HOST_SYS} \
	STAGING_INCDIR=${STAGING_INCDIR} \
	STAGING_LIBDIR=${STAGING_LIBDIR} \
	"

EXTRA_OECONF_append_dm7025 = " --with-oldpvr"

# Swig generated 200k enigma.py file has no purpose for end users
FILES_${PN}-dbg += "\
	/usr/lib/enigma2/python/enigma.py \
	"

# some plugins contain so's, their stripped symbols should not end up in the enigma2 package
FILES_${PN}-dbg += "\
	/usr/lib/enigma2/python/Plugins/*/*/.debug \
	"

# Save some space by not installing sources (mytest.py must remain)
FILES_${PN}-src = "\
	/usr/lib/enigma2/python/GlobalActions.py \
	/usr/lib/enigma2/python/Navigation.py \
	/usr/lib/enigma2/python/NavigationInstance.py \
	/usr/lib/enigma2/python/RecordTimer.py \
	/usr/lib/enigma2/python/ServiceReference.py \
	/usr/lib/enigma2/python/SleepTimer.py \
	/usr/lib/enigma2/python/e2reactor.py \
	/usr/lib/enigma2/python/keyids.py \
	/usr/lib/enigma2/python/keymapparser.py \
	/usr/lib/enigma2/python/skin.py \
	/usr/lib/enigma2/python/timer.py \
	/usr/lib/enigma2/python/*/*.py \
	/usr/lib/enigma2/python/*/*/*.py \
	/usr/lib/enigma2/python/*/*/*/*.py \
	"
RADIOMVI = "${@base_contains("MACHINE_FEATURES", "hdtv", "radio-hd.mvi" , "radio.mvi", d)}"

SRC_URI += " \
	file://${RADIOMVI} \
	"

RCONFLICTS_${PN} = "dreambox-keymaps"
RREPLACES_${PN} = "dreambox-keymaps"

do_openpli_preinstall() {
	install -m 0644 ${WORKDIR}/${RADIOMVI} ${S}/data/radio.mvi
	install -d ${D}${sysconfdir}/enigma2
}

addtask openpli_preinstall after do_compile before do_install

do_install_append() {
	install -d ${D}/usr/share/keymaps
	find ${D}/usr/lib/enigma2/python/ -name '*.pyc' -exec rm {} \;
}

python populate_packages_prepend () {
	enigma2_plugindir = bb.data.expand('${libdir}/enigma2/python/Plugins', d)
	do_split_packages(d, enigma2_plugindir, '(.*?/.*?)/.*', 'enigma2-plugin-%s', '%s ', recursive=True, match_path=True, prepend=True, extra_depends="enigma2")
}

