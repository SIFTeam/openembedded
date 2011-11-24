/* This program is free software; you can redistribute it and/or modify
*  it under the terms of the GNU General Public License as published by
*  the Free Software Foundation; either version 2 of the License, or
*  (at your option) any later version.
*  
*  This program is distributed in the hope that it will be useful,
*  but WITHOUT ANY WARRANTY; without even the implied warranty of
*  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*  GNU General Public License for more details.
*  
*  You should have received a copy of the GNU General Public License
*  along with this program; if not, write to the Free Software
*  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
*  MA 02110-1301, USA.
*
*  E2 Packer for AzBox enigma2 patch.e2 format 
*
*	Based on specs by Telesat:
*	
*	-ID TEam - 10 bytes ( example: like RTi Team, SIF Team, or PB Team ) ok
*	-ID Enigma - 20 bytes ( example: next 20 bytes are ID Enigma, like BlackHole Enigma usb dvb-t etc. etc ) ok
*	-Version - 10 bytes ( example: this describe version like RC5 ) ok 
*	-About Enigma - 12 bytes ( example: This is PB Enigma ) ok
*	-size of cramfs - 4 bytes False is not true
*	-cramfs - content of e2 files
*	-Kernel Description - 12 bytes 
*	-size of kernel - 4 bytes False is not true
*	-kernel 
*  
* ChangeLog:
* 2011-04-06  Dr_gogeta86 
* 
* Rewrite file outputstreams
* File type check
* 
* 2011-04-11  Dr_gogeta86
* 
* Added option support
*
* 2011-04-15 Dr_gogeta86
*
* Now cramfs and kernel size are used as file magic according to spec
*
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <arpa/inet.h>


#define MAGIC_LEN  4

int main (int argc, char *argv[]) { 

	int i;
	uint32_t cramfs_size;
	uint32_t kernel_size;
	int e2_cramfs_size;
	int e2_kernel_size;	
	char e2_team[10] ;
	char e2_enigma[20] ;
	char e2_version[10] ;
	char e2_about[12] ;
	char e2_kernel_desc[12]  ;
	char kernel_file_magic[]= {0x2D,0x72,0x6F,0x6D}; 	// Kernel file magic
	char cramfs_file_magic[]= {0x45,0x3D,0xCD,0x28};	// Cramfs file magic
	
	FILE *cramfs_file;
	FILE *kernel_file;
	FILE *e2_packed;


	if ( argc < 8 ){
		printf("USAGE: e2_packer cramfs_file kernel_file patch.e2 e2_team e2_enigma e2_version e2_about e2_kernel_desc\n");
		exit(98);		
	}	
	
	if ((cramfs_file = fopen( argv[1] ,"r")) == NULL){
		 printf("Cramfs: %s non trovato\n", argv[1] );
		 exit (1);
	}
	
	char var_temp_file_magic[7] ="";
	fread(var_temp_file_magic, 1, MAGIC_LEN , cramfs_file);
	if ( memcmp(var_temp_file_magic , cramfs_file_magic , MAGIC_LEN )) {
		printf("DEBUG Cramfs magic: %s\n", var_temp_file_magic);
		printf("Cramfs non valido!\n");
		exit (97);
	}

	
	if ((kernel_file = fopen( argv[2] ,"r")) == NULL){
		printf("Kernel %s not found\n", argv[3]);
		exit (2);
	}
	
	fread(var_temp_file_magic, 1, MAGIC_LEN , kernel_file);
	if ( memcmp(var_temp_file_magic , kernel_file_magic , MAGIC_LEN )) {
		printf("DEBUG Kernel magic: %s\n", var_temp_file_magic);
		printf("Kernel non valido!\n");
		exit (96);
	}
	
	if ((e2_packed = fopen( argv[3] ,"w+")) == NULL) {
		printf("I cannot write image to file\n");
		exit (4);
	}	

	strncpy(e2_team, argv[4], sizeof(e2_team));
	strncpy(e2_enigma, argv[5], sizeof(e2_enigma));
	strncpy(e2_version, argv[6], sizeof(e2_version));
	strncpy(e2_about, argv[7], sizeof(e2_about));
	strncpy(e2_kernel_desc, argv[8], sizeof(e2_kernel_desc));

	// Calculate Cramfs size
	
	fseek(cramfs_file,0,SEEK_END);
	e2_cramfs_size = ftell(cramfs_file);
	fseek(cramfs_file,0,SEEK_SET);
	
	printf("Cramfs size: %d bytes or %08X \n",e2_cramfs_size,e2_cramfs_size);
	
	// Calculate Kernel size
	fseek(kernel_file,0,SEEK_END);
	e2_kernel_size = ftell(kernel_file);
	fseek(kernel_file,0,SEEK_SET);
	
	printf("Kernel size: %d bytes or %08X \n",e2_kernel_size,e2_kernel_size);
	
	// Write File Magic to Image or cramfs size
	
	cramfs_size = htonl(e2_cramfs_size);
	fwrite((void *)&cramfs_size, 4, 1, e2_packed);
	

	// Write Team Name
	
	fwrite(e2_team, 1, sizeof(e2_team), e2_packed);
	
	// Write Enigma Name
	
	fwrite(e2_enigma, 1, sizeof(e2_enigma), e2_packed);
	
	// Write Image Version

	fwrite(e2_version, 1, sizeof(e2_version), e2_packed);	
		
	// Write Release Note
	
	fwrite(e2_about, 1, sizeof(e2_about), e2_packed);	
		
	
	// Copy Cramfs to image
	
	printf("Copy Cramfs into image\n");
	 
	 while(!feof(cramfs_file)) {
		char ch = fgetc(cramfs_file);
		if(ferror(cramfs_file)) {
			printf("Error reading cramfs source file.\n");
			exit(4);
		}
    
		if(!feof(cramfs_file)) fputc(ch, e2_packed );
			if(ferror(e2_packed)) {
				printf("Error writing cramfs destination image.\n");
				exit(5);
			}
		}
	
	// Write Kernel Magic to Image (kernel size)
	
	kernel_size = htonl(e2_kernel_size);
	fwrite((void *)&kernel_size, 4, 1, e2_packed);

	// Write Kernel Description to Image
	
	fwrite(e2_kernel_desc, 1, sizeof(e2_kernel_desc), e2_packed);	
	
	// Copy kernel to image
	printf("Copy Kernel into image\n");
	
	
	while(!feof(kernel_file)) {
		char kr = fgetc(kernel_file);
		if(ferror(kernel_file)) {
			printf("Error reading kernel source file.\n");
			exit(6);
		}
		if(!feof(kernel_file)) fputc(kr, e2_packed );
		if(ferror(e2_packed)) {
			printf("Error writing cramfs destination file.\n");
			exit(7);
		}	
	}
  
	fflush(cramfs_file); // flush cache
	fclose(cramfs_file); // close cramfs file
	fflush(kernel_file); // flush cache
	fclose(kernel_file); // close kernel file
	fflush(e2_packed); // flush cache
	fclose(e2_packed); // close output file
}
