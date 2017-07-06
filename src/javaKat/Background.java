package javaKat;  

import java.awt.image.BufferedImage;
import javax.sound.sampled.Clip;
import javaKat.debugger.DebuggerTag;
final class Background extends Entity implements DebuggerTag {
    protected static Background instance;
    private static Sprite sprite;
    private Background() {
        super();
        rect.setLayer(0);
        sprite = new Sprite(this);
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
        sprite.addImage(input,"main");
        sprite.setImage("main");
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