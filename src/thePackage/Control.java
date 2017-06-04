package thePackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
final class Control extends Manipulator implements GameData{
    protected static void run() {
        if (enabled){
            keyboard();
            mouse();
        }
    }
    
    protected static void keyboard() {    
        ArrayList <KeyEvent> cacheOn = Keyboard.getInstance().getCacheOn();
        ArrayList <KeyEvent> cacheOff = Keyboard.getInstance().getCacheOff();
        if(!cacheOn.isEmpty()){
            int [] intOn = new int[cacheOn.size()];
            for (int x = 0; x < intOn.length; x++) {
                intOn[x] = cacheOn.get(x).getKeyChar() != KeyEvent.CHAR_UNDEFINED ?
                           (int) cacheOn.get(x).getKeyChar() : cacheOn.get(x).getKeyCode();
            }
            Arrays.sort(intOn);
            for( Key k : GameData.keys ) {
                if(Arrays.binarySearch(intOn,k.getInput()) >= 0) {
                    k.toggleOn();
                }
            }
        }
        if(!cacheOff.isEmpty()){
            int [] intOff = new int[cacheOff.size()];
            for (int x = 0; x < intOff.length; x++) {
                intOff[x] = cacheOff.get(x).getKeyChar() != KeyEvent.CHAR_UNDEFINED ?
                            (int) cacheOff.get(x).getKeyChar() : cacheOff.get(x).getKeyCode();
            }
            Arrays.sort(intOff);
            for( Key k : GameData.keys ) {
                if(Arrays.binarySearch(intOff,k.getInput()) >= 0) {
                    k.toggleOff();
                }
            }
        }
    }
    
    protected static void mouse() {
        ArrayList <MouseEvent> cacheOn = Mouse.getInstance().getCacheOn();
        ArrayList <MouseEvent> cacheOff = Mouse.getInstance().getCacheOff();
        ArrayList <MouseEvent> cacheMove = Mouse.getInstance().getCacheMove();
        if(!cacheOn.isEmpty()){
            int [][] intOn = new int[cacheOn.size()][3];
            for (int x = 0; x < intOn.length; x++) {
                intOn[x][0] = cacheOn.get(x).getX();
                intOn[x][1] = cacheOn.get(x).getY();
                intOn[x][2] = cacheOn.get(x).getButton();
            }
            for( Button b : GameData.buttons ) {
                for (int x = 0; x < intOn.length; x++){
                    if (Utility.checkClick(intOn[x][0],intOn[x][1],
                        b.getX(),b.getY(),b.getX$(),b.getY$())){
                        if (b.getInput() == intOn[x][2]){
                            b.toggleOn();
                        }
                    }
                }
            }
        }
        if(!cacheOff.isEmpty()){
            int [][] intOff = new int[cacheOff.size()][3];
            for (int x = 0; x < intOff.length; x++) {
                intOff[x][0] = cacheOff.get(x).getX();
                intOff[x][1] = cacheOff.get(x).getY();
                intOff[x][2] = cacheOff.get(x).getButton();
            }
            for( Button b : GameData.buttons ) {
                for (int x = 0; x < intOff.length; x++){
                    if (Utility.checkClick(intOff[x][0],intOff[x][1],
                        b.getX(),b.getY(),b.getX$(),b.getY$())){
                        if (b.getInput() == intOff[x][2]){
                            b.toggleOff();
                        }
                    }
                }
            }
        }
        if(!cacheMove.isEmpty()){
            int [][] intMove = new int[cacheOn.size()][2];
            for (int x = 0; x < intMove.length; x++) {
                intMove[x][0] = cacheMove.get(x).getX();
                intMove[x][1] = cacheMove.get(x).getY();
            }
            for( Button b : GameData.buttons ) {
                for (int x = 0; x < intMove.length; x++){
                    if (Utility.checkClick(intMove[x][0],intMove[x][1],
                        b.getX(),b.getY(),b.getX$(),b.getY$())){
                        if (b.getInput() == intMove[x][2]){
                            b.toggleOn();
                        }
                    }
                }
            }
        }
    }
}