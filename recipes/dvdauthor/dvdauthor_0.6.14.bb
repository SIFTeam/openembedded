DESCRIPTION = "A set of tools to help you generate DVD files to be played back on a standalone DVD player."
MAINTAINER = "Felix Domke <tmbinc@elitedvb.net>"
LICENSE = "GPL"
SECTION = "optional"
DEPENDS = "libdvdread libfribidi freetype"

SRC_URI = "${SOURCEFORGE_MIRROR}/dvdauthor/dvdauthor-${PV}.tar.gz \
	file://dvdauthor-fix-fribidi.patch \
	file://dvdauthor-flush-progress.patch \
	file://dvdauthor-fix-old-freetype.patch"

PR = "r1"
inherit autotools
