#! /bin/sh
# This script is run once when your box boots for the first time. You can run
# it again later, but that may destroy settings that you did.

# Restore files from backup dir with the most recent timestamp

BACKUPDIR=/media/hdd

for candidate in /media/cf /media/usb /media/mmc1
do
   if [ -d ${candidate}/backup ]
   then
     if [ ! -f ${BACKUPDIR}/backup/.timestamp ]
     then
     	BACKUPDIR=${candidate}
     elif [ ${candidate}/backup/.timestamp -nt ${BACKUPDIR}/backup/.timestamp ]
     then
     	BACKUPDIR=${candidate}
     fi
   fi    
done

# suicide...
rm -f /etc/rcS.d/S*settingsrestore

if  [ ! -f ${BACKUPDIR}/backup/.timestamp ]
then
    echo "No valid backup location, aborting auto-restore"
    exit 0
fi

if [ ! -f ${BACKUPDIR}/backup/PLi-AutoBackup.tar.gz ]
then
    echo "PLi-AutoBackup.tar.gz not found, attempting old backup"
    exec /etc/init.d/settings-restore.old.sh ${BACKUPDIR}
    exit 1
fi

echo "Restoring from: ${BACKUPDIR}/backup/"
tar -xzf ${BACKUPDIR}/backup/PLi-AutoBackup.tar.gz -C /

[ -f /tmp/fstab ] && echo /tmp/fstab >> /etc/fstab

[ -f /tmp/crontab ] && crontab /tmp/crontab

mergerootpwd()
{
	grep "^root:" /tmp/passwd
	grep -v "^root:" /etc/passwd
}

if [ -f ${BACKUPDIR}/tmp/passwd ]
then
	mergerootpwd > /tmp/passwds
	# QA check - we don't want a passwd file without root entry
	if grep -q "^root:" /tmp/passwds
	then
		cp /tmp/passwds /etc/passwd
	fi
	rm -f /tmp/passwds
fi

rm -f /tmp/crontab
rm -f /tmp/passwd
rm -f /tmp/fstab
