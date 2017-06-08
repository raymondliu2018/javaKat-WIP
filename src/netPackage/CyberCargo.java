package netPackage;

import java.io.Serializable;

class CyberCargo implements Serializable{
    private CyberCargo() {
        
    }
    
    protected static void unpack(CyberCargo input) {
        
    }
    
    protected static CyberCargo pack() {
        return new CyberCargo();
    }
}
