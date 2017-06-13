
package javaKat.debugger; ;

import java.awt.Color;
import java.awt.Font;

public interface DebuggerTag {
    public static String DEBUGGER_MESSAGE = "This method is reserved for the debugging tool";
    public static Color STANDARD_COLOR = Color.GREEN;
    public static Color WARNING_COLOR = Color.RED;
    public static int DEBUGGER_LAYER = 20;
    public static String DEBUGGER_FONT = Font.MONOSPACED;
    public static int DISPLAY_OVERFLOW = 20;
}
