package hardware;

import java.util.ArrayList;
import CLIPSJNI.*;

/**
 *
 * @author DuarteG
 */
public class Dispatch { //asserts
    
    private static final int LEFT = -1;
    private static final int RIGHT = 1;
    private static final int UP = 2;
    private static final int DOWN = -2;
    private static final int IN = 3;
    private static final int OUT = -3;
    private static final int LIFT = 4;
    private static final int LEFT_STATION = 5;
    private static final int RIGHT_STATION = -5;
    public static Hardware h;
    Warehouse wh;
    
    public Dispatch(Warehouse _wh) {
        
        wh = _wh;
        h = new Hardware();
        h.create_di(0);
        h.create_di(1);
        h.create_di(2);
        h.create_di(3);
        h.create_do(4);
        h.create_do(5);
    }
    
    public void go() {
        
        int size = wh.nextOrderList.size();
        String s;
        for (int i = 0; i < size; i++) {
            
            hwOrder order = wh.remNextHwOrder(0);
            // System.out.println(order.id);
            order.setOrderStatus(0);
            
            if (order.op == 0 && order.side_axis == 0) {
                if (h.position('X') > order.posx) {
                    h.move('X', LEFT);
                } else {
                    if (h.position('X') < order.posx) {
                        h.move('X', RIGHT);
                    }
                }
                
            }
            if (order.op == 0 && order.side_axis == 2) {
                if (h.position('Z') < order.posz) {
                    h.move('Z', UP);
                } else {
                    if (h.position('Z') > order.posz) {
                        h.move('Z', DOWN);
                    }
                }
            }
            if (order.op == 0 && order.side_axis == 1) {
                if (h.position('Y') > order.posy) {
                    h.move('Y', IN);
                } else {
                    if (h.position('Y') < order.posy) {
                        h.move('Y', OUT);
                    }
                }
            }
            if (order.op == 4) {
                h.move('Z', UP);
            }
            
            if (order.op == 5) {
                
                h.move('Z', DOWN);
                
            }
            
            if (order.op == 2 && order.side_axis == 3) {
                
                h.move_station(LEFT, IN);
                
            }
            if (order.op == 2 && order.side_axis == 4) {
                h.move_station(RIGHT, OUT);
            }
            
            if (order.op == 1 && order.side_axis == 0) {
                h.stop('X');
                order.setStatus(2);
            }
            if (order.op == 1 && order.side_axis == 1) {
                h.stop('Y');
                order.setStatus(2);
            }
            if (order.op == 1 && order.side_axis == 2) {
                h.stop('Z');
                order.setStatus(2);
            }
            if (order.op == 1 && order.side_axis == 3) {
                h.stop('w');
                order.setStatus(2);
            }
            if (order.op == 1 && order.side_axis == 4) {
                h.stop('w');
                order.setStatus(2);
            }
//            if (order.op == 1) {
//                h.stop('w');
//            }
            if (order.op == 3) { //Calibrate
                
                h.move('Y', OUT);
                while (!(h.position('Y') == 1) && h.moving(LIFT, OUT)) {
                    h.position('Y');
                }
                h.stop('Y');
                
                h.move('X', LEFT);
                while (!(h.position('X') == 0) && h.moving(LIFT, LEFT)) {
                    h.position('X');
                }
                h.stop('X');
                
                h.move('Z', DOWN);
                while (!(h.position('Z') == 0) && h.moving(LIFT, DOWN)) {
                    h.position('Z');
                }
                h.stop('Z');
            }
            
        }
        int p0 = h.read_port(0);
        Integer p1 = h.read_port(1);
        Integer p2 = h.read_port(2);
        Integer p3 = h.read_port(3);
        Integer p4 = h.read_port(4);
        Integer p5 = h.read_port(5);
        
        // String portos = p0.toString() + p1.toString() + p2.toString() + p3.toString() + p4.toString() + p5.toString();
        p0 = h.read_port(0);
        wh.sensoresInfo[0] = p0;
        
        p1 = h.read_port(1);
        wh.sensoresInfo[1] = p1;
        
        p2 = h.read_port(2);
        wh.sensoresInfo[2] = p2;
        
        p3 = h.read_port(3);
        wh.sensoresInfo[3] = p3;
        
        p4 = h.read_port(4);
        wh.sensoresInfo[4] = p4;
        
        p5 = h.read_port(5);
        wh.sensoresInfo[5] = p5;
        
    }
    
    public void checkOrderEnd(ArrayList<hwOrder> orderList) {
        Hardware h = new Hardware();
        for (int i = 0; i < orderList.size(); i++) {
            hwOrder order = orderList.get(i);
            if (order.status == 2) {
                h.stop('w');
                
            }
        }
    }
}
