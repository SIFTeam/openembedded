from enigma import eTuxtxtApp
from Plugins.Plugin import PluginDescriptor

def main(session, **kwargs):
	eTuxtxtApp.getInstance().startUi()

def Plugins(**kwargs):
	return PluginDescriptor(name="TuxTXT", description="Videotext", where = PluginDescriptor.WHERE_TELETEXT, fnc=main)
