DESCRIPTION = "modutils sleep driver hack"
SECTION = "kernel/modules"
LICENSE = "GPL"

SRC_URI = " \
	file://Makefile \
	file://sleep.c \
	"

S = "${WORKDIR}"

inherit module

do_install() {
	install -d ${D}/lib/modules/${KERNEL_VERSION}/extra
	install -m 0644 ${WORKDIR}/module-sleep.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/
}
