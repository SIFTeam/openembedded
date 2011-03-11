#!/bin/sh

MOUNTPOINT="/media/$MDEV"
NOTIFYDEVNAME=$MDEV

case "$ACTION" in
	add|"")
		# Set device parameters
		DEVBASE=`expr substr $MDEV 1 3`
		ADAPTER=`expr substr $DEVBASE 1 2`
		if [ "$ADAPTER" == "hd" ] ; then
			# 7025 needs to set this on internal disks
			hdparm -d1 -X66 /dev/$DEVBASE
		fi
		# remove old mountpoint symlinks we might have for this device
		rm -f $MOUNTPOINT
		# first allow fstab to determine the mountpoint
		grep -q "/dev/$MDEV" /etc/fstab && mount /dev/$MDEV
		if [ $? -ne 0 ] ; then
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
			finddevice "mmc1"
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
		finddevice "mmc1"
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
