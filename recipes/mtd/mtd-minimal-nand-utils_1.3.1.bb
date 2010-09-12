require mtd-utils_${PV}.bb

OVERRIDES_append = ":mtd-utils"

EXTRA_OEMAKE += "TARGETS='flash_eraseall nandwrite' SUBDIRS=''"

