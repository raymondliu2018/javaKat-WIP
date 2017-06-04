package thePackage.debugger;

import thePackage.Entity;

abstract class TrinketBase extends Entity implements IsDebugger{
    protected TrinketBase(){
        super();
        rect.setLayer(DEBUGGER_LAYER);
    }
    
}
