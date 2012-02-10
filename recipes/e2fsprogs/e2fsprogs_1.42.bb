require e2fsprogs.inc

PR = "${INC_PR}"

SRC_URI += "file://llseek-uclibc.patch"

do_configure() {
	oe_runconf
}

SRC_URI[md5sum] = "a3c4ffd7352310ab5e9412965d575610"
SRC_URI[sha256sum] = "55b46db0cec3e2eb0e5de14494a88b01ff6c0500edf8ca8927cad6da7b5e4a46"
