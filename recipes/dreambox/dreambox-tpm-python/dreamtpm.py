# TPM check module based on the TPM check demo in the enigma2 repository
from enigma import eTPM
import hashlib

def bin2long(s):
	return reduce( lambda x,y:(x<<8L)+y, map(ord, s))

def long2bin(l):
	res = ""
	for byte in range(128):
		res += chr((l >> (1024 - (byte + 1) * 8)) & 0xff)
	return res

def rsa_pub1024(src, mod):
	return long2bin(pow(bin2long(src), 65537, bin2long(mod)))
	
def decrypt_block(src, mod):
	if len(src) != 128 and len(src) != 202:
		return None
	dest = rsa_pub1024(src[:128], mod)
	hash = hashlib.sha1(dest[1:107])
	if len(src) == 202:
		hash.update(src[131:192])	
	result = hash.digest()
	if result == dest[107:127]:
		return dest
	return None

def validate_cert(cert, key):
	buf = decrypt_block(cert[8:], key) 
	if buf is None:
		return None
	return buf[36:107] + cert[139:196]

def read_random():
	try:
		fd = open("/dev/urandom", "r")
		buf = fd.read(8)
		fd.close()
		return buf
	except:
		return None


rootkey = ['\x9f', '|', '\xe4', 'G', '\xc9', '\xb4', '\xf4', '#', '&', '\xce', '\xb3', '\xfe', '\xda', '\xc9', 'U', '`', '\xd8', '\x8c', 's', 'o', '\x90', '\x9b', '\\', 'b', '\xc0', '\x89', '\xd1', '\x8c', '\x9e', 'J', 'T', '\xc5', 'X', '\xa1', '\xb8', '\x13', '5', 'E', '\x02', '\xc9', '\xb2', '\xe6', 't', '\x89', '\xde', '\xcd', '\x9d', '\x11', '\xdd', '\xc7', '\xf4', '\xe4', '\xe4', '\xbc', '\xdb', '\x9c', '\xea', '}', '\xad', '\xda', 't', 'r', '\x9b', '\xdc', '\xbc', '\x18', '3', '\xe7', '\xaf', '|', '\xae', '\x0c', '\xe3', '\xb5', '\x84', '\x8d', '\r', '\x8d', '\x9d', '2', '\xd0', '\xce', '\xd5', 'q', '\t', '\x84', 'c', '\xa8', ')', '\x99', '\xdc', '<', '"', 'x', '\xe8', '\x87', '\x8f', '\x02', ';', 'S', 'm', '\xd5', '\xf0', '\xa3', '_', '\xb7', 'T', '\t', '\xde', '\xa7', '\xf1', '\xc9', '\xae', '\x8a', '\xd7', '\xd2', '\xcf', '\xb2', '.', '\x13', '\xfb', '\xac', 'j', '\xdf', '\xb1', '\x1d', ':', '?']

class PyTPM:
	def __init__(self):
		self.passed = False
	def check(self):
		if self.passed:
			return True
		try:
			device = open("/proc/stb/info/model", "r").readline().strip()
			if device == "dm7025":
				self.passed = True
				return True
		except:
			pass
		etpm = eTPM()
		l2cert = etpm.getCert(eTPM.TPMD_DT_LEVEL2_CERT)
		if l2cert is None:
			raise Exception, "l2cert not found"
		self.l2cert = l2cert 	
		l2key = validate_cert(l2cert, rootkey)
		if l2key is None:
			raise Exception, "l2cert invalid"
		self.l2key = l2key
		l3cert = etpm.getCert(eTPM.TPMD_DT_LEVEL3_CERT)
		if l3cert is None:
			raise Exception, "l3cert not found (can be fixed by running the genuine dreambox plugin and running the offered update)"
		self.l3cert = l3cert	
		l3key = validate_cert(l3cert, l2key)
		if l3key is None:
			raise Exception, "l3cert invalid"
		self.l3key = l3key
		rnd = read_random()
		val = etpm.challenge(rnd)
		result = decrypt_block(val, self.l3key)
		if result[80:88] != rnd:
			raise Exception, "Failed challenge"
		self.passed = True
		return True
	def __getattr__(self, name):
		if name in ('l2cert', 'l2key', 'l3cert', 'l3key'):
			self.check()
			return getattr(self, name)
		else:
			raise AttributeError

tpm = PyTPM()
check = tpm.check
