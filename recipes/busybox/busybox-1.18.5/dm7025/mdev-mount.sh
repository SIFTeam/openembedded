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
		# Set device parameters
		DEVBASE=`expr substr $MDEV 1 3`
		# check for "please don't mount it" file
		if [ -f "/dev/nomount.${DEVBASE}" ] ; then
			# blocked
			exit 0
		fi
		# Call hdparm for hda/hdc
		if [ `expr substr $DEVBASE 1 2` == hd ] ; then
			if [ "${DEVBASE}" == "${MDEV}" ] ; then
				if [ -x /usr/bin/enable-dma-mode.sh ] ; then
					# call the DMA mode parser for hd devices
					(sleep 10; /usr/bin/enable-dma-mode.sh ${MDEV})&
				fi
			fi
		fi
                # check if already mounted
                if grep -q "^/dev/${MDEV} " /proc/mounts ; then
                        # Already mounted
                        exit 0
                fi
		# first allow fstab to determine the mountpoint
		grep -q "/dev/$MDEV" /etc/fstab && mount /dev/$MDEV
		if [ $? -ne 0 ] ; then
			# check for full-disk partition
			if [ "${DEVBASE}" == "${MDEV}" ] ; then
				if [ -d /sys/block/${DEVBASE}/${DEVBASE}1 ] ; then
					# Partition detected, bail out!
					exit 0
				fi
			fi
			# no fstab entry, use automatic mountpoint
			if [ "$DEVBASE" == "hda" ]; then
				DEVICETYPE="hdd"
			elif [ "$DEVBASE" == "hdc" ]; then
				DEVICETYPE="cf"
			else
				DEVICETYPE="usb"
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
	*)
		# Unexpected keyword
		exit 1
		;;
esac

notify
