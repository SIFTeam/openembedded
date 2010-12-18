#! /bin/sh
#
# umountnfs.sh	Unmount all network filesystems.
#

PATH=/sbin:/bin:/usr/sbin:/usr/bin

# Write a reboot record to /var/log/wtmp before unmounting
halt -w

echo "Unmounting remote filesystems..."

umount -a -f -t nfs,smbfs,cifs,ncpfs
