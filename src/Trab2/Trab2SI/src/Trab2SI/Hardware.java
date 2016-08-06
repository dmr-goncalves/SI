package Trab2SI;

/**
 *
 * @author DuarteG
 */
public class Hardware {
    
    static {
        System.load("C:\\SI\\Trab2_SI_39366_39928_40581\\Trab2SI\\Hardware.dll");
    }
    
    native public void create_di(int port);
    
    native public void create_do(int port);
    
    native public void write_port(int port, int value);
    
    native public int read_port(int port);
    
    native public boolean getBit(int pos, int port);
    
    native public int setBit(int port, int pos, boolean bitValue);
    
    native public int move(char axis, int direction);
    
    native public int stop(char axis);
    
    native public int move_station(int side, int direction);
    
    native public int position(char axis);
    
    native public boolean moving(int mover, int direction);
    
    native public boolean got_piece(int mover);
    
    native public boolean is_at_z_up();
    
}
