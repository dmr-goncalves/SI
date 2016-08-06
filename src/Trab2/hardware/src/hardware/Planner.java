package hardware;

import java.util.Scanner;

/**
 *
 * @author DuarteG
 */
public class Planner {
    
    Warehouse wh;
    static hwOrder order;
    static Transition transition;
    static Plan thisPlan;
    
    public Planner(Warehouse _wh) {
        wh = _wh;
    }
    boolean has_piece[][] = new boolean[10][5];  // incializado a 0 por defeito
    
    public Plan createPlan(int op) {
        int sensorx, sensorz, sensorUp, sensorx2, sensorz2, sensorUp2;
        thisPlan = new Plan();
        Scanner reader = new Scanner(System.in);
        wh.firstTime = 0;
        
        System.out.print("X - ");
        int posx = reader.nextInt();
        if (posx < 8) {
            sensorx = posx;
        } else {
            sensorx = 10 + posx - 8;
        }
        
        System.out.print("Z - ");
        int posz = reader.nextInt();
        if (posz < 4) {
            sensorz = 20 + 6 - 2 * posz;
        } else {
            sensorz = 16;
        }
        if (sensorz == 20) {
            sensorUp = 17;
        } else {
            sensorUp = sensorz - 1;
        }
        
        switch (op) {
            
            case 1:
                
                order = new hwOrder(0, 0, 1, 13);
                order.setPosY(1);
                order.setOrderStatus(0);
                thisPlan.addHwOrder(order);
                transition = new Transition(0, 1);
                thisPlan.addTransition(transition);
                transition = new Transition(0, 2);
                thisPlan.addTransition(transition);
                
                order = new hwOrder(1, 0, 0, sensorx); //Move to the right
                order.setPosX(posx);
                thisPlan.addHwOrder(order);
                order.setOrderStatus(-1);//Adiciona uma ordem a uma lista de ordens, que constituem um plano
                
                order = new hwOrder(2, 0, 2, sensorz);//Move up
                order.setPosZ(posz);
                
                thisPlan.addHwOrder(order);//Adiciona uma ordem a uma lista de ordens, que constituem um plano
                order.setOrderStatus(-1);
                break;
                
            case 2: //put_piece
                if (has_piece[posx][posz]) {
                    System.out.println("There is already a piece in" + posx + "," + posz);
                }
                
                //Move Y Middle
                order = new hwOrder(0, 0, 1, 13);
                order.setPosY(1);
                transition = new Transition(0, 1);
                thisPlan.addTransition(transition);
                transition = new Transition(0, 2);
                thisPlan.addTransition(transition);
                thisPlan.addHwOrder(order);
                //Move X to 0
                order = new hwOrder(1, 0, 0, 00); //Move to the right
                order.setPosX(0);
                thisPlan.addHwOrder(order);
                transition = new Transition(1, 3);
                thisPlan.addTransition(transition);
                order.setOrderStatus(-1);//Adiciona uma ordem a uma lista de ordens, que constituem um plano
                
                //Move Z to 0
                order = new hwOrder(2, 0, 2, 26);//Move up
                order.setPosZ(0);
                thisPlan.addHwOrder(order);//Adiciona uma ordem a uma lista de ordens, que constituem um plano
                order.setOrderStatus(-1);
                transition = new Transition(2, 3);
                thisPlan.addTransition(transition);
                
                //Move Y Out
                order = new hwOrder(3, 0, 1, 12);
                order.setPosY(2);
                transition = new Transition(3, 4);
                thisPlan.addHwOrder(order);
                thisPlan.addTransition(transition);
                
                //Move Z UP
                order = new hwOrder(4, 4, 2, 25);
                transition = new Transition(4, 5);
                thisPlan.addHwOrder(order);
                thisPlan.addTransition(transition);
                
                //START LEFT MOTOR
                order = new hwOrder(5, 2, 3, 30);
                transition = new Transition(5, 6);
                thisPlan.addHwOrder(order);
                thisPlan.addTransition(transition);
                
                //Move Y Middle
                order = new hwOrder(6, 0, 1, 13);
                order.setPosY(1);
                transition = new Transition(6, 7);
                thisPlan.addHwOrder(order);
                thisPlan.addTransition(transition);
                
                //Move Z Down
                order = new hwOrder(7, 5, 2, 26);
                transition = new Transition(7, 8);
                thisPlan.addHwOrder(order);
                thisPlan.addTransition(transition);
                transition = new Transition(7, 9);
                thisPlan.addTransition(transition);
                
                //GOTO X
                order = new hwOrder(8, 0, 0, sensorx); //Move to the right
                order.setPosX(posx);
                transition = new Transition(8, 10);
                thisPlan.addTransition(transition);
                thisPlan.addHwOrder(order);
                order.setOrderStatus(-1);//Adiciona uma ordem a uma lista de ordens, que constituem um plano
                
                //GOTO Z
                order = new hwOrder(9, 0, 2, sensorz);//Move up
                order.setPosZ(posz);
                transition = new Transition(9, 10);
                thisPlan.addTransition(transition);
                thisPlan.addHwOrder(order);//Adiciona uma ordem a uma lista de ordens, que constituem um plano
                order.setOrderStatus(-1);
                
                //Move Z UP
                order = new hwOrder(10, 4, 2, sensorUp);//Move up
                order.setPosZ(posz);
                transition = new Transition(10, 11);
                thisPlan.addTransition(transition);
                thisPlan.addHwOrder(order);//Adiciona uma ordem a uma lista de ordens, que constituem um plano
                order.setOrderStatus(-1);
                
                //Move Y In
                order = new hwOrder(11, 0, 1, 14);
                order.setPosY(0);
                transition = new Transition(11, 12);
                thisPlan.addHwOrder(order);
                thisPlan.addTransition(transition);
                
                //Move Z Down
                order = new hwOrder(12, 5, 2, sensorz);//Move up
                order.setPosZ(posz);
                transition = new Transition(12, 13);
                thisPlan.addTransition(transition);
                thisPlan.addHwOrder(order);//Adiciona uma ordem a uma lista de ordens, que constituem um plano
                order.setOrderStatus(-1);
                
                //Move Y Middle
                order = new hwOrder(13, 0, 1, 13);
                order.setPosY(1);
                thisPlan.addHwOrder(order);
                
                has_piece[posx][posz] = true;
                break;
                
            case 3: //get_piece
                
                if (!has_piece[posx][posz]) {
                    System.out.println("There isn't a piece in" + posx + "," + posz);
                }
                
                //Move Y Middle
                order = new hwOrder(0, 0, 1, 13);
                order.setPosY(1);
                transition = new Transition(0, 1);
                thisPlan.addTransition(transition);
                transition = new Transition(0, 2);
                thisPlan.addTransition(transition);
                thisPlan.addHwOrder(order);
                //Move X to posx
                order = new hwOrder(1, 0, 0, sensorx); //Move to the right
                order.setPosX(posx);
                thisPlan.addHwOrder(order);
                transition = new Transition(1, 3);
                thisPlan.addTransition(transition);
                order.setOrderStatus(-1);//Adiciona uma ordem a uma lista de ordens, que constituem um plano
                
                //Move Z to posz
                order = new hwOrder(2, 0, 2, sensorz);//Move up
                order.setPosZ(posz);
                
                thisPlan.addHwOrder(order);//Adiciona uma ordem a uma lista de ordens, que constituem um plano
                order.setOrderStatus(-1);
                transition = new Transition(2, 3);
                thisPlan.addTransition(transition);
                
                //Y IN
                order = new hwOrder(3, 0, 1, 14);
                order.setPosY(0);
                transition = new Transition(3, 4);
                thisPlan.addHwOrder(order);
                thisPlan.addTransition(transition);
                
                //Z UP
                order = new hwOrder(4, 4, 2, sensorUp);
                transition = new Transition(4, 5);
                thisPlan.addHwOrder(order);
                thisPlan.addTransition(transition);
                
                //Y MIDDLE
                order = new hwOrder(5, 0, 1, 13);
                order.setPosY(1);
                transition = new Transition(5, 6);
                thisPlan.addHwOrder(order);
                thisPlan.addTransition(transition);
                
                //Z DOWN
                order = new hwOrder(6, 5, 2, sensorz);//Move up
                order.setPosZ(posz);
                transition = new Transition(6, 7);
                thisPlan.addTransition(transition);
                transition = new Transition(6, 8);
                thisPlan.addTransition(transition);
                thisPlan.addHwOrder(order);//Adiciona uma ordem a uma lista de ordens, que constituem um plano
                order.setOrderStatus(-1);
                //MOVE X to 9
                order = new hwOrder(7, 0, 0, 11); //Move to the right
                order.setPosX(9);
                thisPlan.addHwOrder(order);
                transition = new Transition(7, 9);
                thisPlan.addTransition(transition);
                order.setOrderStatus(-1);//Adiciona uma ordem a uma lista de ordens, que constituem um plano
                
                //Move Z to 0
                order = new hwOrder(8, 0, 2, 26);//Move up
                order.setPosZ(0);
                thisPlan.addHwOrder(order);//Adiciona uma ordem a uma lista de ordens, que constituem um plano
                order.setOrderStatus(-1);
                transition = new Transition(8, 9);
                thisPlan.addTransition(transition);
                
                //Z UP
                order = new hwOrder(9, 4, 2, 25);
                transition = new Transition(9, 10);
                thisPlan.addHwOrder(order);
                thisPlan.addTransition(transition);
                //Y OUT
                order = new hwOrder(10, 0, 1, 12);
                order.setPosY(2);
                transition = new Transition(10, 11);
                thisPlan.addHwOrder(order);
                thisPlan.addTransition(transition);
                //START INVERSE RIGHT MOTOR
                order = new hwOrder(11, 2, 4, 52);
                transition = new Transition(11, 12);
                thisPlan.addHwOrder(order);
                thisPlan.addTransition(transition);
                //Z DOWN
                order = new hwOrder(12, 5, 2, 26);
                transition = new Transition(12, 13);
                thisPlan.addHwOrder(order);
                thisPlan.addTransition(transition);
                //Y MIDDLE
                order = new hwOrder(13, 0, 1, 13);
                order.setPosY(1);
                thisPlan.addHwOrder(order);
                has_piece[posx][posz] = false;
                break;
            case 4:
                System.out.print("X2 - ");
                int posx2 = reader.nextInt();
                System.out.print("Z2 - ");
                int posz2 = reader.nextInt();
                
                if (posx2 < 8) {
                    sensorx2 = posx2;
                } else {
                    sensorx2 = 10 + posx2 - 8;
                }
                
                if (posz2 < 4) {
                    sensorz2 = 20 + 6 - 2 * posz2;
                } else {
                    sensorz2 = 16;
                }
                if (sensorz2 == 20) {
                    sensorUp2 = 17;
                } else {
                    sensorUp2 = sensorz2 - 1;
                }
                //Move Y Middle
                order = new hwOrder(0, 0, 1, 13);
                order.setPosY(1);
                transition = new Transition(0, 1);
                thisPlan.addTransition(transition);
                transition = new Transition(0, 2);
                thisPlan.addTransition(transition);
                thisPlan.addHwOrder(order);
                //Move X to posx
                order = new hwOrder(1, 0, 0, sensorx); //Move to the right
                order.setPosX(posx);
                thisPlan.addHwOrder(order);
                transition = new Transition(1, 3);
                thisPlan.addTransition(transition);
                order.setOrderStatus(-1);//Adiciona uma ordem a uma lista de ordens, que constituem um plano
                
                //Move Z to posz
                order = new hwOrder(2, 0, 2, sensorz);//Move up
                order.setPosZ(posz);
                
                thisPlan.addHwOrder(order);//Adiciona uma ordem a uma lista de ordens, que constituem um plano
                order.setOrderStatus(-1);
                transition = new Transition(2, 3);
                thisPlan.addTransition(transition);
                
                //Y IN
                order = new hwOrder(3, 0, 1, 14);
                order.setPosY(0);
                transition = new Transition(3, 4);
                thisPlan.addHwOrder(order);
                thisPlan.addTransition(transition);
                
                //Z UP
                order = new hwOrder(4, 4, 2, sensorUp);
                transition = new Transition(4, 5);
                thisPlan.addHwOrder(order);
                thisPlan.addTransition(transition);
                
                //Y MIDDLE
                order = new hwOrder(5, 0, 1, 13);
                order.setPosY(1);
                transition = new Transition(5, 6);
                thisPlan.addHwOrder(order);
                thisPlan.addTransition(transition);
                
                //Z DOWN
                order = new hwOrder(6, 5, 2, sensorz);//Move up
                order.setPosZ(posz);
                transition = new Transition(6, 7);
                thisPlan.addTransition(transition);
                transition = new Transition(6, 8);
                thisPlan.addTransition(transition);
                thisPlan.addHwOrder(order);//Adiciona uma ordem a uma lista de ordens, que constituem um plano
                order.setOrderStatus(-1);
                
                //MOVE X TO POSX2
                order = new hwOrder(7, 0, 0, sensorx2); //Move to the right
                order.setPosX(posx2);
                thisPlan.addHwOrder(order);
                transition = new Transition(7, 9);
                thisPlan.addTransition(transition);
                order.setOrderStatus(-1);//Adiciona uma ordem a uma lista de ordens, que constituem um plano
                
                //Move Z to posz2
                order = new hwOrder(8, 0, 2, sensorz2);//Move up
                order.setPosZ(posz2);
                
                thisPlan.addHwOrder(order);//Adiciona uma ordem a uma lista de ordens, que constituem um plano
                order.setOrderStatus(-1);
                transition = new Transition(8, 9);
                thisPlan.addTransition(transition);
                
                //Move Z UP
                order = new hwOrder(9, 4, 2, sensorUp2);//Move up
                order.setPosZ(posz2);
                transition = new Transition(9, 10);
                thisPlan.addTransition(transition);
                thisPlan.addHwOrder(order);//Adiciona uma ordem a uma lista de ordens, que constituem um plano
                order.setOrderStatus(-1);
                
                //Move Y In
                order = new hwOrder(10, 0, 1, 14);
                order.setPosY(0);
                transition = new Transition(10, 11);
                thisPlan.addHwOrder(order);
                thisPlan.addTransition(transition);
                
                //Move Z Down
                order = new hwOrder(11, 5, 2, sensorz2);//Move up
                order.setPosZ(posz2);
                transition = new Transition(11, 12);
                thisPlan.addTransition(transition);
                thisPlan.addHwOrder(order);//Adiciona uma ordem a uma lista de ordens, que constituem um plano
                order.setOrderStatus(-1);
                
                //Move Y Middle
                order = new hwOrder(12, 0, 1, 13);
                order.setPosY(1);
                thisPlan.addHwOrder(order);
                
                //Adiciona uma ordem a uma lista de ordens, que constituem um plano
                has_piece[posx2][posz2] = true;
                break;
            default:
                break;
        }
        return thisPlan;
        
    }
    
    public void go(Plan thisPlan) {
        int porto;
        int bit;
        byte b;
        char bitstring;
        String s;
        int aux = 0;
        hwOrder order;
        if (wh.AnyHwOrderRunning()) {
            for (int z = 0; z < wh.lastOrderList.size(); z++) {
                hwOrder hw = wh.lastOrderList.get(z);
                porto = hw.sensor / 10;
                bit = hw.sensor % 10;
                b = (byte) (wh.sensoresInfo[porto]);
                s = String.format("%8s", Integer.toBinaryString(b & 0xFF));
                s = s.replace(' ', '0');
                bitstring = s.charAt(7 - bit);
                
                if (hw.op == 2 && bitstring == '1') {
                    order = new hwOrder(1000, 1, hw.side_axis, hw.sensor);
                    wh.addNextHwOrder(order);
                    wh.remLastHwOrder(hw.id);
                    hw.setOrderStatus(2);
                    thisPlan.setAllTransitionDependingFromOrderDone(hw);
                    wh.loadDone = true;
                    
                } else if (bitstring == '0') {
                    
                    order = new hwOrder(1000, 1, hw.side_axis, hw.sensor);
                    wh.addNextHwOrder(order);
                    wh.remLastHwOrder(hw.id);
                    hw.setOrderStatus(2);
                    thisPlan.setAllTransitionDependingFromOrderDone(hw);
                }
            }
        } else {
            if (wh.firstTime == 0) {
                for (int i = 0; i < thisPlan.orderList.size(); i++) {
                    hwOrder hw = thisPlan.orderList.get(i);
                    
                    if (thisPlan.numberTransitionsOrderDependency(hw) == 0) {
                        wh.addNextHwOrder(hw);
                        wh.addLastHwOrder(hw);
                        wh.firstTime++;
                        
                    }
                }
            } else {
                for (int i = 0; i < thisPlan.orderList.size(); i++) {
                    hwOrder hw = thisPlan.orderList.get(i);
                    
                    if (hw.status == -1 && thisPlan.allTransitionsToOrderDone(hw)) {
                        
                        wh.addNextHwOrder(hw);
                        wh.addLastHwOrder(hw);
                        
                    }
                    
                }
                
            }
        }
    }
}
