package javaKat.debugger; ;

import javaKat.Entity;

abstract class TrinketBase extends Entity implements DebuggerTag{
    protected TrinketBase(double xPosition, double yPosition){
        super();
        rect.setLayer(DEBUGGER_LAYER);
        rect.setCornerX(xPosition);
        rect.setCornerY(yPosition);
    }
    
}
