#include <linux/autoconf.h>
#include <linux/module.h>
#include <linux/version.h>
#include <linux/fs.h>
#include <linux/delay.h>
#include <linux/kernel.h>
#include <linux/module.h>
#include <linux/moduleparam.h>
#include <linux/vermagic.h>
#include <linux/compiler.h>

static int msdelay = 1000;
module_param(msdelay, int, 0644);

static int __init sleep_init(void)
{
	mdelay(msdelay);
	return 0;
}

static void __exit sleep_exit(void)
{
}

module_init(sleep_init);
module_exit(sleep_exit);

MODULE_LICENSE("GPL");
MODULE_DESCRIPTION("sleep hack");
