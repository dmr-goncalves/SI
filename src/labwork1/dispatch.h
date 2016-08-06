#pragma once
#ifndef DISPATCH_H_
#define DISPATCH_H_

//*******************************************************************************************************
//OPERAÇÕES REFERENTES À INTERACÇÃO COM O KIT/SIMULADOR
bool get_bit(int pos, uInt8 port);

uInt8 set_bit(uInt8 px, int pos, bool bit_value);

uInt8 read_port(int port);

void write_port(int port, uInt8 value);

//*******************************************************************************************************
//OPERAÇÕES REFERENTES AOS MOVIMENTOS
void move_z_up();

void move_z_down();

void stop_z();

void move_x_right();

void move_x_left();

void stop_x();

void move_y_in();

void move_y_out();

void stop_y();

void move_left_station_in();

void move_left_station_out();

void stop_left_station();

void move_right_station_in();

void move_right_station_out();

void stop_right_station();

void stop_all();

#endif /* DISPATCH_H_ */
