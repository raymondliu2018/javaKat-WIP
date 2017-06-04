
package thePackage.debugger;

import thePackage.Manager;
public class Debugger {
    private static boolean enabled = false;
    public static void enabled() {
        enabled = true;
        Manager.queueNewEntity(new DebugTool());
    }
}
