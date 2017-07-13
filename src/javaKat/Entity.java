package javaKat;  

/**
 * Every game character should be a subclass of Entity
 * 
 * Entities have one Rect, one Sprite, one team, and a timer
 * 
 * Entities have arrays of keys and stats
 */

import java.util.ArrayList;
import java.util.HashMap;
import javaKat.debugger.DebugTool;
import javaKat.debugger.DebuggerTag;

public abstract class Entity
{
    private int layer;
    private boolean superCalled = false;
    private final HashMap<String,KeyCommand> keyMapPressed;
    private final HashMap<String,KeyCommand> keyMapReleased;
    private final ArrayList<Key> keys;
    private final ArrayList<Button> buttons;
    private final ArrayList<Album> albums;
    protected JukeBox jukeBox;
    private boolean focused = false;
    protected Rect rect;
    private int timer;
    protected ArrayList<Tag> stats;
    
    public Entity() {
        timer = 0;

        layer = 1;
        rect = new Rect(this);
        rect.setLayer(layer);
        
        jukeBox = new JukeBox(this);
        
        stats = new ArrayList<>();
        keys = new ArrayList<>();
        buttons = new ArrayList<>();
        albums = new ArrayList<>();
        keyMapPressed = new HashMap<>();
        keyMapReleased = new HashMap<>();
        superCalled = true;
        Manager.queueNewEntity(this);
    }
    
    //Sprite
    /**
     * @return get the Album element of this Entity
     */
    public final ArrayList<Album> getAlbums() {return albums;}
    
    protected final void attachAlbum(Album input) {
        albums.add(input);
    }
    
    /**
     * @return get whether or not the Entity is being focused on by the Camera
     */
    public final boolean getFocused() {return focused;}
    
    /**
     * Sets this entity's hitbox to the size of its current image
     */
    public final void resizeByCenter(double inputx, double inputy) {
        rect.setSize(inputx,inputy);
    }
    
    public final void resizeByCorner(double inputx, double inputy) {
        double xPosition = rect.getCornerX();
        double yPosition = rect.getCornerY();
        resizeByCenter(inputx,inputy);
        rect.setCornerPosition(xPosition, yPosition);
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
        subUpdate();
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
    public Reaction collidedWith(Entity input) {
        return Reaction.GHOST;
    }
    
    //Stats
    protected final void attachStat(Tag input) {
        stats.add(input);
    }
    /**
     * @return get an Arraylist of Tag elements that this Entity is displaying
     */
    public final ArrayList<Tag> getStats() {return stats;}
    
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
    protected final void bindCodeToAction( String s1, KeyCommand a1 , KeyCommand a2) {
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
        if (requestor instanceof DebugTool){
            return keyMapPressed;
        }
        throw new IllegalArgumentException(DebuggerTag.DEBUGGER_MESSAGE);
    }
    
    
    public final HashMap<String, KeyCommand> getKeyMapReleased(Object requestor) {
        if (requestor instanceof DebugTool){
            return keyMapReleased;
        }
        throw new IllegalArgumentException(DebuggerTag.DEBUGGER_MESSAGE);
    }
    
    public final boolean matchesClassOf(String input, Entity requestor) {
        if (requestor instanceof DebugTool){
            return input.equals(this.getClass().getName());
        }
        throw new IllegalArgumentException(DebuggerTag.DEBUGGER_MESSAGE);
    }
    
    protected final boolean superCalled() {return superCalled;}
    
    public final void setLayer(int input) {
        Manager.removeRect(rect, layer);
        Manager.removeAlbums(albums, layer);
        layer = input;
        rect.setLayer(layer);
        for (Album sprite: albums) {  
            sprite.setLayer(layer);
        }
        Manager.addAlbums(albums, layer);
        Manager.addRect(rect, layer);
    }
    
    public final void focus() {
        focused = true;
        Manager.addFocused(this);
    }
    
    public final void defocus() {
        focused = false;
        Manager.removeFocused(this);
    }
    
    public final int ticksCounted(){
        return timer;
    }
}
