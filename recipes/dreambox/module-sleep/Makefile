PWD  ?= $(shell pwd)

obj-m := module-sleep.o

module-sleep-y := sleep.o

default:
	$(MAKE) -C $(KDIR) M=$(PWD) modules