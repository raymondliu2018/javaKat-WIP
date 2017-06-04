package thePackage;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

final class Mouse implements MouseListener, MouseMotionListener{
    private static Mouse instance = null;
    private ArrayList<MouseEvent> cacheOn;
    private ArrayList<MouseEvent> cacheOff;
    private ArrayList<MouseEvent> cacheMove;
    
    protected Mouse() {
        cacheOn = new ArrayList<>();
        cacheOff = new ArrayList<>();
        cacheMove = new ArrayList<>();
    }
    
    public static Mouse getInstance() {
        if (instance == null) {
            instance = new Mouse();
        }
        return instance;
    }
    
    public void mouseExited(MouseEvent input){}
    
    public void mouseEntered(MouseEvent input){}
    
    public void mouseClicked(MouseEvent input){}
    
    public void mouseReleased(MouseEvent input){
        cacheOff.add(input);
    }
    
    public void mousePressed(MouseEvent input){
        cacheOn.add(input);
    }
    
    public void mouseMoved(MouseEvent input){
        cacheMove.add(input);
    }
    
    public void mouseDragged(MouseEvent input){}
    
    protected ArrayList<MouseEvent> getCacheOn() {
        ArrayList<MouseEvent> temp = new ArrayList<>(cacheOn);
        cacheOn.clear();
        return temp;
    }
    
    protected ArrayList<MouseEvent> getCacheOff() {
        ArrayList<MouseEvent> temp = new ArrayList<>(cacheOff);
        cacheOff.clear();
        return temp;
    }
    
    public ArrayList<MouseEvent> getCacheMove() {
        ArrayList<MouseEvent> temp = new ArrayList<>(cacheMove);
        cacheMove.clear();
        return temp;
    }
}