package thePackage;

import java.util.HashMap;
import javax.sound.sampled.Clip;

public final class JukeBox {
    private HashMap <String,Clip> sounds;
    private Entity owner;
    private String currentSound;
    private Clip currentClip;
    public JukeBox(Entity input) {
        owner = input;
    }
    
    public void playSound(String name) {
        sounds.get(name);
    }
    
    public void addSound(String input, String name, boolean set){
        addSound(input,name);
        if (set) {
            setSound(name);
        }
    }
    
    public void addSound(String input, String name) {
        //sounds.put(name, );
    }
    
    public String getCurrentSound() {
        return currentSound;
    }
    
    public void setSound(String input){
        currentSound = input;
    }
    
    public void playSound(){
        Clip clip = sounds.get(currentSound);
        clip.setFramePosition(0);
        clip.start();
        currentClip = clip;
    }
    
    public void interrupt() {
        if (currentClip != null) {
            currentClip.stop();
        }
        System.out.println("No clip to interrupt");
    }
}
