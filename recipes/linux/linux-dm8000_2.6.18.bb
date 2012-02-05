require linux-opendreambox-2.6.18.inc

MACHINE_KERNEL_PR_append = "${PR_INC}.4"

RDEPENDS_${PN} += "dreambox-secondstage (>=83)"

SRC_URI += "\
	file://linux-2.6.18-disable-unneeded-uarts.patch \
	file://linux-2.6.18-use-full-flash.patch;patch=1 \
"

pkg_preinst_kernel-image_append_dm8000 () {
	if [ -z "$D" ]; then
		# /boot has been mounted already by pkg_preinst_kernel-image
		if grep -q '^mtd3: 03c00000 ' /proc/mtd && ! grep -q '^legacy_flash_mapping=' /boot/secondstage.conf; then
			echo "enable legacy flash mapping in secondstage.conf";
			echo "legacy_flash_mapping=1;" >> /boot/secondstage.conf;
		fi;
	fi;
	true
}
