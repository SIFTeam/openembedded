require v4l-dvb-modules.inc

SRCDATE = "20100904"
SRCREV = "6e0befab696a"
PV = "0.0+hg${SRCDATE}"
PR = "r2"

SRC_URI = "http://linuxtv.org/hg/v4l-dvb/archive/${SRCREV}.tar.gz \
           file://defconfig \
           file://v4l-dvb-compat.patch \
           file://v4l-2.6.18-compat.patch \
           file://fix-blocking-demux.patch \
           file://basic-dvb-t2-support.patch \
           file://localversion.patch \
           file://fix-strip.patch \
           file://build-fix.patch \
"

S = "${WORKDIR}/v4l-dvb-${SRCREV}"
