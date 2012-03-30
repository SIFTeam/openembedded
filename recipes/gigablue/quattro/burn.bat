flash -noheader -forceerase usbdisk0:vmlinux.gz nandflash0.kernel

flash -noheader -forceerase usbdisk0:ubi.img nandflash0.rootfs

setenv -p STARTUP "boot -z -elf nandflash0.kernel: 'ubi.mtd=0 root=ubi0:rootfs rootfstype=ubifs rw bmem=128M@128M bmem=256M@512M'"

boot -z -elf nandflash0.kernel:

