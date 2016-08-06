#include "stdafx.h"
#include <conio.h>
#include <stdio.h>
#include <stdlib.h>
#include "NIDAQmx.h"
#include "interface.h"
#include <iostream>
#include <windows.h>

//constantes
int bit_z_up = 5; //P4
int bit_z_down = 6; //P4
int bit_x_right = 0; //P4
int bit_x_left = 1; //P4
int bit_y_in = 4; //P4
int bit_y_out = 3; //P4
int bit_left_station_in = 7; //P4
int bit_left_station_out = 0; //P5
int bit_right_station_in = 1; //P5
int bit_right_station_out = 2; //P5

//*******************************************************************************************************
//OPERAÇÕES REFERENTES À INTERACÇÃO COM O KIT/SIMULADOR
bool get_bit(int pos, uInt8 port) {
	return(port & (1 << pos));
}

uInt8 set_bit(uInt8 px, int pos, bool bit_value) {
	uInt8 p_temp;

	if (bit_value == true) {
		p_temp = 0;
		p_temp |= 1 << pos;
		px |= p_temp;
	} else {
		p_temp = 0xff;
		px &= ((p_temp)-(1 << pos));
	}
	return px;
}

uInt8 read_port(int port) {
	uInt8 aa = ReadDigitalU8(port);
	return(aa);
}

void write_port(int port, uInt8 value) {
	WriteDigitalU8(port, value);
}

//*******************************************************************************************************
//OPERAÇÕES REFERENTES AOS MOVIMENTOS
void move_z_up() {
	uInt8 p4 = read_port(4);
	p4 = set_bit(p4, bit_z_down, false);
	p4 = set_bit(p4, bit_z_up, true);
	write_port(4, p4);
}

void move_z_down() {
	uInt8 p4 = read_port(4);
	p4 = set_bit(p4, bit_z_up, false);
	p4 = set_bit(p4, bit_z_down, true);
	write_port(4, p4);
}

void stop_z() {
	uInt8 p4 = read_port(4);
	p4 = set_bit(p4, bit_z_up, false);
	p4 = set_bit(p4, bit_z_down, false);
	write_port(4, p4);
}

void move_x_right() {
	uInt8 p4 = read_port(4);
	p4 = set_bit(p4, bit_x_left, false);
	p4 = set_bit(p4, bit_x_right, true);
	write_port(4, p4);
}

void move_x_left() {
	uInt8 p4 = read_port(4);
	p4 = set_bit(p4, bit_x_right, false);
	p4 = set_bit(p4, bit_x_left, true);
	write_port(4, p4);
}

void stop_x() {
	uInt8 p4 = read_port(4);
	p4 = set_bit(p4, bit_x_right, false);
	p4 = set_bit(p4, bit_x_left, false);
	write_port(4, p4);
}

void move_y_in() {
	uInt8 p4 = read_port(4);
	p4 = set_bit(p4, bit_y_out, false);
	p4 = set_bit(p4, bit_y_in, true);
	write_port(4, p4);
}

void move_y_out() {
	uInt8 p4 = read_port(4);
	p4 = set_bit(p4, bit_y_in, false);
	p4 = set_bit(p4, bit_y_out, true);
	write_port(4, p4);
}

void stop_y() {
	uInt8 p4 = read_port(4);
	p4 = set_bit(p4, bit_y_in, false);
	p4 = set_bit(p4, bit_y_out, false);
	write_port(4, p4);
}

void move_left_station_in() {
	uInt8 p4 = read_port(4);
	uInt8 p5 = read_port(5);
	p5 = set_bit(p5, 0, false);
	p4 = set_bit(p4, 7, true);
	write_port(5, p5);
	write_port(4, p4);
}

void move_left_station_out() {
	uInt8 p4 = read_port(4);
	uInt8 p5 = read_port(5);
	p4 = set_bit(p4, 7, false);
	p5 = set_bit(p5, 0, true);
	write_port(4, p4);
	write_port(5, p5);
}

void stop_left_station() {
	uInt8 p4 = read_port(4);
	uInt8 p5 = read_port(5);
	p4 = set_bit(p4, 7, false);
	p5 = set_bit(p5, 0, false);
	write_port(4, p4);
	write_port(5, p5);
}

void move_right_station_in() {
	uInt8 p5 = read_port(5);
	p5 = set_bit(p5, 2, false);
	p5 = set_bit(p5, 1, true);
	write_port(5, p5);
}

void move_right_station_out() {
	uInt8 p5 = read_port(5);
	p5 = set_bit(p5, 1, false);
	p5 = set_bit(p5, 2, true);
	write_port(5, p5);
}

void stop_right_station() {
	uInt8 p5 = read_port(5);
	p5 = set_bit(p5, 1, false);
	p5 = set_bit(p5, 2, false);
	write_port(0, p5);
}

void stop_all() {
	uInt8 pX = 0;
	write_port(4, pX);
	write_port(5, pX);
}