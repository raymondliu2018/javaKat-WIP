package thePackage;

import java.awt.image.BufferedImage;
import javax.sound.sampled.Clip;
import thePackage.debugger.DebuggerTag;
public final class Background extends Entity implements DebuggerTag {
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
        sprite.addImage(input,"main",true);
    }
    
    protected void set(Clip input) {
        if (input != null) {
            
        }
        jukeBox.addSound(input,"main",true);
        jukeBox.playSound();
    }
    
    public void subUpdate(){
    }
}