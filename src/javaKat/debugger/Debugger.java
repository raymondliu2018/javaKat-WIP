package javaKat.debugger; ;

public class Debugger {
    private static boolean enabled = false;
    public static void enabled() {
        enabled = true;
        DebugTool temp = new DebugTool();
    }
    
    public static boolean isEnabled() {
        return enabled;
    }
}
