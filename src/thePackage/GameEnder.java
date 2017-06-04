package thePackage;

public final class GameEnder {
    public static void disableUpdates() {
        Update.disable();
        Collision.disable();
    }
    
    public static void disableRepainting() {
        Drawing.disable();
        Camera.disable();
    }
    
    public static void disableControls(){
        Control.disable();
    }
    
    public static void disableAddingAndRemovingEntities() {
        Manager.disable();
    }
    
    public static void start() {
        GameMaster.disable();
    }
    
    public static void disableAll() {
        disableUpdates();
        disableRepainting();
        disableControls();
        disableAddingAndRemovingEntities();
    }
}
