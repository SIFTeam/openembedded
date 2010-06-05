#! /bin/sh
# This script is run once when your box boots for the first time.
# It installs the things from /hdd/backup/autoinstall

if [ -f /hdd/backup/autoinstall ]
then
	ipkg-cl update  
	for package in `cat /hdd/backup/autoinstall`
	do
		ipkg-cl install -force-defaults $package
	done
fi

# suicide...
rm -f /etc/rcS.d/S*autoinstall
