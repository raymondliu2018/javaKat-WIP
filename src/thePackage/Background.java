package thePackage;  

import java.awt.image.BufferedImage;
import javax.sound.sampled.Clip;
import thePackage.debugger.DebuggerTag;
final class Background extends Entity implements DebuggerTag {
    protected static Background instance;
    private Background() {
        super();
        rect.setLayer(0);
    }
    
    public static Background getInstance() {
        if (instance == null) {
            instance = new Background();
            Manager.queueNewEntity(instance);
        }
        return instance;
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
        jukeBox.addTrack(input,"main",true);
        jukeBox.loopCurrentTrack();
    }
    
    protected void set(float input) {
        jukeBox.setVolumeOfCurrentClip(input);
    }
    
    public void subUpdate(){
    }
}