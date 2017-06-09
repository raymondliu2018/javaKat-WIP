package thePackage;

import javax.sound.sampled.Clip;

public final class Speaker {
    private static boolean loop;
    
    public static void setTrack(Clip input) {
        Background.getInstance().set(input);
    }
    
    public static void setTrack(String input) {
        setTrack(Loader.loadClip(input));
    }
}
