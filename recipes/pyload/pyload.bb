DESCRIPTION = "pyLoad is a fast, lightweight and full featured download manager for many One-Click-Hoster"
HOMEPAGE = "http://pyload.org/"
RDEPENDS = "python-subprocess \
  python-pprint \
  python-pycurl \
  python-unixadmin \
  python-email \
  python-db \
  python-pycrypto \
  python-imaging \
  tesseract \
"

SRCDATE = "20110528"
PV = "hg${SRCDATE}"
SRCREV = "47eb7385846b"
PR = "r3"

SRC_URI = "hg://bitbucket.org/spoob/;proto=http;module=pyload;rev=${SRCREV} \
  file://pyload.init \
  file://pyload.tar.gz.defaults"

S = "${WORKDIR}/pyload"

FILES_${PN} = "/usr/pyload/* /etc/*"

do_install() {
	install -d ${D}/etc/init.d
	install -d ${D}/etc/rc3.d
	install -d ${D}/etc/rc6.d
	install -d ${D}/etc/rc0.d
	install -d ${D}/usr/pyload

	cp -r ${S}/icons ${D}/usr/pyload
	cp -r ${S}/locale ${D}/usr/pyload
	cp -r ${S}/module ${D}/usr/pyload
	cp -r ${S}/scripts ${D}/usr/pyload
	cp ${S}/pyLoadCli.py ${D}/usr/pyload
	cp ${S}/pyLoadCore.py ${D}/usr/pyload
	cp ${S}/systemCheck.py ${D}/usr/pyload
	cp ${WORKDIR}/pyload.tar.gz.defaults ${D}/usr/pyload/pyload-defaults.tar.gz
	
	chmod 0755 ${D}/usr/pyload/pyLoadCli.py
	chmod 0755 ${D}/usr/pyload/pyLoadCore.py
	chmod 0755 ${D}/usr/pyload/systemCheck.py
	
	install -m 0755 ${WORKDIR}/pyload.init ${D}/etc/init.d/pyload
	ln -s /etc/init.d/pyload ${D}/etc/rc3.d/S30pyload
	ln -s /etc/init.d/pyload ${D}/etc/rc6.d/K30pyload
	ln -s /etc/init.d/pyload ${D}/etc/rc0.d/K30pyload
}

pkg_postinst () {
	/etc/init.d/pyload restart || true
}
