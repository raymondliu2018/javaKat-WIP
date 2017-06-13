
package javaKat.debugger; ;

import javaKat.Manager;
public class Debugger {
    private static boolean enabled = false;
    public static void enabled() {
        enabled = true;
        DebugTool temp = new DebugTool();
        temp.getRect().setCornerX(0);
        temp.getRect().setCornerY(0);
        Manager.queueNewEntity(temp);
    }
}
