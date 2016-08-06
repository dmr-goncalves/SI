package Trab2SI;

import java.util.*;

/**
 *
 * @author DuarteG
 */
public class Plan {
    
    ArrayList<hwOrder> orderList = new ArrayList<hwOrder>(); //Lista de ordens que constituem um plano
    ArrayList<Transition> transitionList = new ArrayList<Transition>(); //Lista de transições de um plano
    
    public Plan() {
    }
    
    //Ordens
    public void addHwOrder(hwOrder newOrder) {
        orderList.add(newOrder); //Adiciona uma ordem ao plano
    }
    
    public hwOrder getHwOrder(int index) {
        return orderList.get(index); //Vai buscar uma ordem ao plano
    }
    
    public ArrayList<hwOrder> plano() {
        return orderList;
    }
    
    public boolean HwOrderDone() {
        //  -1 - waiting; 0 - running; 1- done
        for (int i = 0; i < orderList.size(); i++) {
            
            hwOrder hw = orderList.get(i);
            if (hw.status != 2) {
                return false;
            }
        }
        return true;
    }
    
    public boolean orderDependency(hwOrder hw) {
        for (int j = 0; j < transitionList.size(); j++) {
            Transition t = transitionList.get(j);
            if (hw.id == t.from) {
                return true;
            }
        }
        return false;
    }
    
    public int numberTransitionsOrderDependency(hwOrder hw) { //numero de transicoes de que a ordem depende
        int number = 0;
        for (int j = 0; j < transitionList.size(); j++) {
            Transition t = transitionList.get(j);
            if (hw.id == t.to) {
                number++;
            }
        }
        return number;
    }
    
    public int numberTransitionsFromOrderDependency(hwOrder hw) { //numero de transicoes que dependem da ordem
        int number = 0;
        for (int j = 0; j < transitionList.size(); j++) {
            Transition t = transitionList.get(j);
            if (hw.id == t.from) {
                number++;
            }
        }
        return number;
    }
    
    public boolean allTransitionsToOrderDone(hwOrder hw) {
        int number = 0;
        for (int j = 0; j < transitionList.size(); j++) {
            Transition t = transitionList.get(j);
            if (hw.id == t.to && t.status == 2) {
                number++;
            }
        }
        if (number == numberTransitionsOrderDependency(hw)) {
            return true;
        } else {
            return false;
        }
        
    }
    
    public boolean allTransitionsFromOrderWaiting(hwOrder hw) {
        int number = 0;
        for (int j = 0; j < transitionList.size(); j++) {
            Transition t = transitionList.get(j);
            if (hw.id == t.from && t.status == -1) {
                number++;
            }
        }
        if (number == numberTransitionsOrderDependency(hw)) {
            return true;
        } else {
            return false;
        }
        
    }
    
    //Transições
    public void addTransition(Transition trans) {
        transitionList.add(trans); //Adiciona uma ordem ao plano
    }
    
    public Transition getTransition(int index) {
        return transitionList.get(index); //Vai buscar uma ordem ao plano
    }
    
    public ArrayList<Transition> plano2() {
        return transitionList;
    }
    
    public boolean TransitionDone() {
        //  -1 - waiting; 0 - running; 2- done
        for (int i = 0; i < orderList.size(); i++) {
            
            Transition trans = transitionList.get(i);
            if (trans.status != 2) {
                return false;
            }
        }
        return true;
    }
    
    public void setAllTransitionDependingFromOrderDone(hwOrder hw) {
        for (int i = 0; i < transitionList.size(); i++) {
            Transition ti = transitionList.get(i);
            if (ti.from == hw.id) {
                ti.setStatus(2);
            }
        }
    }
    
}
