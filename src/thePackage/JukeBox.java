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
    
    public void addSound(String input, String name, boolean set){
        addSound(Loader.loadClip(input),name,set);
    }
    
    public void addSound(String input, String name) {
        addSound(Loader.loadClip(input),name);
    }
    
    public void addSound(Clip input, String name) {
        clipMap.put(name,input);
        setVolumeOfNamedClip(name,1.0f);
    }
    public void addSound(Clip input, String name, boolean set) {
        addSound(input,name);
        if (set) {
            setSound(name);
        }
    }
    
    public void addSound(String input, String name, float volume) {
        addSound(Loader.loadClip(input),name,volume);
    }
    
    public void addSound(Clip input, String name, float volume) {
        addSound(input,name);
        setVolumeOfNamedClip(name,volume);
    }
    
    public void addSound(Clip input, String name, float volume, boolean set) {
        addSound(input,name,volume);
        if (set) {
            setSound(name);
        }
    }
    
    public void addSound(String input, String name, float volume, boolean set) {
        addSound(Loader.loadClip(input),name,volume,set);
    }
    
    public String getCurrentSound() {
        return currentString;
    }
    
    public void setSound(String input){
        currentString = input;
    }
    
    public void playSound(){
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
    
    public void interrupt() {
        if (currentClip != null) {
            currentClip.stop();
        }
        else {
            System.out.println("No clip to interrupt");
        }
    }
    
    public void proceed(){
        if (currentClip != null) {
            currentClip.start();
        }
        else {
            System.out.println("No clip to continue");
        }
    }
    
    public Entity getOwner() {
        return owner;
    }
}
