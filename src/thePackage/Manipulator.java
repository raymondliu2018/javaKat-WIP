package thePackage;

abstract class Manipulator {
    protected static boolean enabled = true;
    public static void disable() {
        enabled = false;
    }
    
    public static void enable() {
        enabled = true;
    }
}
