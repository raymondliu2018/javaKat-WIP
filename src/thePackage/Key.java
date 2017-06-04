package thePackage;

public final class Key {
    private Entity owner;
    private int input;
    private boolean toggled = false;
    private KeyCommand commandPressed = null;
    private KeyCommand commandReleased = null;
    protected Key(Entity e1, char c1, KeyCommand a1, KeyCommand a2) {
        input = (int) c1;
        commandPressed = a1;
        commandReleased = a2;
        owner = e1;
    }
    
    protected Key(Entity e1, int i1, KeyCommand a1, KeyCommand a2) {
        input = i1;
        commandPressed = a1;
        commandReleased = a2;
        owner = e1;
    }
    
    /**
     * @param input code to be executed when this Key's character is pressed
     */
    protected void whenPressed( KeyCommand input ) {commandPressed = input;}
    
    /**
     * @param input code to be executed when this Key's character is released
     */
    protected void whenReleased( KeyCommand input ) {commandReleased = input;}
    
    /**
     * @return get the character this Key is bound to
     */
    public int getInput() {return input;}
    
    /**
     * @return get the state of this Key
     */
    public boolean getToggle() {return toggled;}
   
    protected void toggleOn(){
        if (!toggled && commandPressed != null) {
            commandPressed.execute(this);
        }
        toggled = true;
    }
    
    protected void toggleOff(){
        if (toggled && commandReleased != null) {
            commandReleased.execute(this);
        }
        toggled = false;
    }
}