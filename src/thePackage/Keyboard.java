package thePackage;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public final class Keyboard implements KeyListener{
    private static Keyboard instance = null;
    private ArrayList <KeyEvent> cacheOn;
    private ArrayList <KeyEvent> cacheOff;
    protected Keyboard(){
        cacheOn = new ArrayList<>();
        cacheOff = new ArrayList<>();
    }
    
    protected static Keyboard getInstance() {
        if( instance == null ) {
            instance = new Keyboard();
        }
        return instance;
    }
    
    public void keyPressed(KeyEvent input) {
        cacheOn.add(input);
    }
    
    public void keyReleased(KeyEvent input) {
        cacheOff.add(input);
    }
    
    public void keyTyped(KeyEvent input) {
    }
    
    protected ArrayList<KeyEvent> getCacheOn() {
        ArrayList<KeyEvent> temp = new ArrayList<>(cacheOn);
        cacheOn.clear();
        return temp;
    }
    
    protected ArrayList<KeyEvent> getCacheOff() {
        ArrayList<KeyEvent> temp = new ArrayList<>(cacheOff);
        cacheOff.clear();
        return temp;
    }
}
