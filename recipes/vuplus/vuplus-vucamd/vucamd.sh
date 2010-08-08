#!/bin/sh

usage()
{
	echo "usage: $0 {start|stop|status|restart|reload}"
}

if [ $# -lt 1 ] ; then usage ; break ; fi
action=$1

case "$action" in

start)
	echo -n "Start daemon:"
	/usr/bin/vucamd &
		;;

stop)
	echo -n "Stopping daemon"
	killall vucamd
	echo "."
	;;

status)
	;;

restart|reload)
	$0 stop
	$0 start
	;;

*)
	usage
	;;

esac

exit 0
