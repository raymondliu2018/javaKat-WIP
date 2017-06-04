package thePackage;

import java.util.*;
import java.awt.event.*;
import workspace.Script;

public final class GameMaster implements GameData
{
    private static Frame frame;
    private static Timer looper;
    private static Looper loop;
    
    public GameMaster(String name) {
        frame = new Frame(name);
        looper = new Timer();
        Script.init();
        loop = new Looper();
        looper.scheduleAtFixedRate(loop,20,20);
    }
    
    public static Frame getFrame() {return frame;}
    
    protected static void stop() {
        looper.cancel();
        looper.purge();
    }
    
    public static void exit() {
        stop();
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
    
    public static int getHeight() {return getFrame().getHeight();}
    
    public static int getWidth() {return getFrame().getWidth();}
    
    public static void enable() {
        loop.enable();
    }
    
    public static void disable() {
        loop.disable();
    }
}
