package Trab2SI;

import java.util.ArrayList;

/**
 *
 * @author DuarteG
 */
public class Warehouse {
    
    /**
     * WareHouse Vars
     */
    ArrayList<hwOrder> nextOrderList = new ArrayList<hwOrder>(); //Lista de ordens que constituem um plano
    ArrayList<Transition> nextTransitionList = new ArrayList<Transition>(); //Lista de transições de um plano
    ArrayList<hwOrder> lastOrderList = new ArrayList<hwOrder>(); //Lista de ordens que constituem um plano
    int firstTime = -1;
    int errorType = 0;
    boolean loadDone = false;
    int sensoresInfo[] = new int[6];
    
    public Warehouse() {
    }
    
    //Ordens Seguintes
    
    public void addNextHwOrder(hwOrder newOrder) {
        nextOrderList.add(newOrder); //Adiciona uma ordem ao plano
    }
    
    public hwOrder getNextHwOrder(int index) {
        return nextOrderList.get(index); //Vai buscar uma ordem ao plano
    }
    
    public hwOrder remNextHwOrder(int index) {
        return nextOrderList.remove(index);
    }
    
    public ArrayList<hwOrder> plano() {
        return nextOrderList;
    }
    
    // Transições
    
    public void addNextTransition(Transition trans) {
        nextTransitionList.add(trans); //Adiciona uma ordem ao plano
    }
    
    public Transition getNextTransition(int index) {
        return nextTransitionList.get(index); //Vai buscar uma ordem ao plano
    }
    
    public Transition remNextTransition(int index) {
        return nextTransitionList.remove(index);
    }
    
    public ArrayList<Transition> plano2() {
        return nextTransitionList;
    }
    
    //Ordens anteriores
    
    public void addLastHwOrder(hwOrder newOrder) {
        lastOrderList.add(newOrder); //Adiciona uma ordem ao plano
    }
    
    public hwOrder getLastHwOrder(int index) {
        return lastOrderList.get(index); //Vai buscar uma ordem ao plano
    }
    
    public hwOrder remLastHwOrder(int index) {
        hwOrder hw;
        for (int i = 0; i < lastOrderList.size(); i++) {
            hw = getLastHwOrder(i);
            if (hw.id == index) {
                return lastOrderList.remove(i);
            }
        }
        
        return null;
    }
    
    public boolean AnyHwOrderRunning() {
        //  -1 - waiting; 0 - running; 1- done
        for (int i = 0; i < lastOrderList.size(); i++) {
            
            hwOrder hw = lastOrderList.get(i);
            if (hw.status == 0) {
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<hwOrder> plano3() {
        return lastOrderList;
    }
//    public void setSensorInfo(String _sensoresInfo) {
//        this.sensoresInfo = _sensoresInfo;
//    }
//    public String getSensoresInfo(){
//        return this.sensoresInfo;
//    }
}
