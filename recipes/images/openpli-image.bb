
IMAGE_PREPROCESS_COMMAND = "create_etc_timestamp"

BOOTLOGO ?= "openpli-bootlogo"

IMAGE_INSTALL = " \
				task-base \
				busybox-inetd \
				vsftpd \
				util-linux-ng-sfdisk \
				avahi-daemon \
				fakelocale \
				timezones-alternative \
				${BOOTLOGO} \
				tuxbox-links \
				cifs \
				samba \
				early-configure \
				e2fsprogs-mke2fs \
				e2fsprogs-e2fsck \
				nfs-utils-client \
				kernel-params \
				sdparm \
				"

OPTIONAL_PACKAGES ?= ""
OPTIONAL_PACKAGES += " \
			openvpn \
			gdb strace \
			procps \
			tcpdump \
			ntp \
			openssh \
			ctorrent nzbget \
			transmission \
			sabnzbd \
			wakelan \
			inadyn-mt \
			cups \
			vim joe \
			mc \
			libdca \
			dvd+rw-tools dvdauthor cdrkit \
			smartmontools \
			hdparm \
			rsync \
			ntfs-3g \
			mpd \
			parted \
			dosfstools \
			"

export IMAGE_BASENAME = "openpli"
IMAGE_LINGUAS = ""

inherit image

