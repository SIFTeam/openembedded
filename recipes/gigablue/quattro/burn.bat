flasherase -all
flash -noheader -forceerase usbdisk0:vmlinux.gz nandflash0.kernel

flash -noheader -forceerase usbdisk0:ubi.img nandflash0.rootfs

setenv -p STARTUP "boot -z -elf nandflash0.kernel:"

boot -z -elf nandflash0.kernel:

