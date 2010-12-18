#
# mountnfs.sh	Now that TCP/IP is configured, mount the network file
#		systems in /etc/fstab if needed.
#
# Since mount is smart enough, we let it do the work.
#

. /etc/default/rcS

echo "Mounting remote filesystems..."

mount -a -t nfs,smbfs,cifs,ncpfs

