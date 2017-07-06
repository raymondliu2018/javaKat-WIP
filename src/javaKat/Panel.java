package javaKat;  

import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;

final class Panel extends JPanel {
    private CopyOnWriteArrayList <CopyOnWriteArrayList<Album>> stack1 = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList <Tag> stack2 = new CopyOnWriteArrayList<>();
    protected void preparePaint(CopyOnWriteArrayList <CopyOnWriteArrayList<Album>> input) {stack1 = input;}
    
    protected void prepareWrite(CopyOnWriteArrayList<Tag> input){stack2 = input;}
        
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for ( CopyOnWriteArrayList <Album> x : stack1 ) {
            for (Album sprite: x) {
                BufferedImage image = sprite.getPicture();
                if (image != null ) {
                    g.drawImage(sprite.getPicture(),
                            (sprite.getCornerX() - Camera.getShiftX()),
                            (sprite.getCornerY() - Camera.getShiftY()),
                            null);
                }
                else{
                    //System.out.println(sprite.toString() + " was null");
                }
            }
        }
        for ( Tag t: stack2 ) {
            g.setColor(t.getColor());
            g.setFont(t.getFont());
            g.drawString(t.getMessage(),
                    (int) (t.getCenterX() - Camera.getShiftX() - (t.getRoughWidth()/2)),
                    (int) (t.getCenterY() - Camera.getShiftY() + (t.getRoughHeight()/2)));
        }
        repaint();
    }
}