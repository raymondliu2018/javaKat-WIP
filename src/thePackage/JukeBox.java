package thePackage;

import java.util.HashMap;
import javax.sound.sampled.Clip;

public final class JukeBox {
    private HashMap <String,Clip> sounds;
    private Entity owner;
    private String currentSound;
    public JukeBox(Entity input) {
        owner = input;
    }
    
    public void playSound(String name) {
        sounds.get(name);
    }
    
    public void addSound(String input, String name, boolean set){
        
    }
    
    public void addSound(String input, String name) {
        
    }
    
    public String getCurrentSound() {
        return currentSound;
    }
}
