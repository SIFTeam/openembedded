
IMAGE_PREPROCESS_COMMAND = "create_etc_timestamp"

BOOTLOGO ?= "openpli-bootlogo"

IMAGE_INSTALL = " \
				task-base \
				netkit-base \
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
			wakelan \
			inadyn-mt \
			cups \
			vim joe \
			mc \
			libdca \
			dvd+rw-tools dvdauthor cdrkit \
			"

export IMAGE_BASENAME = "openpli"
IMAGE_LINGUAS = ""

inherit image

