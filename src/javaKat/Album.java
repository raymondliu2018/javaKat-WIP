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
    private double angle;
    private DoubleCommand x$ = null;
    private DoubleCommand y$ = null;
    private RotationMode rotationMode = RotationMode.NONE;
    private MovementMode movementMode = MovementMode.NONE;
    
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
        movementUpdate();
        rotationUpdate();
    }
    
    private void rotationUpdate() {
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
    
    private void movementUpdate() {
        switch (movementMode) {
            case BY_RECT:
                x = (int) owner.getRect().getCornerX();
                y = (int) owner.getRect().getCornerY();
                break;
            case BY_INPUT:
                if (x$ != null) {
                    x = (int) x$.value();
                }
                if (y$ != null) {
                    y = (int) y$.value();
                }
                break;
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
        checkMovementMode();
        x = input;
        x$ = null;
    }
    
    public void setCornerY(int input) {
        checkMovementMode();
        y = input;
        y$ = null;
    }
    
    /**
     * @param input bind code to the x position of this Album to automatically recalculate position
     */
    public void setCornerX(DoubleCommand input) {
        checkMovementMode();
        x$ = input;
    }
    
    /**
     * @param input bind code to the y position of this Album to automatically recalculate position
     */
    public void setCornerY(DoubleCommand input) {
        checkMovementMode();
        y$ = input;
    }
    
    public void setCenterPosition(int inputx, int inputy){
        setCenterX(inputx);
        setCenterX(inputy);
    }
    
    public void setCenterX(DoubleCommand input) {
        checkMovementMode();
        x$ = () -> {
            return input.value() - getPicture().getWidth()/2;
        };
    }
    
    public void setCenterY(DoubleCommand input) {
        checkMovementMode();
        x$ = () -> {
            return input.value() - getPicture().getHeight()/2;
        };
    }
    
    public void setCenterX(int input) {
        setCornerX(input - getPicture().getWidth()/2);
    }
    
    public void setCenterY(int input) {
        setCornerY(input - getPicture().getHeight()/2);
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
    
    private void checkMovementMode() {
        if (movementMode != MovementMode.BY_INPUT) {
            throw new IllegalArgumentException("Movement mode not by input; cannot set coordinates");
        }
    }
    
    public void setMovementMode(MovementMode input) {
        switch(input) {
            case BY_RECT:
                if (owner == null) {
                    throw new IllegalArgumentException("This album is not attached to an Entity to calculate position from");
                }
                break;
        }
        movementMode = input;
    }
}
