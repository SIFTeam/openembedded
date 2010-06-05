#!/bin/sh

case "$1" in
   start)
        # dirty_background_ratio defaults to 10%, it is set to 5 to
        # let pdflush do more work once activated. It could even
	# be argued that 1 is an appropriate value.
        echo 5 > /proc/sys/vm/dirty_background_ratio
	# dirty ratio defaults to 40, we bump it up a little because
	# writing is time-critical but reading is not
        echo 60 > /proc/sys/vm/dirty_ratio
        ;;
   restart|reload|force-reload)
        echo "Error: argument '$1' not supported" >&2
        exit 3
        ;;
   stop)
        # No-op
        ;;
   *)
        echo "Usage: $0 start|stop" >&2
        exit 3
        ;;
esac
exit 0
