package hardware;

import CLIPSJNI.*;

/**
 *
 * @author DuarteG
 */
public class Monitor {
    
    private Environment clips;
    Hardware h;
    int erroId = 0;
    Warehouse wh;
    
    public Monitor(Warehouse _wh) {
        
        this.wh = _wh;
        
        clips = new Environment();
        clips.load("rulesCLIPS.CLP");
        clips.reset();
        
    }
    
    int getErro() {
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
    
    private void errorConditions() {
        String s0, s1, s2, s3;
        char bitstring1, bitstring2, bitstringZ0, bitstringX0, bitstringX9, bitstringElevator;
        byte b0, b1, b2, b3;
        b0 = (byte) (wh.sensoresInfo[0]);
        b1 = (byte) (wh.sensoresInfo[3]);
        b2 = (byte) (wh.sensoresInfo[2]);
        b3 = (byte) (wh.sensoresInfo[1]);
        s0 = String.format("%8s", Integer.toBinaryString(b0 & 0xFF));
        s0 = s0.replace(' ', '0');
        s1 = String.format("%8s", Integer.toBinaryString(b1 & 0xFF));
        s1 = s1.replace(' ', '0');
        s2 = String.format("%8s", Integer.toBinaryString(b2 & 0xFF));
        s2 = s2.replace(' ', '0');
        s3 = String.format("%8s", Integer.toBinaryString(b3 & 0xFF));
        s3 = s3.replace(' ', '0');
        bitstringX0 = s0.charAt(7);
        bitstringX9 = s3.charAt(6);
        bitstring1 = s1.charAt(7);
        bitstring2 = s1.charAt(6);
        bitstringZ0 = s2.charAt(1);
        bitstringElevator = s2.charAt(0);
        
        //ERRO1
        if (bitstring1 == '0') { //Peça na estação da esquerda
            clips.eval("(assert (piece_inStation (pos Left)))");
        } else if (bitstring2 == '0') { //Peça na estação da direita
            clips.eval("(assert (piece_inStation (pos Right)))");
        } else { //Não há peça na estação
            clips.eval("(assert (piece_inStation (pos false)))");
        }
        
        if (bitstringZ0 == '0') //Z= 0
        {
            if (bitstringX0 == '0') {//X = 0
                clips.eval("(assert (elevator_atStation (pos Left)))"); //Left Station
            } else if (bitstringX9 == '0') { //X = 9
                clips.eval("(assert (elevator_atStation (pos Right)))");//Right Station
            } else {
                clips.eval("(assert (elevator_atStation (pos false)))");//No station
            }
        } else {
            clips.eval("(assert (elevator_atStation (pos false)))");//No station
        }
        //ERRO2
        
        if (wh.loadDone) {
            clips.eval("(assert ( STATION_LOAD_DONE (status true)))");
            
        } else {
            clips.eval("(assery ( STATION_LOAD_DONE (status false)))");
        }
        
        if (bitstringElevator == '1') { //Has piece
            clips.eval("(assert ( piece_inElevator (status true)))");
        } else {
            clips.eval("(assert ( piece_inElevator (status false)))"); //Hasn't piece
        }
        
    }
    
    public boolean go() {
        System.out.println("Monitor a correr");
        clips.reset();
        errorConditions();
        clips.run();
        erroId = getErro();
        if (erroId > 0) {
            
            System.out.print("Erro detectado. ID: ");
            System.out.println(erroId);
            return false;
        } else {
            return true;
        }
    }
    
}
