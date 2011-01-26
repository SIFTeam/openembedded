DESCRIPTION = "Start, stop and select softcams."
MAINTAINER = "PLi team"

PACKAGES = "${PN}"
#ALLOW_EMPTY_${PN} = "1"

PV = "1"
PR = "r1"
# SRC_URI = 'file://*'

INITSCRIPT_NAME = "softcam"
INITSCRIPT_PARAMS = "defaults 50"
inherit update-rc.d

FILES_${PN} = "/etc"

do_install() {
	install -d ${D}/etc/init.d
	install -m 755 ${S}/softcam.None ${D}/etc/init.d/softcam.None
	ln -s softcam.None ${D}/etc/init.d/softcam
}

do_compile_append() {
	echo "# Placeholder for no cam" > ${S}/softcam.None
}
