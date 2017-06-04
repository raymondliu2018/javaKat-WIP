package thePackage;

class Button{
    private Entity owner;
    private int input;
    private boolean toggled = false;
    private ButtonCommand commandPressed = null;
    private ButtonCommand commandReleased = null;
    private ButtonCommand commandMoved = null;
    public Button(Entity e1, int i1, ButtonCommand a1, ButtonCommand a2, ButtonCommand a3) {
        input = i1;
        commandPressed = a1;
        commandReleased = a2;
        commandMoved = a3;
        owner = e1;
    }
    
    /**
     * @param input code to be executed when this Button's button is pressed above this Entity
     */
    protected void whenPressed( ButtonCommand input ) {commandPressed = input;}
    
    /**
     * @param input code to be executed when this Button's button is released above this Entity
     */
    
    protected void whenReleased( ButtonCommand input ) {commandReleased = input;}
    
    /**
     * @param input code to be executed when the Mouse is moved above this Entity
     */
    
    protected void whenMoved( ButtonCommand input ) {commandMoved = input;}
    
    /**
    * @return get the button this Button is bound to
    */
    public int getInput() {return input;}
    
    /**
    * @return get the state of this Button
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
    
    protected void moved(){
        if (commandMoved != null) {
            commandMoved.execute(this);
        }
    }
    
    public int getX() {return (int) owner.getRect().getCornerX();}
    
    public int getY() {return (int) owner.getRect().getCornerY();}
    
    public int getX$() {return (int) (owner.getRect().getCornerX() + owner.getRect().getWidth());}
    
    public int getY$() {return (int) (owner.getRect().getCornerY() + owner.getRect().getHeight());}
    
}