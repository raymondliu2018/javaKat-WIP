package thePackage;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

public final class Utility implements GameData{
    protected static void addSprite( Sprite sprite, int index ) {
        while (GameData.sprites.size() <= index + 1) {
            GameData.sprites.add( new CopyOnWriteArrayList<>() );
        }
        GameData.sprites.get(index).add(sprite);
    }
    
    protected static void addLayer( Entity entity, int index ) {
        while (GameData.layers.size() <= index + 1) {
            GameData.layers.add( new ArrayList<>() );
        }
        GameData.layers.get(index).add(entity.getRect());
    }
    
    public static BufferedImage scaleImage( BufferedImage input, double scale ){
        if (input != null) {
            int height = (int)(input.getHeight(null) * scale);
            int width = (int)(input.getWidth(null) * scale);
            return scaleImage(input, width, height);
        }
        return null;
    }
    
    public static BufferedImage scaleImage( String input, double scale ){
        return scaleImage(Loader.loadImage(input), scale);
    }
    
    public static BufferedImage scaleImage( String input, int width, int height ){
        return scaleImage(Loader.loadImage(input), width, height);
    }
    
    public static BufferedImage scaleImage( BufferedImage input, int width, int height ) {
        if (input != null) {
            BufferedImage result = new BufferedImage(width, height, input.getType());
            Graphics graphics = result.getGraphics();
            graphics.drawImage(input, 0, 0, width, height, null);
            graphics.dispose();
            return result;
        }
        return null;
    }
    
    protected static boolean checkClick(int x0, int y0, int x1, int y1, int x2, int y2){
        if ( (x0 > x1 && x0 < x2) && (y0 > y1 && y0 < y2) ){ return true;}
        return false;
    }
}
