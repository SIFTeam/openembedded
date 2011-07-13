require openpli-image.bb

ENIGMA2_PLUGINS = " \
				enigma2-plugin-pli-ppanel \
				enigma2-plugin-pli-softcamsetup \
				enigma2-plugin-extensions-audiosync \
				enigma2-plugin-systemplugins-softwaremanager \
				enigma2-plugin-systemplugins-positionersetup \
				enigma2-plugin-extensions-cutlisteditor \
				enigma2-plugin-systemplugins-satfinder \
				enigma2-plugin-systemplugins-videotune \
				enigma2-plugin-extensions-mediascanner \
				enigma2-plugin-extensions-webinterface \
				enigma2-plugin-extensions-graphmultiepg \
				enigma2-plugin-systemplugins-skinselector \
				enigma2-plugin-extensions-pictureplayer \
				enigma2-plugin-extensions-mediaplayer \
				enigma2-plugin-systemplugins-networkbrowser \
				enigma2-plugin-systemplugins-fastscan \
				enigma2-plugin-systemplugins-osdpositionsetup \
				${@base_contains("MACHINE_FEATURES", "nohotplug", "", "enigma2-plugin-systemplugins-hotplug", d)} \
				${@base_contains("MACHINE_FEATURES", "dvb-c", "enigma2-plugin-systemplugins-cablescan" , "", d)} \
				${@base_contains("MACHINE_FEATURES", "frontprocessor", "enigma2-plugin-systemplugins-frontprocessorupgrade" , "", d)} \
				${@base_contains("MACHINE_FEATURES", "hdtv", "enigma2-plugin-systemplugins-videomode" , "", d)} \
				${@base_contains("MACHINE_FEATURES", "dvbapi5", "usbtunerhelper" , "", d)} \
				${@base_contains("MACHINE_FEATURES", "3dtv", "enigma2-plugin-systemplugins-osd3dsetup" , "", d)} \
				${@base_contains("MACHINE_FEATURES", "wifi", "enigma2-plugin-systemplugins-wirelesslan rtl871x", "", d)} \
				${@base_contains("MACHINE_FEATURES", "wifiusblegacy", "rt73 rt3070", "", d)} \
				"

DEPENDS += "enigma2 enigma2-plugins enigma2-pliplugins"

ENIGMA2_OPTIONAL = " \
				enigma2-skins \
				enigma2-plugins \
				enigma2-pliplugins \
				enigma2-plugin-extensions-tuxcom \
				enigma2-plugin-others-nano \
				enigma2-plugin-extensions-tuxterm \
				${@base_contains("MACHINE_FEATURES", "usbhost", "enigma2-plugin-drivers-usbserial" , "", d)} \
				${@base_contains("MACHINE_FEATURES", "dvbapi5", "enigma2-plugin-drivers-dvb-usb-dib0700 enigma2-plugin-drivers-dvb-usb-af9015 enigma2-plugin-drivers-dvb-usb-siano" , "", d)} \
				enigma2-plugin-security-firewall \
				enigma2-plugin-extensions-xmltvimport \
				enigma2-plugin-extensions-ambx \
				enigma2-plugin-extensions-project-valerie \
				channelsettings-enigma2-meta \
				picons-enigma2-meta \
				softcams-enigma2-meta \
				dvbsnoop \
				"

IMAGE_INSTALL += " \
				enigma2 \
				${ENIGMA2_PLUGINS} \
				enigma2-streamproxy \
				aio-grab \
				tuxbox-common \
				libavahi-client \
				settings-autorestore \
				"

OPTIONAL_PACKAGES += " \
			${ENIGMA2_OPTIONAL} \
			"

export IMAGE_BASENAME = "openpli-enigma2"
