package thePackage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
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
}