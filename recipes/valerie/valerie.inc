SRCREV = "466"
PV = "0.2+svnr${SRCPV}"
PR = "r1"

SRC_URI = "svn://project-valerie.googlecode.com/svn/trunk;proto=http;module=${MODULE}"
S = "${WORKDIR}/${MODULE}"

PLUGINDIR = "/usr/lib/enigma2/python/Plugins/Extensions/ProjectValerie"

FILES_${PN} = "${PLUGINDIR}"
