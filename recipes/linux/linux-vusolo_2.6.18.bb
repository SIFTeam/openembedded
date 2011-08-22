require linux-vuplus-2.6.18.inc

MACHINE_KERNEL_PR_append = ".1"

SRC_URI += "\
	file://vusolo_defconfig \
	file://linux-vusolo_nand4.patch;striplevel=0 \
	file://linux-vusolo_info.patch;striplevel=0 \
	file://linux-vusolo_setup.patch;striplevel=0 \
	file://linux-vusolo_romblock.patch;striplevel=0 \
	file://linux-vusolo_serial.patch;striplevel=0 \
	"
