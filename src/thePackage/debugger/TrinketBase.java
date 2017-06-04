package thePackage.debugger;

import thePackage.Entity;

abstract class TrinketBase extends Entity implements IsDebugger{
    protected TrinketBase(double xPosition, double yPosition){
        super();
        rect.setLayer(DEBUGGER_LAYER);
        rect.setCornerX(xPosition);
        rect.setCornerY(yPosition);
    }
    
}
