package javaKat;  

import workspace.Script;


public final class GameRestarter {
    public static void start() {
        Control.enable();
        Update.enable();
        Collision.enable();
        Manager.enable();
        Camera.enable();
        Drawing.enable();
        Script.init();
        GameMaster.enable();
    }
    
    public static void wipeAllEntities() {
        Manager.wipeAll();
    }
}
