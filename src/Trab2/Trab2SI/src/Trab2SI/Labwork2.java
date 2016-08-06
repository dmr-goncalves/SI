package Trab2SI;

import java.util.Scanner;

/**
 *
 * @author DuarteG
 */
public class Labwork2 {
    
    static Warehouse wh;
    public static Diagnostic diag;
    
    private static int menu() {
        System.out.println("            MENU                  ");
        System.out.println("1 - Move  x , z                   ");
        System.out.println("2 - Put Piece                     ");
        System.out.println("3 - Get Piece                     ");
        System.out.println("4 - Swap piece from x,z to x2, z2 ");
        System.out.println("                                  ");
        Scanner reader = new Scanner(System.in);
        int opcion = reader.nextInt();    //Le do menu
        return opcion;
    }
    
    public static void main(String args[]) {
        wh = new Warehouse();
        Planner planner = new Planner(wh);
        Dispatch dis = new Dispatch(wh);
        Monitor mon = new Monitor(wh);
        ErrorRecover er = new ErrorRecover(wh);
        diag = new Diagnostic(wh);
        Plan thisPlan = new Plan();
        
        while (true) {
            
            int op = menu();
            
            thisPlan = planner.createPlan(op);
            while (!thisPlan.HwOrderDone()) {
                planner.go(thisPlan);
                dis.go();
                
                if (!mon.go()) {
                    diag.go();
                    er.go();
                }
            }
        }
        
    }
}
