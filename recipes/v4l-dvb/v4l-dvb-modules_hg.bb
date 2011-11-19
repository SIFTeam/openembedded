require v4l-dvb-modules.inc

SRCDATE = "20100904"
SRCREV = "6e0befab696a"
PV = "0.0+hg${SRCDATE}"

MACHINE_KERNEL_PR_append = ".9"

SRC_URI = "http://linuxtv.org/hg/v4l-dvb/archive/${SRCREV}.tar.gz \
           file://defconfig \
           file://v4l-dvb-compat.patch \
           file://v4l-2.6.18-compat.patch \
           file://fix-blocking-demux.patch \
           file://basic-dvb-t2-support.patch \
           file://localversion.patch \
           file://fix-strip.patch \
           file://build-fix.patch \
           file://fix-get-property.patch \
           file://technisat_airstar_telestick_2.patch \
           file://v4l-dvb-as102.patch \
           file://v4l-dvb-a867.patch \
           file://v4l-dvb-tua9001.patch \
           file://v4l-dvb-af9033.patch \
           file://v4l-dvb-af9035.patch \
           file://v4l-dvb-tda18218.patch \
           file://v4l-dvb-af9013_fix_for_tda18218_tuner.patch \
           file://v4l-dvb-af9015_fix_for_tda18218_tuner.patch \
           file://v4l-dvb-af9013_add_firmware5.1.patch \
           file://v4l-dvb-af9015_fix_for_A815M.patch \
           file://v4l-dvb-em28xx_fix.patch \
           file://v4l-dvb-af9015_add_a850red.patch \
           file://v4l-dvb-smsdvb_fix_frontend.patch \
"

S = "${WORKDIR}/v4l-dvb-${SRCREV}"
