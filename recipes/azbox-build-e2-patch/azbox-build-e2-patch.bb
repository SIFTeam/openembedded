DESCRIPTION = "create Azbox e2.patch images"
SECTION = "console/utils"
MAINTAINER = "Dr_Gogeta86 <dr_gogeta86@messinalug.org>"
PV = "0.1.3"
PR = "r0"

SRC_URI = "file://e2_packer.c"

inherit native

do_compile() {
	cp ${WORKDIR}/e2_packer.c .
	${CXX} -I. -o e2_packer e2_packer.c
}

do_stage() {
	install -m 0755 e2_packer ${STAGING_BINDIR}/
}
