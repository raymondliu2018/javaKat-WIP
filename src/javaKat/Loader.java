package javaKat;  

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
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
        catch(IllegalArgumentException e){
            try {
                temp = (ImageIO.read(new Utility().getClass().getResource(input)));
                GameData.imageFiles.add(input);
                GameData.images.add(temp);
                return temp;
            }
            catch (IllegalArgumentException e$){
                System.out.println("Could not find file " + input);
            }
            catch (IOException e$){
                System.out.println("File corrupted: " + input);
            }
        }
        catch(IOException e) {
            System.out.println("File corrupted: " + input);
        }
        return null;
    }
    
    public static Clip loadClip(String input) {
        for (int index = 0; index < GameData.soundFiles.size(); index++){
            if (input.equals(GameData.soundFiles.get(index))){
                return GameData.sounds.get(index);
            }
        }
        Clip clip;
        AudioInputStream audioIn;
        try {
            clip = AudioSystem.getClip();
        }
        catch (LineUnavailableException e) {
            System.out.println("Computer Audio System bugged");
            return null;
        }
        try {
            audioIn = AudioSystem.getAudioInputStream(new Utility().getClass().getResource("/" + input));
            clip.open(audioIn);
            GameData.soundFiles.add(input);
            GameData.sounds.add(clip);
            return clip;
        }
        catch (UnsupportedAudioFileException e){
            System.out.println("Unsupported audio file");
            return null;
        }
        catch (IOException e){
            try {
                audioIn = AudioSystem.getAudioInputStream(new Utility().getClass().getResource(input));
                clip.open(audioIn);
                GameData.soundFiles.add(input);
                GameData.sounds.add(clip);
                return clip;
            }
            catch(UnsupportedAudioFileException e$){
                System.out.println("Unsupported audio file");
                return null;
            }
            catch(IOException e$) {
                System.out.println("Corrupted File: " + input);
                return null;
            }
            catch (LineUnavailableException e$) {
                System.out.println("Computer Audio System Bugged");
                return null;
            }
        } 
        catch (LineUnavailableException e) {
            System.out.println("Computer Audio System Bugged");
            return null;
        }
    }
}