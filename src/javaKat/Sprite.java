package javaKat;  

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.awt.Color;
import java.awt.Graphics2D;

public final class Sprite{
    private Entity owner = null;
    private HashMap<String,BufferedImage> images;
    private HashMap<String,BufferedImage> rotatedImages;
    private HashMap<String,Double> imageAngles;
    private String currentKey;
    private int x;
    private int y;
    private int layer = Integer.MAX_VALUE;
    private DoubleCommand x$ = null;
    private DoubleCommand y$ = null;
    private RotationMode rotationMode = RotationMode.NONE;
    
    public Sprite(int input) {
        layer = input;
        images = new HashMap<>();
        rotatedImages = new HashMap<>();
        imageAngles = new HashMap<>();
        Manager.addSprite(this);
    }
    
    public Sprite(Entity input) {
        this(input.getLayer());       
        owner = input;
        input.attachSprite(this);
    }
    
    protected void update() {
        if (x$ != null) {
            x = (int) x$.value();
        }
        if (y$ != null) {
            y = (int) y$.value();
        }
        rotationUpdate();
    }
    
    private void rotationUpdate() {
        double angle;
        switch (rotationMode) {
            case BY_VELOCITY:
                angle = owner.getRect().getVelocityAngle();
                if (angle != Double.NaN){
                    rotateCurrentImage(angle);
                }
                break;
            case BY_ACCELERATION:
                angle = owner.getRect().getAccelerationAngle();
                if (angle != Double.NaN){
                    rotateCurrentImage(angle);
                }
                break;
            case BY_INPUT:
            case NONE:
                break;
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
        addImage(Loader.loadImage(input),name);
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
    public void setCornerPosition( int inputx, int inputy ){
        setCornerX(inputx);
        setCornerY(inputy);
    }
    
    public void setCornerX(int input) {
        x = input;
        x$ = null;
    }
    
    public void setCornerY(int input) {
        y = input;
        y$ = null;
    }
    
    /**
     * @param input bind code to the x position of this Sprite to automatically recalculate position
     */
    public void setCornerX(DoubleCommand input) {x$ = input;}
    
    /**
     * @param input bind code to the y position of this Sprite to automatically recalculate position
     */
    public void setCornerY(DoubleCommand input) {y$ = input;}
    
    public void setCenterPosition(int inputx, int inputy){
        setCenterX(inputx);
        setCenterX(inputy);
    }
    
    public void setCenterX(DoubleCommand input) {
        x$ = () -> {
            return input.value() - getImage().getWidth()/2;
        };
    }
    
    public void setCenterY(DoubleCommand input) {
        x$ = () -> {
            return input.value() - getImage().getHeight()/2;
        };
    }
    
    public void setCenterX(int input) {
        x = input - getImage().getWidth()/2;
        x$ = null;
    }
    
    public void setCenterY(int input) {
        y = input - getImage().getHeight()/2;
        y$ = null;
    }
    
    public int getCornerX() {return (int) x;}
    
    public int getCornerY() {return (int) y;}
    
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
        if (imageAngles.get(name) == null || imageAngles.get(name) != radians) {
            rotatedImages.put(name,rotateImage(images.get(name), radians));
            imageAngles.put(name, radians);
        }
    }
    
    public void rotateCurrentImage(double radians) {
        rotateImage(currentKey,radians);
    }
            
    public void rotateCurrentImage(int degrees) {
        rotateCurrentImage(Math.toRadians((double) degrees));
    }
    
    private BufferedImage rotateImage(BufferedImage image, double radians){
        if (image != null){
            int centerX = image.getWidth()/2;
            int centerY = image.getHeight()/2;
            BufferedImage temp = new BufferedImage(centerX * 2, centerY * 2,BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = temp.createGraphics();
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
    
    public void setLayer(int input) {
        Manager.removeSprite(this, layer);
        layer = input;
        Manager.addSprite(this, layer);
    }
    
    public int getLayer() {return layer;}
    
    public void setRotationMode( RotationMode input ) {
        switch (input) {
            case BY_ACCELERATION:
            case BY_VELOCITY:
                if (owner == null) {
                    throw new NullPointerException("This sprite is not attached to an Entity");
                }
                break;
            case BY_INPUT:
            case NONE:
                break;
        }
        rotationMode = input;
    }
}
