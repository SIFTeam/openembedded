#!/bin/sh

MOUNTPOINT="/media/$MDEV"
NOTIFYDEVNAME=$MDEV

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
		# remove old mountpoint symlinks we might have for this device
		rm -f $MOUNTPOINT
		# first allow fstab to determine the mountpoint
		grep -q "/dev/$MDEV" /etc/fstab && mount /dev/$MDEV
		if [ $? -ne 0 ] ; then
			# check for full-disk partition
			if [ "${DEVBASE}" == "${MDEV}" ] ; then
				if [ -x /usr/bin/enable-dma-mode.sh ] ; then
					# call the DMA mode parser for hd devices
					if [ `expr substr $DEVBASE 1 2` == hd ] ; then
						/usr/bin/enable-dma-mode.sh ${MDEV}
					fi
				fi
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
			touch /dev/mdev.$DEVICETYPE
			DEVSTATE=`cat /dev/mdev.$DEVICETYPE`
			if [ -z $DEVSTATE ]; then
				MOUNTPOINT="/media/$DEVICETYPE"
				NOTIFYDEVNAME=$DEVICETYPE
				echo $MDEV > /dev/mdev.$DEVICETYPE
			fi
			mkdir -p $MOUNTPOINT
			mount -t auto /dev/$MDEV $MOUNTPOINT
			rm -f /autofs/$MDEV
			ln -s $MOUNTPOINT /autofs/$MDEV
		else
			# we used an fstab entry, try to find out what happened
			finddevice ()
			{
				DEVICETYPE="$1"
				cat /proc/mounts | grep /media/$DEVICETYPE | grep /dev/$MDEV
				if [ $? -eq 0 ]; then
					# we've just mounted our device on /media/$DEVICETYPE
					NOTIFYDEVNAME=$DEVICETYPE
					echo $MDEV > /dev/mdev.$DEVICETYPE
				fi
			}
			finddevice "hdd"
			finddevice "usb"
			finddevice "cf"
		fi
		# we don't really depend on the hotplug_e2_helper, but when it exists, call it
		if [ -x /usr/bin/hotplug_e2_helper ]; then
			/usr/bin/hotplug_e2_helper $ACTION /block/$MDEV $PHYSDEVPATH
		fi
		;;
	remove)
		umount /dev/$MDEV
		finddevice ()
		{
			DEVICETYPE="$1"
			touch /dev/mdev.$DEVICETYPE
			DEVSTATE=`cat /dev/mdev.$DEVICETYPE`
			if [ $DEVSTATE == $MDEV ]; then
				rm /dev/mdev.$DEVICETYPE
				NOTIFYDEVNAME=$DEVICETYPE
			fi
		}
		finddevice "hdd"
		finddevice "usb"
		finddevice "cf"
		# in case we had an automatic mountpoint, remove it
		rmdir $MOUNTPOINT
		# in case it was a symlink, remove that as well
		rm -f $MOUNTPOINT
		rm -f /autofs/$MDEV
		# we don't really depend on the hotplug_e2_helper, but when it exists, call it
		if [ -x /usr/bin/hotplug_e2_helper ]; then
			/usr/bin/hotplug_e2_helper $ACTION /block/$MDEV $PHYSDEVPATH
		fi
		;;
esac
