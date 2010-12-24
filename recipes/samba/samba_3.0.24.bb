require samba.inc
require samba-basic.inc

SRC_URI += "file://configure.patch \
            file://cifs.patch \
            file://mtabsymlinkcheck.patch \
            "

PR = "r2"

EXTRA_OECONF += "samba_cv_LINUX_LFS_SUPPORT=yes samba_cv_HAVE_EXPLICIT_LARGEFILE_SUPPORT=yes"

SRC_URI[src.md5sum] = "11ed2bfef4090bd5736b194b43f67289"
SRC_URI[src.sha256sum] = "bb67c0e13d4ccbd84b9200c8739393fdd9b3145b5aad216934dc670f0fcea266"

