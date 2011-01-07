# we cannot use PACKAGES_DYNAMIC = "enigma2-plugin-*"  here, because enigma2-plugins already has it,
# so we only publish enigma2-plugin-pli-* here (as a result, only those can occur in any RDEPENDS)

SRCREV = "466"
PV = "0.2+svnr${SRCPV}"
PR = "r0"

SRC_URI = "svn://project-valerie.googlecode.com/svn/trunk;proto=http;module=${MODULE}"
S = "${WORKDIR}/${MODULE}"

PLUGINDIR = "/usr/lib/enigma2/python/Plugins/Extensions/ProjectValerie"

FILES_${PN} = "${PLUGINDIR}"
