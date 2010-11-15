#! /bin/sh
# This script is run once when your box boots for the first time.
# It installs the things from /hdd/backup/autoinstall
# or from wherever the settings were restored

BACKUPDIR=/hdd/backup
if [ -f /tmp/restoredfrom ]
then
	BD=`cat /tmp/restoredfrom`
	[ -f ${BD}/autoinstall ] && BACKUPDIR=${BD}
fi

if [ -x /usr/bin/opkg ]
then
	IPKG=/usr/bin/opkg
	OPTS=
else
	IPKG=ipkg
	OPTS=-force-defaults
fi

if [ -f ${BACKUPDIR}/autoinstall ]
then
	echo "autoinstall from: ${BACKUPDIR}"
	${IPKG} update  
	for package in `cat ${BACKUPDIR}/autoinstall`
	do
		${IPKG} install ${OPTS} $package
	done
fi

# suicide...
rm -f /etc/rcS.d/S*autoinstall
