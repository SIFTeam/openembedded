#include <sys/mman.h>
#include <sys/mount.h>
#include <sys/types.h>
#include <sys/stat.h>

#include <stdio.h>
#include <fcntl.h>
#include <linux/kdev_t.h>
#include <dirent.h>
#include <errno.h>

#include <unistd.h>
#include <stdlib.h>
#include <string.h>


#define PREFIX
//#define PREFIX "/boot"

static int bootcf()
{
	int res;

	// Mount the CF card, where the root is	
	printf("mounting CF...\n");
	res = mount("/dev/hdc1", PREFIX"/mnt/root", "ext3", 0, 0);
	if (res)
	{
		perror("Error mounting /dev/hdc1 on /mnt/root");
		res = mount("/dev/hdc", PREFIX"/mnt/root", "ext3", 0, 0);
		if (res)
		{
			perror("Error mounting /dev/hdc on /mnt/root");
			return res;
		}
	}
	
	/* now the situation is:
			/                     - our boot jffs2 partition
			/mnt/root                 - The CF card 
	*/
	
	printf("pivot_root\n");
	res = pivot_root(PREFIX"/mnt/root", PREFIX"/mnt/root/boot");
	if (res < 0)
	{
		perror("pivot_root");
		return 1;
	}

	return 0;
}


int main(int argc, char *argv[], char *envp[])
{
	int r = bootcf();
	if (r == 0)
	{
		printf("call init!\n");
		execve("/sbin/init", argv, envp);
	}
	else
	{
		printf("FAILED! code: %d. Boot JFFS from FLASH\n", r);
		execve("/bin/initflash", argv, envp);
	}
	perror("Panic! Could not boot anything");
	return 1;
}

