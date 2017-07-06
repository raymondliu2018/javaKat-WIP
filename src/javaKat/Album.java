package javaKat;  

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.awt.Color;
import java.awt.Graphics2D;

public final class Album{
    private Entity owner = null;
    private HashMap<String,BufferedImage> imageNameMap;
    private HashMap<String,BufferedImage> rotatedImageNameMap;
    private HashMap<String,Double> imageNameAngleMap;
    private String page;
    private int x;
    private int y;
    private int layer = Integer.MAX_VALUE;
    private DoubleCommand x$ = null;
    private DoubleCommand y$ = null;
    private RotationMode rotationMode = RotationMode.NONE;
    
    public Album(int input) {
        layer = input;
        imageNameMap = new HashMap<>();
        rotatedImageNameMap = new HashMap<>();
        imageNameAngleMap = new HashMap<>();
        Manager.addAlbum(this);
    }
    
    public Album(Entity input) {
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
                    Album.this.rotateCurrentPicture(angle);
                }
                break;
            case BY_ACCELERATION:
                angle = owner.getRect().getAccelerationAngle();
                if (angle != Double.NaN){
                    Album.this.rotateCurrentPicture(angle);
                }
                break;
            case BY_INPUT:
            case NONE:
                break;
        }
    }
    
    /**
     * @param input add an Image this Album can display
     * @param name give this Image a name so this Album can access it anytime
     */
    public void addPageWithPicture(BufferedImage input, String name) {
        imageNameMap.put(name,input);
    }
    
    /**
     * @param input add an Image this Album can display
     * @param name give this Image a name so this Album can access it anytime
     */
    public void addPageWithPicture(String input, String name) {
        addPageWithPicture(Loader.loadImage(input),name);
    }
    /**
     * clear all Images this Album has stored
     */
    public void eraseAllPages() {
        imageNameMap.clear();
    }
    
    /**
     * @param d1 set the X position of this Album
     * @param d2 set the Y position of this Album
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
     * @param input bind code to the x position of this Album to automatically recalculate position
     */
    public void setCornerX(DoubleCommand input) {x$ = input;}
    
    /**
     * @param input bind code to the y position of this Album to automatically recalculate position
     */
    public void setCornerY(DoubleCommand input) {y$ = input;}
    
    public void setCenterPosition(int inputx, int inputy){
        setCenterX(inputx);
        setCenterX(inputy);
    }
    
    public void setCenterX(DoubleCommand input) {
        x$ = () -> {
            return input.value() - getPicture().getWidth()/2;
        };
    }
    
    public void setCenterY(DoubleCommand input) {
        x$ = () -> {
            return input.value() - getPicture().getHeight()/2;
        };
    }
    
    public void setCenterX(int input) {
        x = input - getPicture().getWidth()/2;
        x$ = null;
    }
    
    public void setCenterY(int input) {
        y = input - getPicture().getHeight()/2;
        y$ = null;
    }
    
    public int getCornerX() {return (int) x;}
    
    public int getCornerY() {return (int) y;}
    
    /**
     * @return get the current Image this Album is displaying
     */
    public BufferedImage getPicture() {
        if (rotatedImageNameMap.get(page) != null){
            return rotatedImageNameMap.get(page);
        }
        return imageNameMap.get(page);
    }
    
    /**
     * @param input set the Image displayed by this Album to the Image with this name
     */
    public void setPage(String input) {
        page = input;
    }
    
    /**
     * @param name the name of the Image being rotated
     * @param degrees the number of degrees to rotate this image by
     */
    public void rotatePictureOnPage(String name, int degrees){
        rotatePictureOnPage(name, Math.toRadians((double) degrees));
    }
    
    /**
     * @param name the name of the Image being rotated
     * @param radians the number of radians to rotate this image by
     */
    public void rotatePictureOnPage(String name, double radians){
        if (imageNameAngleMap.get(name) == null || imageNameAngleMap.get(name) != radians) {
            rotatedImageNameMap.put(name,rotateImage(imageNameMap.get(name), radians));
            imageNameAngleMap.put(name, radians);
        }
    }
    
    public void rotateCurrentPicture(double radians) {
        rotatePictureOnPage(page,radians);
    }
            
    public void rotateCurrentPicture(int degrees) {
        Album.this.rotateCurrentPicture(Math.toRadians((double) degrees));
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
        Manager.removeAlbum(this, layer);
        layer = input;
        Manager.addAlbum(this, layer);
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
    
    public int getCurrentPageWidth() {
        return getPicture().getWidth();
    }
    
    public int getCurrentPageHeight() {
        return getPicture().getHeight();
    }
    
    public int getPageWidth(String input) {
        return imageNameMap.get(input).getWidth();
    }
    
    public int getPageHeight(String input) {
        return imageNameMap.get(input).getHeight();
    }
}
