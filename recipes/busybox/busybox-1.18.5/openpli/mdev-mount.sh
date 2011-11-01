#!/bin/sh

notify() {
	# we don't really depend on the hotplug_e2_helper, but when it exists, call it
	if [ -x /usr/bin/hotplug_e2_helper ]; then
		/usr/bin/hotplug_e2_helper $ACTION /block/$MDEV $PHYSDEVPATH
	fi
}

case "$ACTION" in
	add|"")
		ACTION="add"
		# check if already mounted
		if grep -q "^/dev/${MDEV} " /proc/mounts ; then
			# Already mounted
			exit 0
		fi
		DEVBASE=`expr substr $MDEV 1 3`
		# check for "please don't mount it" file
		if [ -f "/dev/nomount.${DEVBASE}" ] ; then
			# blocked
			exit 0
		fi
		# check for full-disk partition
		if [ "${DEVBASE}" == "${MDEV}" ] ; then
			if [ -d /sys/block/${DEVBASE}/${DEVBASE}1 ] ; then
				# Partition detected, just tell and quit
				notify
				exit 0
			fi
			if [ ! -f /sys/block/${DEVBASE}/size ] ; then
				# No size at all
				exit 0
			fi
			if [ `cat /sys/block/${DEVBASE}/size` == 0 ] ; then
				# empty device, bail out
				exit 0
			fi
		fi
		# first allow fstab to determine the mountpoint
		# Note: we grep first, to avoid that mount complains when the device is not listed in fstab
		if ! grep -q "/dev/$MDEV\s" /etc/fstab || ! mount /dev/$MDEV
		then
			# no fstab entry, use automatic mountpoint
			REMOVABLE=`cat /sys/block/$DEVBASE/removable`
			MODEL=`cat /sys/block/$DEVBASE/device/model`
			if [ "${REMOVABLE}" -eq "0" ]; then
				# mount the first non-removable device on /media/hdd
				DEVICETYPE="hdd"
			else
				if [ "$MODEL" == "USB CF Reader   " ]; then
					DEVICETYPE="cf"
				elif [ "$MODEL" == "Compact Flash   " ]; then
					DEVICETYPE="cf"
				elif [ "$MODEL" == "USB SD Reader   " ]; then
					DEVICETYPE="mmc1"
				elif [ "$MODEL" == "USB SD  Reader  " ]; then
					DEVICETYPE="mmc1"
				elif [ "$MODEL" == "SD/MMC          " ]; then
					DEVICETYPE="mmc1"
				elif [ "$MODEL" == "USB MS Reader   " ]; then
					DEVICETYPE="mmc1"
				elif [ "$MODEL" == "SM/xD-Picture   " ]; then
					DEVICETYPE="mmc1"
				elif [ "$MODEL" == "USB SM Reader   " ]; then
					DEVICETYPE="mmc1"
				elif [ "$MODEL" == "MS/MS-Pro       " ]; then
					DEVICETYPE="mmc1"
				else
					DEVICETYPE="usb"
				fi
			fi
			if grep -q " /media/$DEVICETYPE " /proc/mounts || grep -q -w "\s/media/$DEVICETYPE\s" /etc/fstab
			then
			        # DEVICETYPE already mounted, or in fstab
				MOUNTPOINT="/media/$MDEV"
			else
				MOUNTPOINT="/media/$DEVICETYPE"
			fi
			mkdir -p $MOUNTPOINT
			mount -t auto /dev/$MDEV $MOUNTPOINT
		fi
		;;
	remove)
		umount /dev/$MDEV
		# remove automatic mountpoint or symlink (if empty)
		rmdir /media/$MDEV || rm -f /media/$MDEV
		;;
	*)
		# Unexpected keyword
		exit 1
		;;
esac

notify
