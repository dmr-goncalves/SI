package Trab2SI;

/**
 *
 * @author DuarteG
 */
public class ErrorRecover {
    
    int errorID;
    Hardware h;
    Warehouse wh;
    hwOrder order;
    Transition trans;
    Diagnostic diag;
    
    public ErrorRecover(Warehouse _wh) {
        this.wh = _wh;
    }
    
    public void go() {
        System.out.println("Recuperador de Erros");
        switch (wh.errorType) {
            case 0:
                break;
            case 1:
                fix_error();
                break;
            case 2:
                fix_error();
                break;
        }
        
    }
    
    private boolean fix_error() {
        
        for (int x = 0; x < wh.nextOrderList.size(); x++) {
            wh.remNextHwOrder(x);
            for (int h = 0; h < wh.nextTransitionList.size(); h++) {
                wh.remNextTransition(h);
            }
        }
        //mete o Y no centro
        order = new hwOrder(10000, 0, 1, 13);
        order.setPosY(1);
        wh.addNextHwOrder(order);
        trans = new Transition(10000, 10001);
        wh.addNextTransition(trans);
//--------------------------------------------------------------------------------------------------
        //mete o Y para dentro
        order = new hwOrder(10001, 0, 1, 12);
        order.setPosY(0);
        wh.addNextHwOrder(order);
        trans = new Transition(10001, 10002);
        wh.addNextTransition(trans);
        
//--------------------------------------------------------------------------------------------------
        order = new hwOrder(10002, 4, 2, 25);
        trans = new Transition(10002, 10003);
        wh.addNextHwOrder(order);
        wh.addNextTransition(trans);
        
//---------------------------------------------------------------------------------------------------
        order = new hwOrder(10003, 2, 3, 30);
        trans = new Transition(10003, 10004);
        wh.addNextHwOrder(order);
        wh.addNextTransition(trans);
        
//--------------------------------------------------------------------------------------------------
        order = new hwOrder(10004, 0, 1, 13);
        order.setPosY(1);
        wh.addNextHwOrder(order);
        trans = new Transition(10004, 10005); //move y para o centro
//--------------------------------------------------------------------------------------------------
        order = new hwOrder(10005, 5, 0, 26);
        order.setPosZ(0);
        wh.addNextHwOrder(order);
        //down_level
//--------------------------------------------------------------------------------------------------
        
        return true;
    }
    
}
