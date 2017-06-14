package javaKat;  

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
        
        GameMaster.tick();
        
        GameMaster.recordEndTime(System.nanoTime());
        
        System.out.println(GameMaster.getRunTime()/1e6);
        
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