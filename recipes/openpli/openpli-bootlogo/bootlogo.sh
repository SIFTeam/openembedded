# thoug not actually a bootlogo task, set the correct videomode before showing the bootlogo
cat /etc/videomode > /proc/stb/video/videomode

/usr/bin/showiframe /usr/share/bootlogo.mvi