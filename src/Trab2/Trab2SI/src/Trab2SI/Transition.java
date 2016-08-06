package Trab2SI;

/**
 *
 * @author DuarteG
 */
public class Transition {
    
    int from; //>0
    int to; //>0
    int status; // -1 - waiting; 0 - running; 1 - ready; 2- done
    
    public Transition(int _from, int _to) {
        
        this.from = _from;
        this.to = _to;
        this.status = -1;
        
    }
    
    public int getFrom() {
        return this.from;
    }
    
    public int getTo() {
        return this.to;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public void setStatus(int _status) {
        this.status = _status;
    }
    
}
