DESCRIPTION = "meta file for enigma2 softcam packages"

PROVIDES = "softcams"

DEPENDS = "\
	enigma2-plugin-softcams-cccam \
	enigma2-plugin-softcams-cccam209 \
	enigma2-plugin-softcams-mgcamd \
	enigma2-plugin-softcams-evocamd \
	enigma2-plugin-softcams-newcs \
	enigma2-plugin-softcams-opencam \
	enigma2-plugin-softcams-oscam \
	enigma2-plugin-softcams-rqcamd \
	enigma2-plugin-softcams-scam \
	enigma2-plugin-softcams-customcam \
	"
# Some alternatives
DEPENDS += "\
	oscam-stable \
	oscam-experimental \
	"
