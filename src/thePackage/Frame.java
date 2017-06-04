package thePackage;

import java.awt.*;
import javax.swing.*;
public final class Frame extends JFrame
{
    private Panel panel;
    protected Frame(String name){
        panel = new Panel();
        add(panel);
        addKeyListener(Keyboard.getInstance());
        addMouseListener(Mouse.getInstance());
        addMouseMotionListener(Mouse.getInstance());
        addWindowListener(new StopListener());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(name);
        setVisible(true);
        pack();
    }
    
    public void setNewSize(double x, double y) {
        panel.setPreferredSize(new Dimension((int)x, (int)y));
        pack();
    }
    
    public int getWidth() {return panel.getWidth();}
    
    public int getHeight() {return panel.getHeight();}
    
    public Panel getPanel() {return panel;}
}
