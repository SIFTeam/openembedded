require linux-opendreambox-2.6.18.inc

#Don't use PR here, use MACHINE_KERNEL_PR in machine.conf instead
#PR="${PR_INC}.0"
#or use MACHINE_KERNEL_PR_append, when a rebuild of external modules is not required

MACHINE_KERNEL_PR_append = ".1"

SRC_URI += "\
	file://linux-2.6.18-fix-serial.patch \
"
