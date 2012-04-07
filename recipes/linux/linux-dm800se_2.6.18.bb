require linux-opendreambox-2.6.18.inc

MACHINE_KERNEL_PR_append = "${PR_INC}.2"

SRC_URI += "\
	file://linux-2.6.18-fix-serial.patch \
	file://linux-2.6.18-swap-sata-ports.patch \
"
