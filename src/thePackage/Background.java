package thePackage;

import java.awt.image.BufferedImage;
public final class Background extends Entity {
    protected Background(BufferedImage picture) {
        super();
        set(picture);
        rect.setLayer(0);
    }
    
    protected void set(BufferedImage input) {
        if(input != null) {
            GameMaster.getFrame().setNewSize(input.getWidth(null),input.getHeight(null));
        }
        else {
            System.out.println("Frame did not resize: Invalid image");
        }
        sprite.clearImages();
        sprite.addImage(input,"main");
        sprite.setImage("main");
    }
    
    public void subUpdate(){
    }
}