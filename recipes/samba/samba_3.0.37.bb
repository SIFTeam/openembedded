require samba.inc
require samba-basic.inc

SRC_URI += "file://configure.patch \
            file://kernel-oplocks.patch \
            "
SRC_URI_append_linux-uclibc        = "file://uclibc-strlcpy-strlcat.patch;patch=1"
SRC_URI_append_linux-uclibceabi = "file://uclibc-strlcpy-strlcat.patch;patch=1"

PR = "r0"

EXTRA_OECONF += "\
	SMB_BUILD_CC_NEGATIVE_ENUM_VALUES=yes \
	"

