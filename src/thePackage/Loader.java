package thePackage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
public final class Loader implements GameData {
    public static BufferedImage loadImage(String input){
        for (int index = 0; index < GameData.imageFiles.size(); index++){
            if (input.equals(GameData.imageFiles.get(index))) {
                return GameData.images.get(index);
            }
        }
        BufferedImage temp;
        try {
            temp = ImageIO.read(new Utility().getClass().getResource("/" + input));
            
            GameData.imageFiles.add(input);
            GameData.images.add(temp);
            return temp;
        }
        catch(IllegalArgumentException error){
            try {
                temp = (ImageIO.read(new File(input)));
                return temp;
            }
            catch (IOException error$){
                System.out.println("Could not find file " + input);
            }
        }
        catch(IOException error) {
            System.out.println("Could not find file " + input);
        }
        return null;
    }
    
    public static Clip loadClip(String input) {
        for (int index = 0; index < GameData.soundFiles.size(); index++){
            if (input.equals(GameData.soundFiles.get(index))){
                return GameData.sounds.get(index);
            }
        }
        Clip temp;
        AudioInputStream audioIn;
        try {
            audioIn = AudioSystem.getAudioInputStream(new Utility().getClass().getResource("/" + input));
        }
        catch (UnsupportedAudioFileException e){
            System.out.println("Unsupported audio file");
        }
        catch (IOException e){
        }
    }
}