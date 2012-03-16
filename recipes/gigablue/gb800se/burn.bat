macprog2 AC-DB-EF-11-22-33

flasherase -all
flash -noheader usbdisk0:vmlinux.gz nandflash0.kernel

flash -noheader usbdisk0:rootfsn.img nandflash0.avail0

setenv -p STARTUP "boot -z -elf nandflash0.kernel:"

boot -z -elf nandflash0.kernel:

