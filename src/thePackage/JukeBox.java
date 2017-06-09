package thePackage;  

import java.util.HashMap;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public final class JukeBox {
    private HashMap <String,Clip> clipMap;
    private Entity owner;
    private String currentString;
    private Clip currentClip;
    private HashMap <String,Float> volumeMap;
    
    public JukeBox(Entity input) {
        owner = input;
        clipMap = new HashMap<>();
        volumeMap = new HashMap<>();
    }
    
    public JukeBox() {
        this(null);
    }
    
    public void addTrack(String input, String name, boolean set){
        addTrack(Loader.loadClip(input),name,set);
    }
    
    public void addTrack(String input, String name) {
        addTrack(Loader.loadClip(input),name);
    }
    
    public void addTrack(Clip input, String name) {
        clipMap.put(name,input);
        setVolumeOfNamedClip(name,1.0f);
    }
    public void addTrack(Clip input, String name, boolean set) {
        addTrack(input,name);
        if (set) {
            setCurrentTrack(name);
        }
    }
    
    public void addTrack(String input, String name, float volume) {
        addTrack(Loader.loadClip(input),name,volume);
    }
    
    public void addTrack(Clip input, String name, float volume) {
        addTrack(input,name);
        setVolumeOfNamedClip(name,volume);
    }
    
    public void addTrack(Clip input, String name, float volume, boolean set) {
        addTrack(input,name,volume);
        if (set) {
            setCurrentTrack(name);
        }
    }
    
    public void addTrack(String input, String name, float volume, boolean set) {
        addTrack(Loader.loadClip(input),name,volume,set);
    }
    
    public String getCurrentTrack() {
        return currentString;
    }
    
    public void setCurrentTrack(String input){
        currentString = input;
        currentClip = clipMap.get(currentString);
    }
    
    public void playCurrentTrack(){
        if (currentString != null){
            currentClip = clipMap.get(currentString);
            currentClip.setFramePosition(0);
            volumeControl();
            currentClip.start();
        }
        else {
            System.out.println("No clip selected");
        }
    }
    
    private void volumeControl() {
        FloatControl floatControl = (FloatControl) currentClip.getControl(FloatControl.Type.MASTER_GAIN);
        floatControl.setValue(volumeMap.get(currentString));
    }
    
    public void setVolumeOfNamedClip(String name, float input) {
        if (input < 0.0f || input > 1.0f){
            System.out.println("Volume must be between 0.0 and 1.0");
            return;
        }
        volumeMap.put(name, decibelConversion(input));
    }
    
    public void setVolumeOfCurrentClip(float input) {
        setVolumeOfNamedClip(currentString,input);
        volumeControl();
    }
    
    private float decibelConversion(float input) {
        return (float) Math.log10(input) * 20.0f;
    }
    
    public void interruptCurrentTrack() {
        if (currentClip != null) {
            currentClip.stop();
        }
        else {
            System.out.println("No clip to interrupt");
        }
    }
    
    public void proceedCurrentTrack(){
        if (currentClip != null) {
            currentClip.start();
        }
        else {
            System.out.println("No clip to continue");
        }
    }
    
    public void loopCurrentTrack() {
        if (currentClip != null) {
            currentClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        else {
            System.out.println("No clip to loop");
        }
    }
    
    public Entity getOwner() {
        return owner;
    }
}
