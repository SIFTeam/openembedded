DESCRIPTION = "LC_TIME locale support"
SECTION = "base"
PRIORITY = "required"
MAINTAINER = "OpenPli team"
PR = "r3"

SRC_URI = "file://lctimelocales.tar.gz file://locale.alias"

S = "${WORKDIR}/locales"

LOCALEDIR = "${libdir}/locale"
LOCALEDIR2 = "/usr/share/locale"

LANGUAGES = "ar_AE bg_BG ca_AD cs_CZ da_DK de_DE el_GR en_EN es_ES et_EE fa_IR fi_FI \
        fr_FR fy_NL he_IL hr_HR hu_HU is_IS it_IT lt_LT lv_LV nl_NL no_NO pl_PL pt_PT \
        ru_RU sk_SK sl_SI sr_YU sv_SE th_TH tr_TR uk_UA"

RPROVIDES = "virtual-locale-ar virtual-locale-bg virtual-locale-ca virtual-locale-cs \
        virtual-locale-da virtual-locale-de virtual-locale-el virtual-locale-en virtual-locale-es \
        virtual-locale-et virtual-locale-fa virtual-locale-fi virtual-locale-fr virtual-locale-fy \
        virtual-locale-he virtual-locale-hr virtual-locale-hu virtual-locale-is virtual-locale-it \
        virtual-locale-lt virtual-locale-lv virtual-locale-nl virtual-locale-no virtual-locale-pl \
        virtual-locale-pt virtual-locale-ru virtual-locale-sk virtual-locale-sl \
	virtual-locale-sr virtual-locale-sv virtual-locale-th virtual-locale-tr virtual-locale-uk

#LOCALE_FILES = " LC_TELEPHONE LC_PAPER LC_NUMERIC LC_NAME LC_MONETARY LC_MESSAGES/SYS_LC_MESSAGES LC_MEASUREMENT \
#	LC_IDENTIFICATION LC_CTYPE LC_COLLATE LC_ADDRESS"

do_install() {
	install -d ${D}${LOCALEDIR2}
	install ${WORKDIR}/locale.alias ${D}${LOCALEDIR2}

	install -d ${D}${LOCALEDIR}
        cp -rp ${S}/* ${D}/${LOCALEDIR}
}

pkg_preinst_${PN} () {
	# in theory we should not need this. the upgrade process should cleanup the symlinks
	# but somehow it doesn't when there is no preinst script
	for lang in ${LANGUAGES}; do
		[ -L ${LOCALEDIR}/${lang} ] && rm ${LOCALEDIR}/${lang}
	done

	exit 0
}

FILES_${PN} = "${LOCALEDIR} ${LOCALEDIR2}"
PACKAGE_ARCH = "all"
