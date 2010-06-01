# we use a fixed SRCDATE, our patches don't allow a flexible SRCDATE controlled by the distro
SRCDATE = "20090130"

require libtuxtxt.bb

PN = "libtuxtxt"
PROVIDES += "libtuxtxt-enigma2"
RPROVIDES_${PN} += "libtuxtxt-enigma2"

OVERRIDES_append = ":libtuxtxt-enigma2"

SRC_URI_append = " \
	file://32bpp.diff \
	file://resize_framebuffer.diff \
	file://allow_different_demux.diff \
	file://fastclear.diff \
	"
