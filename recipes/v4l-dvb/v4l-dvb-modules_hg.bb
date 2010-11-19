require v4l-dvb-modules.inc

SRCDATE = "20100904"
SRCREV = "6e0befab696a"
PV = "0.0+hg${SRCDATE}"
PR = "r1"

SRC_URI = "http://linuxtv.org/hg/v4l-dvb/archive/${SRCREV}.tar.gz \
           file://defconfig \
           file://v4l-dvb-compat.patch;patch=1 \
           file://v4l-2.6.18-compat.patch;patch=1 \
           file://fix-blocking-demux.patch;patch=1 \
           file://basic-dvb-t2-support.patch;patch=1 \
           file://localversion.patch;patch=1 \
           file://fix-strip.patch;patch=1 \
           file://build-fix.patch;patch=1 \
"

S = "${WORKDIR}/v4l-dvb-${SRCREV}"
