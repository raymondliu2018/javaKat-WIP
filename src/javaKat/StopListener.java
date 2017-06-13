package javaKat;  

import java.awt.event.*;
import javaKat.GameMaster;
final class StopListener extends WindowAdapter{
    public void windowClosing(WindowEvent e) {
        GameMaster.stop();
    }
}