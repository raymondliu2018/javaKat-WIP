package thePackage;

import java.util.TimerTask;
import workspace.Script;
final class Looper extends TimerTask implements GameData{
    
    private boolean enabled = true;
    
    public void run() {
        GameMaster.recordStartTime(System.nanoTime());
        if (enabled) {
            Script.loop();
        }
        
        Control.run();
        
        Update.run();
        
        Collision.run();
        
        Manager.run();
        
        Camera.run();
        
        Drawing.run();
        
        GameMaster.recordEndTime(System.nanoTime());
        
        if (GameMaster.getRunTime() > 20 * 1e6){
            System.out.println("WARNING: OVERTIME");
        }
    }
    
    protected void enable(){
        enabled = true;
    }
    
    protected void disable() {
        enabled = false;
    }
}