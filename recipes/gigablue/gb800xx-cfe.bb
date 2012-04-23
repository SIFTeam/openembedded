DESCRIPTION = "GigaBlue GB800XX CFE AddOn"
SECTION = "base"
PRIORITY = "required"
LICENSE = "proprietary"
MAINTAINER = "openMips"

PR = "r3"

S = "${WORKDIR}"

SRC_URI_gb800se = " file://burn.bat"
SRC_URI_gb800ue = " file://burn.bat"
SRC_URI_gb800solo = " file://burn.bat"
SRC_URI_quattro = " file://burn.bat"


do_install_append_gb800se() {
    install -d ${DEPLOY_DIR_IMAGE}
	install -m 0755 ${S}/burn.bat ${DEPLOY_DIR_IMAGE}/burn.bat
}
do_install_append_gb800ue() {
    install -d ${DEPLOY_DIR_IMAGE}
	install -m 0755 ${S}/burn.bat ${DEPLOY_DIR_IMAGE}/burn.bat
}
do_install_append_gb800solo() {
    install -d ${DEPLOY_DIR_IMAGE}
	install -m 0755 ${S}/burn.bat ${DEPLOY_DIR_IMAGE}/burn.bat
}

do_install_append_quattro() {
    install -d ${DEPLOY_DIR_IMAGE}
	install -m 0755 ${S}/burn.bat ${DEPLOY_DIR_IMAGE}/burn.bat
}
