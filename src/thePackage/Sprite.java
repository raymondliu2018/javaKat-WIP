package thePackage;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.awt.Color;
import java.awt.Graphics2D;

public final class Sprite{
    private HashMap<String,BufferedImage> images;
    private HashMap<String,BufferedImage> rotatedImages;
    private String currentKey;
    private double x;
    private double y;
    private DoubleCommand x$ = null;
    private DoubleCommand y$ = null;
    private Entity owner;
    protected Sprite(Entity input) {
        owner = input;
        images = new HashMap<>();
        rotatedImages = new HashMap<>();
    }
    
    public Sprite() {
        owner = null;
        images = new HashMap<>();
        rotatedImages = new HashMap<>();
    }
    
    protected void update() {
        if (x$ != null) {
            x = x$.value();
        }
        if (y$ != null) {
            y = y$.value();
        }
    }
    
    /**
     * @param input add an Image this Sprite can display
     * @param name give this Image a name so this Sprite can access it anytime
     */
    public void addImage(BufferedImage input, String name) {
        images.put(name,input);
    }
    
    /**
     * @param input add an Image this Sprite can display
     * @param name give this Image a name so this Sprite can access it anytime
     */
    public void addImage(String input, String name) {
        images.put(name,Loader.loadImage(input));
    }
    
    /**
     * @param input add an Image this Sprite can display
     * @param name give this Image a name so this Sprite can access it anytime
     * @param set indicate whether the image should be set as current image
     */
    public void addImage(String input, String name, boolean set) {
        addImage(input,name);
        if (set) {setImage(name);}
    }
    
    /**
     * @param input add an BufferedImage this Sprite can display
     * @param name give this Image a name so this Sprite can access it anytime
     * @param set indicate whether the image should be set as current image
     */
    public void addImage(BufferedImage input, String name, boolean set) {
        addImage(input,name);
        if (set) {setImage(name);}
    }
    
    /**
     * clear all Images this Sprite has stored
     */
    public void clearImages() {
        images.clear();
    }
    
    /**
     * @param d1 set the X position of this Sprite
     * @param d2 set the Y position of this Sprite
     */
    public void setPos( double d1, double d2 ){
        x = d1;
        y = d2;
        x$ = null;
        y$ = null;
    }
    
    public void setX(double input) {
        x = input;
        x$ = null;
    }
    
    public void setY(double input) {
        y = input;
        y$ = null;
    }
    
    /**
     * @param input bind code to the x position of this Sprite to automatically recalculate position
     */
    public void setX(DoubleCommand input) {x$ = input;}
    
    /**
     * @param input bind code to the y position of this Sprite to automatically recalculate position
     */
    public void setY(DoubleCommand input) {y$ = input;}
    
    public double getX() {return x;}
    
    public double getY() {return y;}
    
    /**
     * @return get the current Image this Sprite is displaying
     */
    public BufferedImage getImage() {
        if (rotatedImages.get(currentKey) != null){
            return rotatedImages.get(currentKey);
        }
        return images.get(currentKey);
    }
    
    /**
     * @param input set the Image displayed by this Sprite to the Image with this name
     */
    public void setImage(String input) {
        currentKey = input;
    }
    
    /**
     * @param name the name of the Image being rotated
     * @param degrees the number of degrees to rotate this image by
     */
    public void rotateImage(String name, int degrees){
        rotateImage(name, Math.toRadians((double) degrees));
    }
    
    /**
     * @param name the name of the Image being rotated
     * @param radians the number of radians to rotate this image by
     */
    public void rotateImage(String name, double radians){
        rotatedImages.put(name,rotateImage(images.get(name), radians));
    }
    
    public void rotateCurrentImage(double radians) {
        rotatedImages.put(currentKey,rotateImage(images.get(currentKey), radians));
    }
            
    public void rotateCurrentImage(int degrees) {
        rotateCurrentImage(Math.toRadians((double) degrees));
    }
    
    private BufferedImage rotateImage(BufferedImage image, double radians){
        if (image != null){
            int centerX = image.getWidth()/2;
            int centerY = image.getHeight()/2;
            BufferedImage temp = new BufferedImage(centerX * 2, centerY * 2,BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = (Graphics2D) temp.createGraphics();
            graphics.setColor(new Color(255,255,255,0));
            graphics.fillRect(0,0,centerX * 2, centerY * 2);
            graphics.rotate(radians, centerX, centerY);
            graphics.drawImage(image, 0, 0, null);
            graphics.dispose();
            return temp;
        }
        else {
            return image;
        }
        
    }
    
    /**
     * @return get the Entity that controls this Sprite
     */
    public Entity getOwner() {return owner;}
}
