package Trab2SI;

/**
 *
 * @author DuarteG
 */
public class hwOrder {
    
    public int id;
    public int op;// 0 - move; 1 - stop; 2 - move_station; 4 - goto_up_level; 5 - goto_down_level
    public int side_axis; // 0 - X; 1 - Y; 2 - Z; 3 - Left Station, 4 - Right Station
    public int sensor;
    public int status; // -1 - waiting; 0 - running; 1 - ready; 2- done
    public int goal; //1 - Done,
    public int actual;
    public int posx = -1;
    public int posy = -1;
    public int posz = -1;
    
    public hwOrder(int _id, int _op, int _side_axis, int _sensor) {
        id = _id;
        op = _op;
        side_axis = _side_axis;
        sensor = _sensor;
        status = -1;
        
    }
    
    public void setStatus(int newStatus) {
        status = newStatus;
    }
    
    public void setPosX(int posx) {
        this.posx = posx;
    }
    
    public void setPosY(int posy) {
        this.posy = posy;
    }
    
    public void setPosZ(int posz) {
        this.posz = posz;
    }
    
    public void setOrderStatus(int newStatus) {
        status = newStatus;
    }
}
