require linux-opendreambox-2.6.18.inc

#Don't use PR here, use MACHINE_KERNEL_PR in machine.conf instead
#PR="${PR_INC}.0"

SRC_URI += "\
	file://linux-2.6.18-fix-serial.patch \
	file://linux-2.6.18-several-nand-flash-fixes.patch \
"
