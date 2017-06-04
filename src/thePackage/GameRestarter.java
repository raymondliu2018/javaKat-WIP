package thePackage;

import workspace.Script;


public final class GameRestarter {
    public static void start() {
        Control.enable();
        Update.enable();
        Collision.enable();
        Manager.enable();
        Camera.enable();
        Drawing.enable();
    }
    
    public static void wipeAllEntities() {
        Manager.wipeAll();
    }
    
    public static void finish() {
        Script.init();
        GameMaster.enable();
    }
}
