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
        for ( CopyOnWriteArrayList <Album> albums : stack1 ) {
            for (Album album: albums) {
                BufferedImage image = album.getPicture();
                if (image != null ) {
                    g.drawImage(album.getPicture(),
                            (album.getCornerX() - Camera.getShiftX()),
                            (album.getCornerY() - Camera.getShiftY()),
                            null);
                }
                else{
                    //System.out.println(album.toString() + "was null");
                }
            }
        }
        for ( Tag tag: stack2 ) {
            g.setColor(tag.getColor());
            g.setFont(tag.getFont());
            g.drawString(tag.getMessage(),
                    (int) (tag.getCornerX() - Camera.getShiftX()),
                    (int) (tag.getCornerY() - Camera.getShiftY() + (tag.getRoughHeight())));
        }
        repaint();
    }
}