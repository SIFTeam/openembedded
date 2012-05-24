require linux-vuplus-3.1.1.inc

MACHINE_KERNEL_PR_append = ".6"

SRC_URI += "\
        file://linux_3.1.1_vuduo.patch;striplevel=1 \
	file://linux-sata_brcm.patch;striplevel=1 \
	file://0001-slab-use-print_hex_dump.patch;striplevel=1 \
	file://0002-mm-restrict-access-to-slab-files-under-procfs-and-sy.patch;striplevel=1 \
	file://0003-slab-rename-slab_break_gfp_order-to-slab_max_order.patch;striplevel=1 \
	file://0004-slab-introduce-slab_max_order-kernel-parameter.patch;striplevel=1 \
	file://0005-slab-add-taint-flag-outputting-to-debug-paths.patch;striplevel=1 \
	file://0006-slab-lockdep-Fix-silly-bug.patch;striplevel=1 \
	file://0007-tracing-mm-Move-include-of-trace-events-kmem.h-out-o.patch;striplevel=1 \
	file://0008-slab-cleanup-remove-unneeded-return.patch;striplevel=1 \
	file://0009-mm-SLAB-Out-of-memory-diagnostics.patch;striplevel=1 \
	"

