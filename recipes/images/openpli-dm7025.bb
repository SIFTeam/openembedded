require openpli-image.bb

# Override to install LESS (we don't have enough flash)
IMAGE_INSTALL = " \
	task-base \
	netkit-base \
	vsftpd \
	avahi-daemon \
	fakelocale \
	timezones-alternative \
	${BOOTLOGO} \
	tuxbox-links \
	early-configure \
	samba \
	"

# Just enough to get enigma2 running
IMAGE_INSTALL += " \
	mdev-static-devices \
	enigma2 \
	tuxbox-common \
	libavahi-client \
	settings-autorestore \
	enigma2-plugin-softcams-cccam \
	"

# Build script for the 7025 so I don't keep on having to change this
# in dm7025.conf and wait for bitbake to chew on all recipes again

IMAGE_CMD_jffs2 = "\
	mkfs.jffs2 \
		--root=${IMAGE_ROOTFS}/boot \
		--faketime \
		--disable-compressor=lzo \
		--compression-mode=size \
		--output=${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.boot.jffs2 \
		${EXTRA_IMAGECMD}; \
	rm -rf ${IMAGE_ROOTFS}/boot/*; \
	mkfs.jffs2 \
		--root=${IMAGE_ROOTFS} \
		--faketime \
		${KERNEL_EXTRA_CMD} \
		--compression-mode=size \
		--output=${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.jffs2 \
		${EXTRA_IMAGECMD}; \
	buildimage ${STAGING_LIBDIR}/dreambox-secondstage/main.bin.gz \
		${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.boot.jffs2 \
		${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.jffs2 \
		${MACHINE} \
		> ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.nfi"



