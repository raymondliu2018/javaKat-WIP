package thePackage;

/**
 * Every game character should be a subclass of Entity
 * 
 * Entities have one Rect, one Sprite, one team, and a timer
 * 
 * Entities have arrays of keys and stats
 */

import java.util.ArrayList;
import java.util.HashMap;
import thePackage.debugger.IsDebugger;

public abstract class Entity
{
    private HashMap<String,KeyCommand> keyMapPressed;
    private HashMap<String,KeyCommand> keyMapReleased;
    private ArrayList<Key> keys;
    private ArrayList<Button> buttons;
    protected Sprite sprite;
    protected boolean focused = false;
    protected Rect rect;
    protected int timer;
    protected ArrayList<Text> stats;
    protected boolean rotating = false;
    
    public Entity() {
        rect = new Rect(this);
        timer = 0;
        
        sprite = new Sprite(this);
        sprite.setX(() -> rect.getCornerX());
        sprite.setY(() -> rect.getCornerY());
        
        stats = new ArrayList<>();
        keys = new ArrayList<>();
        buttons = new ArrayList<>();
        keyMapPressed = new HashMap<>();
        keyMapReleased = new HashMap<>();
    }
    
    //Sprite
    /**
     * @return get the Sprite element of this Entity
     */
    public final Sprite getSprite() {return sprite;}
    
    /**
     * @return get whether or not the Entity is being focused on by the Camera
     */
    public final boolean getFocused() {return focused;}
    
    /**
     * Sets this entity's hitbox to the size of its current image
     */
    public final void resize() {
        if (sprite.getImage() != null){
            rect.setSize(sprite.getImage().getWidth(null),sprite.getImage().getHeight(null));
        }
    }
    
    //Rect
    /**
     * @param input set the Rect element of this Entity
     */
    public final void setRect(Rect input) {rect = input;}
    
    /**
     * @return get the Rect element of this Entity
     */
    public final Rect getRect() {return rect;}
    
    /**
     * @return get the layer number of this Entity
     */
    public final int getLayer() {return rect.getLayer();}
        
    //Update
    final void update() {
        tick();
        rect.update();
        rotationUpdate();
        subUpdate();
    }
    
    final void rotationUpdate() {
        if (rotating){
            double temp = rect.getAngle();
            if (temp != Double.MAX_VALUE){
                sprite.rotateCurrentImage(temp);
            }
        }
    }
    
    final void tick() {timer += 1;}
    
    /**
     * override this method for code that should be run every time the game loops
     */
    public abstract void subUpdate();
    
    /**
     * override this method to perform actions when this Entity collides with another Entity
     * @param input the other Entity this Entity collided with
     * @return return true to simulate a normal collision
     */
    public boolean collidedWith(Entity input) {
        return false;}
    
    //Stats
    /**
     * @param input Text element this Entity will display 
     */
    public final void addStat( Text input ) {
        stats.add(input);
        Manager.addStat(input);
    }
    
    /**
     * @return get an Arraylist of Text elements that this Entity is displaying
     */
    public final ArrayList<Text> getStats() {return stats;}
    
    //Keys
    /**
     * @return get an Arraylist of Key elements that allow control of the Entity
     */
    public final ArrayList<Key> getKeys() {return keys;}
    
    /**
     * @param c1 character this action is bound to; intended to be unique to each instance of a subclass of Entity
     * @param s1 the action (a String)
     */
    public final void bindKeyToAction( char c1, String s1 ) {keys.add(new Key(this,c1,keyMapPressed.get(s1),keyMapReleased.get(s1)));}
    
    /**
     * @param i1 integer code this action is bound to; intended to be unique to each instance of a subclass of Entity
     * @param s1 the action (a String)
     */
    public final void bindKeyToAction( int i1, String s1 ) {keys.add(new Key(this,i1,keyMapPressed.get(s1),keyMapReleased.get(s1)));}
    
    /**
     * @param s1 the action (a String)
     * @param a1 the code to be triggered when action is toggled on.
     * intended to be constant across all instances of a subclass of Entity
     * null when no code is to be triggered
     * @param a2 the code to be triggered when action is toggled off.
     * intended to be constant across all instances of a subclass of Entity
     * null when no code is to be triggered
     */
    public final void bindCodeToAction( String s1, KeyCommand a1 , KeyCommand a2) {
        keyMapPressed.put(s1,a1);
        keyMapReleased.put(s1,a2);
    }
    
    //Buttons
    /**
     * @param i1 the button (an int)
     * @param a1 the code to be triggered when Entity is clicked on.
     * intended to be constant across all instances of a subclass of Entity
     * null when no code is to be triggered
     * @param a2 the code to be triggered when Entity is released.
     * intended to be constant across all instances of a subclass of Entity
     * null when no code is to be triggered
     * @param a3 the code to be triggered when the Mouse moves over this Entity
     * intended to be constant across all instances of a subclass of Entity
     * null when no code is to be triggered
     */
    public final void bindButtonToCode( int i1, ButtonCommand a1, ButtonCommand a2, ButtonCommand a3){
        buttons.add(new Button(this,i1,a1,a2,a3));
    }
    
     /**
     * @return get an Arraylist of Button elements that allow control of the Entity
     */
    public final ArrayList<Button> getButtons() {return buttons;}
    
    public final HashMap<String, KeyCommand> getKeyMapPressed(Object requestor) {
        if (requestor instanceof IsDebugger){
            return keyMapPressed;
        }
        System.out.println(IsDebugger.DEBUGGER_MESSAGE);
        return null;
    }
    
    
    public final HashMap<String, KeyCommand> getKeyMapReleased(Object requestor) {
        if (requestor instanceof IsDebugger){
            return keyMapReleased;
        }
        System.out.println(IsDebugger.DEBUGGER_MESSAGE);
        return null;
    }
    
    public final boolean matchesClassOf(Entity input, Entity requestor) {
        if (requestor instanceof IsDebugger){
            return input.getClass().isInstance(this);
        }
        System.out.println(IsDebugger.DEBUGGER_MESSAGE);
        throw new RuntimeException();
    }
}
