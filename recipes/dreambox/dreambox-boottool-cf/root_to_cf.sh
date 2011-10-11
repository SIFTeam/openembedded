if grep "/dev/hdc" /proc/mounts | grep "ext3" | grep -q "/media/cf"
then
	echo "Found ext3 CF mounted, now copying files..."
else
	echo "Cannot continue, you did not format or mount the CF correcly."
	echo "This may also mean that you are already booting from CF."
	exit 1
fi
for f in bin etc home lib sbin tmp usr var
do
	echo "Copying: $f"
	rm -rf /media/cf/$f 
	cp -rp /$f /media/cf/
done
echo "Cleanup..."
for f in boot dev media proc sys
do 
	rm -rf /media/cf/$f
	mkdir /media/cf/$f
done
rm -rf /media/tmp/*
rm -rf /media/var/volatile/*
[ -d /media/cf/hdd ] || ln -sf media/hdd /media/cf/hdd
ln -f /boot/bin/initcf /boot/bin/init
echo "Done... reboot to boot from CF!"

