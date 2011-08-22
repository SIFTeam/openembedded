require linux-vuplus-2.6.18.inc

MACHINE_KERNEL_PR_append = ".1"

SRC_URI += "\
	file://vuuno_defconfig \
	file://linux_vuuno_nand2.patch;striplevel=1 \
        file://linux_vuuno_proc.patch;striplevel=0 \
        file://linux_vuuno_serial.patch;striplevel=0 \
	"

