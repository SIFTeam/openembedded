#!/bin/sh

MOUNTPOINT=/media/$MDEV

case "$ACTION" in
        add|"")
                mkdir -p $MOUNTPOINT
                mount -t auto /dev/$MDEV $MOUNTPOINT
                ;;
        remove)
                umount $MOUNTPOINT
                rmdir $MOUNTPOINT
                ;;
esac
