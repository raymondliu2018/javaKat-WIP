package thePackage;

import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;

final class Panel extends JPanel {
    private CopyOnWriteArrayList <CopyOnWriteArrayList<Sprite>> stack1 = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList <Text> stack2 = new CopyOnWriteArrayList<>();
    protected void preparePaint(CopyOnWriteArrayList <CopyOnWriteArrayList<Sprite>> input) {stack1 = input;}
    
    protected void prepareWrite(CopyOnWriteArrayList<Text> input){stack2 = input;}
        
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for ( CopyOnWriteArrayList <Sprite> x : stack1 ) {
            for (Sprite sprite: x) {
                BufferedImage image = sprite.getImage();
                if (image != null ) {
                    g.drawImage(sprite.getImage(),
                                (int) (sprite.getX() - Camera.getShiftX()),
                                (int) (sprite.getY() - Camera.getShiftY()),
                                null);
                }
                else{
                    //System.out.println(sprite.toString() + " was null");
                }
            }
        }
        for ( Text t: stack2 ) {
            g.setColor(t.getColor());
            g.setFont(t.getFont());
            g.drawString(t.getMessage(),
                         (int) (t.getCenterX() - Camera.getShiftX() - (g.getFontMetrics().stringWidth(t.getMessage())/2)),
                         (int) (t.getCenterY() - Camera.getShiftY() - (g.getFontMetrics().getHeight()/2)));
        }
        repaint();
    }
}