#!/bin/sh

# Parse hdparm -I output for a hd device, and determine the best DMA
# or PIO mode based on the output
d=/dev/$1
par=`hdparm -I $d`
dma=`echo "${par}" | grep 'DMA:'`
test ! -r /etc/default/dma_mode.$1 || . /etc/default/dma_mode.$1
if echo -n "${dma}" | grep -q '[mu]dma'
then
	# DMA mode is just weird, see http://linux-sxs.org/housekeeping/hdptune.html
	echo "Enable DMA for $d" 
	hdparm -d1 $d
	if echo -n "${dma}" | grep -q 'udma2'
	then
		hdparm -X66 $d
	elif echo -n "${dma}" | grep -q 'udma1'
	then
		hdparm -X65 $d
	elif echo -n "${dma}" | grep -q 'mdma2'
	then
		hdparm -X34 $d
	elif echo -n "${dma}" | grep -q 'mdma1'
	then
		hdparm -X33 $d
	elif echo -n "${dma}" | grep -q 'mdma0'
	then
		hdparm -X32 $d
	fi
else
	# extract highest PIO digit from the PIO list
	pio=`echo -n "${par}" | grep 'PIO:' | sed -e 's/ //g' | sed -e "s/^.*\(.\)$/\1/"`
	if [ ! -z $pio ]
	then
		hdparm -p${pio} $d
	fi
fi
