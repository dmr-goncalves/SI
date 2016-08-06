// labwork1.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "time.h"
#include <conio.h>
#include <stdio.h>
#include <stdlib.h>
#include "NIDAQmx.h"
#include "interface.h"
#include <iostream>
#include <windows.h>
#include "dispatch.h"

typedef struct {
	bool cheio;
	int valor;
	struct tm * now;
}Armazem;

Armazem matrix[10][5];

int main() {
	create_DI_channel(0);
	create_DI_channel(1);
	create_DI_channel(2);
	create_DI_channel(3);
	create_DO_channel(4);
	create_DO_channel(5);

	bool run = true;
	int bit = 0;

	while(run){
		if (_kbhit())
			if(_getch()==27){
				exit(1);
			}
		uInt8 p4 = read_port(4);
		bit++;

		Sleep(100);

		if(get_bit(7, p4)){
			printf("\n1");
		}
		if (!get_bit(7, p4)) {
			printf("\n0");
		}

		if(bit>=10){
			if(!get_bit(7, p4)){
				p4 = set_bit(p4, 7, true);
				write_port(4, p4);
			} else {
				if (get_bit(7, p4)) {
					p4 = set_bit(p4, 7, false);
					write_port(4, p4);
				}
			}
			bit = 0;
		}

	}

	/*time_t t = time(0); //now time

	for (int i = 0; i < 5; i++) {
		for (int j = 0; j < 10; j++) {
			matrix[i][j].cheio = false;
			matrix[i][j].valor = 0;
		}
	}

	calibrate();

	task_storage_services();

	write_port(4, 0); //para todos os motores como precaução
	close_channels();*/

	_getch();
}

