require mtd-utils.inc

PR_append = ".1"

DEPENDS += "util-linux-ng lzo2"

PARALLEL_MAKE = ""
ARM_INSTRUCTION_SET = "arm"

# This is the default package, thus we lock to a specific git version so 
# upstream changes will not break builds.

TAG = "v${PV}"

SRC_URI = "git://git.infradead.org/mtd-utils.git;protocol=git;tag=${TAG} \
	   file://add-exclusion-to-mkfs-jffs2-git-2.patch \
	   file://fix_heap_corruption.patch \
	   file://0001-mkfs.ubifs-allow-output-file-creation-on-different-device.patch \
	  "

S = "${WORKDIR}/git/"
