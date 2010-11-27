DESCRIPTION = "streamproxy manages streaming data to a PC using enigma2"
MAINTAINER = "Felix Domke <tmbinc@elitedvb.net>"

PV = "1.0cvs${SRCDATE}"
PR = "r1"

SRC_URI = "cvs://anonymous@cvs.schwerkraft.elitedvb.net/cvsroot/streamproxy;module=enigma2-streamproxy;method=pserver \
	file://streampatch.diff"

inherit autotools

S = "${WORKDIR}/enigma2-streamproxy"
