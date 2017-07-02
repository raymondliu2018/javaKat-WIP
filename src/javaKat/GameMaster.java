package javaKat;  

import java.util.*;
import java.awt.event.*;
import workspace.Script;

public final class GameMaster implements GameData
{
    private static Frame frame;
    private static Timer looper;
    private static Looper loop;
    private static long loopStartTime;
    private static long loopEndTime;
    private static GameMaster instance;
    private static int gameTick = 0;
    
    private GameMaster(String name) {
        frame = new Frame(name);
        looper = new Timer();
    }
    
    private void init() {
        Script.init();
        loop = new Looper();
        looper.scheduleAtFixedRate(loop,20,20);
    }
    
    private GameMaster getInstance() { return instance; }
    
    public static Frame getFrame() {return frame;}
    
    public static void start(String name) {
        instance = new GameMaster(name + " - javaKat");
        instance.init();
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
    
    public static void pause(int input) {
        try {
            Thread.sleep((long) input);
        }
        catch (InterruptedException e) {
            throw new RuntimeException("Internal error");
        }
    }
    
    protected static void tick() {
        gameTick += 1;
    }
    
    public int gameTick() {
        return gameTick;
    }
}
