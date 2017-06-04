package thePackage;

import java.util.TimerTask;
import workspace.Script;
final class Looper extends TimerTask implements GameData{
    
    private boolean enabled = true;
    
    public void run() {
        if (enabled) {
            Script.loop();
        }
        
        Control.run();
        
        Update.run();
        
        Collision.run();
        
        Manager.run();
        
        Camera.run();
        
        Drawing.run();
    }
    
    protected void enable(){
        enabled = true;
    }
    
    protected void disable() {
        enabled = false;
    }
}