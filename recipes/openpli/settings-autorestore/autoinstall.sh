#! /bin/sh
# This script is run once when your box boots for the first time.
# It installs the things from /hdd/backup/autoinstall
# or from wherever the settings were restored

BACKUPDIR=/media/hdd
INSTALLED=/etc/installed

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

if [ -x /usr/bin/opkg ]
then
	IPKG=/usr/bin/opkg
else
	IPKG=ipkg
fi

${IPKG} list_installed | cut -d ' ' -f 1 > ${INSTALLED}
chmod 444 ${INSTALLED}

if [ -f ${BACKUPDIR}/backup/autoinstall ]
then
	${IPKG} update  
	for package in `cat ${BACKUPDIR}/backup/autoinstall`
	do
		packagefile=`echo ${package} | sed 's/,/ /g' | awk '{print $1}'`
		packageoption=`echo ${package} | sed 's/,/ /g' | awk '{print $2" "$3" "$4}'`
		if [ "$packageoption" == "" ] 
		then
			${IPKG} install --force-defaults ${packagefile}
		else
			${IPKG} install ${packageoption} ${packagefile}
		fi
	done
fi

# suicide...
rm -f /etc/rcS.d/S*autoinstall
