package thePackage;  

import javax.sound.sampled.Clip;

public final class Speaker {
    private static boolean loop;
    
    public static void setBackground(Clip input) {
        Background.getInstance().set(input);
    }
    
    public static void setBackground(String input) {
        setBackground(Loader.loadClip(input));
    }
    
    public static void setBackground(Clip input, float volume) {
        setBackground(input);
        Background.getInstance().set(volume);
    }
    
    public static void setBackground(String input, float volume) {
        setBackground(Loader.loadClip(input),volume);
    }
}
