package javaKat;  

import java.util.*;
import java.awt.event.*;
import IE.CyberBridge;
import workspace.Script;

public final class GameMaster implements GameData
{
    private static Frame frame;
    private static Timer looper;
    private static Looper loop;
    private static long loopStartTime;
    private static long loopEndTime;
    
    private GameMaster(String name) {
        CyberBridge.setSail(this);
        frame = new Frame(name);
        looper = new Timer();
        Script.init();
        loop = new Looper();
        looper.scheduleAtFixedRate(loop,20,20);
    }
    
    public static Frame getFrame() {return frame;}
    
    public static void start(String name) {
        new GameMaster(name);
    }
    
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
    
    protected static long getRunTime() {
        return loopEndTime - loopStartTime;
    }
    
    protected static void recordStartTime(long input) {
        loopStartTime = input;
    }
    
    protected static void recordEndTime(long input){
        loopEndTime = input;
    }
}
