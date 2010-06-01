DESCRIPTION = "Skins for Enigma2"
MAINTAINER = "Felix Domke <tmbinc@elitedvb.net>"

PACKAGES_DYNAMIC = "enigma2-plugin-skins-*"

# if you want the 2.7 release, use
#TAG = ";tag=enigma2-skins_rel27"
#PV = "2.7cvs${SRCDATE}"

# if you want experimental, use:
TAG = ""
PV = "experimental-cvs${SRCDATE}"
PR = "r1"

SRC_URI = "cvs://anonymous@cvs.schwerkraft.elitedvb.net/cvsroot/enigma2-skins;module=enigma2-skins;method=pserver${TAG};date=${SRCDATE}"

#include examples of openpli widgets
SRC_URI_append = " \
	file://dtvhd.diff \
	file://brushedaluhd.diff \
	file://blackbox.diff \
	"

FILES_${PN} += " /usr/share/enigma2 /usr/share/fonts "
FILES_${PN}-meta = "${datadir}/meta"
PACKAGES += "${PN}-meta"

inherit autotools

S = "${WORKDIR}/enigma2-skins"

python populate_packages_prepend () {
	if bb.data.expand('${REL_MINOR}', d) != "4":
		enigma2_skindir = bb.data.expand('${datadir}/enigma2', d)
		do_split_packages(d, enigma2_skindir, '(.*?)/.*', 'enigma2-plugin-skins-%s', 'Enigma2 Skin: %s', recursive=True, match_path=True, prepend=True)
}
