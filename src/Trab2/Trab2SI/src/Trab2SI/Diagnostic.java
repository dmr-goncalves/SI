package Trab2SI;

import CLIPSJNI.*;

/**
 *
 * @author DuarteG
 */
public class Diagnostic {
    
    private Environment clips;
    Hardware h;
    public int erroId = 0;
    Warehouse wh;
    
    public Diagnostic(Warehouse _wh) {
        this.wh = _wh;
        clips = new Environment();
        clips.load("rulesCLIPS.CLP");
        clips.reset();
        
    }
    
    int getError() {
        try {
            int size = clips.eval("(find-all-facts ((?E Erro)) TRUE)").size();
            if (size != 0) {
                return Integer.parseInt(clips.eval("(find-all-facts ((?E Erro)) TRUE)").get(0).getFactSlot("id").toString());
            }
        } catch (Exception ex) {
            System.out.println("Erro a ir buscar os erros");
        }
        return 0;
    }
    
    public void go() {
        System.out.println("Diagnostico");
        clips.reset();
        clips.run();
        erroId = getError();
        wh.errorType = erroId;
        
        if (erroId > 0) {
            System.out.print("Erro Diagnosticado. ID: ");
            System.out.println(erroId);
        }
    }
    
}
