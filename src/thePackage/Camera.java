package thePackage;

import java.util.ArrayList;
import java.awt.image.BufferedImage;
public final class Camera extends Manipulator implements GameData
{
    private static double centerX;
    private static double centerY;
    private static double backgroundHeight;
    private static double backgroundWidth;
    private static ArrayList<Entity> focused;
    private static Background background;
    
    protected static void run()
    {
        if (enabled) {
            centerX = 0.0;
            centerY = 0.0;
            focused = GameData.focusedEntities;
            for(int i = 0; i < focused.size(); i++){
                Rect r = focused.get(i).getRect();
                centerX += r.getCenterX();
                centerY += r.getCenterY();
            }
            if (focused.size() > 0) {
                centerX = centerX/focused.size();
                centerY = centerY/focused.size();
            }
            else {
                centerX = GameMaster.getFrame().getWidth()/2;
                centerY = GameMaster.getFrame().getHeight()/2;
            }
            if (centerX < GameMaster.getFrame().getWidth()/2) {
                centerX = GameMaster.getFrame().getWidth()/2;
            }
            if (centerY < GameMaster.getFrame().getHeight()/2) {
                centerY = GameMaster.getFrame().getHeight()/2;
            }
            if (centerX > backgroundWidth - GameMaster.getFrame().getWidth()/2){
                centerX = backgroundWidth - GameMaster.getFrame().getWidth()/2;
            }
            if (centerY > backgroundHeight - GameMaster.getFrame().getHeight()/2){
                centerY = backgroundHeight - GameMaster.getFrame().getHeight()/2;
            }
        }
    }
    
    protected static double getShiftX() { return centerX - GameMaster.getFrame().getWidth()/2;}
    
    protected static double getShiftY() { return centerY - GameMaster.getFrame().getHeight()/2;}
    
    public static void setBackground(String input) {
        setBackground(Loader.loadImage(input));
    }
    
    public static void setBackground(BufferedImage input) {
        if (background != null) {Manager.removeEntity(background);}
        background = new Background(input);
        Manager.queueNewEntity(background);
        if (input != null) {
            backgroundWidth = input.getWidth(null);
            backgroundHeight = input.getHeight(null);
        }
        else {
            backgroundWidth = GameMaster.getFrame().getWidth();
            backgroundHeight = GameMaster.getFrame().getHeight();
        }
    }
    
    public static void lockFrameSize(int i1, int i2){
        GameMaster.getFrame().setNewSize(i1, i2);
    }
    
    public static int getMapHeight() {return (int)backgroundHeight;}
    
    public static int getMapWidth() {return (int)backgroundWidth;}
}