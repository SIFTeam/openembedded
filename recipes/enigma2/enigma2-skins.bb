DESCRIPTION = "Skins for Enigma2"
MAINTAINER = "Felix Domke <tmbinc@elitedvb.net>"
RDEPENDS = ""
PACKAGES = "${PN}-meta ${PN}"
PACKAGES_DYNAMIC = "enigma2-plugin-skins-*"

inherit gitpkgv

PV = "experimental-git${SRCPV}"
PKGV = "experimental-git${GITPKGV}"
PR = "r1"
BRANCH = "master"

SRC_URI = "git://schwerkraft.elitedvb.net/enigma2-skins/enigma2-skins.git;protocol=git;branch=${BRANCH}"

#include examples of openpli widgets
SRC_URI_append = " \
	file://dtvhd.diff \
	file://brushedaluhd.diff \
	file://blackbox.diff \
	"

# note that enigma2-skins is just an empty package to satisfy silly dependencies.
ALLOW_EMPTY = "1"
FILES_${PN} = "/usr/share/enigma2 /usr/share/fonts"
FILES_${PN}-meta = "${datadir}/meta"
RDEPENDS_${PN}-meta = ""

inherit autotools

S = "${WORKDIR}/git"

python populate_packages_prepend () {
	if bb.data.expand('${REL_MINOR}', d) != "4":
		enigma2_skindir = bb.data.expand('${datadir}/enigma2', d)
		do_split_packages(d, enigma2_skindir, '(.*?)/.*', 'enigma2-plugin-skins-%s', 'Enigma2 Skin: %s', recursive=True, match_path=True, prepend=True)
}
