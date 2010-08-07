PARALLEL_MAKE = ""

require unionfs-modules.inc

PR = "r1"

KERNEL_MAJMIN = "${@base_read_file('${STAGING_KERNEL_DIR}/kernel-abiversion')[:3]}"

do_compile() {
	unset LDFLAGS
	oe_runmake unionfs.ko
#${KERNEL_MAJMIN}
}
