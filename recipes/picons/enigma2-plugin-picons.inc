DESCRIPTION = "enigma2 picons"

PACKAGES_DYNAMIC = "enigma2-plugin-picons-*"

PACKAGE_ARCH = "all"

S = "${WORKDIR}/picons"

do_install() {
}

python populate_packages_prepend() {
	import os
	import shutil
	dir = bb.data.getVar('D', d, 1)
	os.makedirs(dir + "/picon")
	sdir = bb.data.getVar('S', d, 1)
	satnames = bb.data.getVar('PICON_SATNAMES', d, 1)
	packages = []
	for sat in os.listdir(sdir):
		def createPackageName(type, sat):
			if sat[-3] == '.':
				zeros = '000'
				pos = zeros[:6 - len(sat)] + sat[:-1]
				if sat[-1] == 'e':
					return "enigma2-plugin-picons-" + type + "-east-" + pos
				elif sat[-1] == 'w':
					return "enigma2-plugin-picons-" + type + "-west-" + pos
			return "enigma2-plugin-picons-" + type + "-" + sat
		if sat == "patches":
			continue
		if sat not in satnames:
			continue
		packagename = createPackageName('tv', sat)
		files = []
		for file in os.listdir(sdir + "/" + sat + "/tv"):
			if file[-4:] == ".png":
				os.system("cp -a \"" + sdir + "/" + sat + "/tv/" + file + "\" " + dir + "/picon/")
				files.append("/picon/" + file)
		bb.data.setVar('FILES_' + packagename, " ".join(files), d)
		bb.data.setVar('DESCRIPTION_' + packagename, "TV picon set", d)
		packages.append(packagename)
		try:
			packagename = createPackageName('radio', sat)
			files = []
			for file in os.listdir(sdir + "/" + sat + "/radio"):
				if file[-4:] == ".png":
					os.system("cp -a \"" + sdir + "/" + sat + "/radio/" + file + "\" " + dir + "/picon/")
					files.append("/picon/" + file)
			bb.data.setVar('FILES_' + packagename, " ".join(files), d)
			bb.data.setVar('DESCRIPTION_' + packagename, "Radio picon set", d)
			packages.append(packagename)
		except:
			pass

	bb.data.setVar('PACKAGES', ' '.join(packages), d)
}
