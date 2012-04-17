SRCREV = "1260"
PV = "1.0+svnr${SRCPV}"
PR = "r0"

SRC_URI = "svn://project-valerie.googlecode.com/svn/trunk;proto=http;module=${MODULE}"
S = "${WORKDIR}/${MODULE}"

PLUGINDIR = "/usr/lib/enigma2/python/Plugins/Extensions/ProjectValerie"

FILES_${PN} = "${PLUGINDIR}"
