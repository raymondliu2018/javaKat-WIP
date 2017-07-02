package javaKat;  

public final class Rect{
    private Entity owner;
    private double xPosition = 0.0;
    private double yPosition = 0.0;
    private double width = 0.0;
    private double height = 0.0;
    private int layer;
    private double mass = 1.0;
    private double xVelocity = 0.0;
    private double yVelocity = 0.0;
    private double xAcceleration = 0.0;
    private double yAcceleration = 0.0;
    private double xMomentum = 0.0;
    private double yMomentum = 0.0;
    private double xMaxSpeed = Integer.MAX_VALUE;
    private double yMaxSpeed = Integer.MAX_VALUE;
    private double fric = 0.0;
    
    public Rect(int input){
        layer = input;
    }
    
    protected void setOwner(Entity input) {
        owner = input;
    }
    
    protected Entity getOwner() {
        return owner;
    }
    /**
     * @param input set the size of the rect
     */ 
    public void setSize( double inputx, double inputy ) {
        setWidth(inputx);
        setHeight(inputy);
    }
    /**
     * @param input set the width of the rect
     */
    public void setWidth( double input ) {width = input;}
    /**
     * @param input set the height of the rect
     */
    public void setHeight( double input ) {height = input;}
    /**
     * @param input set the center position of the rect
     */
    public void setCenterPosition(double inputx, double inputy ) {
        setCenterX(inputx);
        setCenterY(inputy);
    }
    /**
     * @param input set the center x of the rect
     */
    public void setCenterX(double input) {xPosition = input;}
    /**
     * @param input set the center y of the rect
     */
    public void setCenterY(double input) {yPosition = input;}
    /**
     * @param input set the top left corner position of the rect
     */
    public void setCornerPosition(double inputx, double inputy) {
        setCornerX(inputx);
        setCornerY(inputy);
    }
    /**
     * @param input set the top left corner xof the rect
     */
    public void setCornerX(double input) {
        xPosition = input + width/2;
    }
    /**
     * @param input set the top left corner y of the rect
     */
    public void setCornerY(double input) {
        yPosition = input + height/2;
    }
    /**
     * @param inputx set x velocity of the rect
     * @param inputy set y velocity of the rect
     */
    public void setVelocity(double inputx, double inputy) {
        setXVelocity(inputx);
        setYVelocity(inputy);
    }
    /**
     * @param input set the xvelocity of the rect
     */
    public void setXVelocity(double input) {
        xVelocity = ( input > 0 ) ? Math.min(input , xMaxSpeed ) : Math.max(input , -xMaxSpeed );
    }
    /**
     * @param input set the y velocity of the rect
     */
    public void setYVelocity(double input) {
        yVelocity = ( input > 0 ) ? Math.min(input , yMaxSpeed ) : Math.max(input , -yMaxSpeed );
    }
    
    public void offsetVelocityBy(double inputx, double inputy) {
        offsetXVelocityBy(inputx);
        offsetYVelocityBy(inputy);
    }
    
    public void offsetXVelocityBy(double input) {
        setXVelocity(xVelocity + input);
    }
    
    public void offsetYVelocityBy(double input) {
        setYVelocity(yVelocity + input);
    }
    /**
     * @param input set the friction of the rect
     */
    public void setFriction(double input) {fric = input;}
    /**
     * @param input set the mass of the rect
     */
    public void setMass(double input) {mass = input;}
    /**
     * @param input set the maximum speed of the rect
     */
    public void setMaxSpeed(double input) {
        setMaxXSpeed(input);
        setMaxYSpeed(input);
    }
    
    public void setMaxSpeed(double inputx, double inputy) {
        setMaxXSpeed(inputx);
        setMaxYSpeed(inputy);
    }
    /**
     * @param input set the maximum xspeed of the rect
     */
    public void setMaxXSpeed(double input) {
        xMaxSpeed = input;
    }
    /**
     * @param input set the maximum y speed of the rect
     */
    public void setMaxYSpeed(double input) {
        yMaxSpeed = input;
    }
    /**
     * @param teleportx teleport this many units in the x-direction
     * @param teleporty teleport this many units in the y-direction
     */
    public void offsetPositionBy(double inputx, double inputy){
        offsetXPositionBy(inputx);
        offsetYPositionBy(inputy);
    }
    
    public void offsetXPositionBy(double input){
        setCenterX(getCenterX() + input);
    }
    
    public void offsetYPositionBy(double input) {
        setCenterY(getCenterY() + input);
    }
    /**
     * calculate and incorperate the jerk in the the rect's movement
     */
    public void setAcceleration(double inputx, double inputy) {
        setXAcceleration(inputx);
        setYAcceleration(inputy);
    }
    
    public void setXAcceleration(double input) {
        xAcceleration = input;
    }
    
    public void setYAcceleration(double input) {
        yAcceleration = input;
    }
    
    public void offsetAccelerationBy(double inputx, double inputy) {
        offsetXAccelerationBy(inputx);
        offsetYAccelerationBy(inputy);
    }
    
    public void offsetXAccelerationBy(double input){
        xAcceleration += input;
    }
    
    public void offsetYAccelerationBy(double input){
        yAcceleration += input;
    }
    /**
     * stop all movement
     */
    public void stop() {
        stopX();
        stopY();
    }
    
    public void stopX() {
        xVelocity = 0;
        xAcceleration = 0;
    }
    
    public void stopY() {
        yVelocity = 0;
        yAcceleration = 0;
    }
    /**
     * updates the momentum of the chosen object
     */
    public void updateMomentum()
    {
        xMomentum = mass*xVelocity;
        yMomentum = mass*yVelocity;
    }
    /**
     * the all-in-one update function that updates the accel, position, and momentum of the selected object
     */
    public void update(){
        offsetVelocityBy((xVelocity == 0) ? 0 : (( xVelocity > 0 ) ? Math.max(-fric, -xVelocity) : Math.min(fric,-xVelocity )) ,
              (yVelocity == 0) ? 0 : (( yVelocity > 0 ) ? Math.max(-fric, -yVelocity) : Math.min(fric,-yVelocity )));
        offsetVelocityBy(xAcceleration,yAcceleration);
        updateMomentum();
        offsetPositionBy(xVelocity,yVelocity);
    }
    /**
     * Movement when friction is negligible 
     */
    public void updateWithoutFriction()
    {   updateMomentum();
        xPosition += xVelocity;
        yPosition += yVelocity;
    }
    /**
     * @param input focus on one lay of objects and set it to an array of objects
     */
    public void setLayer( int initLayer ){
        layer = initLayer;
    }
    /**
     * @return focus on one lay of objects and return that array
     */
    public int getLayer() {return layer;}
    /**
     * @return returns the center xposition of the rect
     */
    public double getCenterX() {return xPosition;}
    /**
     * @return returns the center y position of the rect
     */
    public double getCenterY() {return yPosition;}
    /**
     * @return returns the width of the rect
     */
    public double getWidth() {return width;}
    /**
     * @return returns the height of the rect
     */
    public double getHeight() {return height;}
    /**
     * @return returns the xvelocity of the rect
     */
    public double getXVelocity() {return xVelocity;}
    /**
     * @return returns the y velocity of the rect
     */
    public double getYVelocity() {return yVelocity;}
    /**
     * @return returns the maximum xvelocity of the rect
     */
    public double getMaxXSpeed() {return xMaxSpeed;}
    /**
     * @return returns the maximum y velocity of the rect
     */
    public double getMaxYSpeed() {return yMaxSpeed;}
    /**
     * @return returns the mass of the object
     */
    public double getMass() {return mass;}
    /**
     * @return returns the corner xposition of the rect
     */
    public double getCornerX() {return xPosition - width/2;}
    /**
     * @return returns the corner y position of the rect
     */
    public double getCornerY() {return yPosition - height/2;}
    
    public double getVelocityAngle() {
        if (xVelocity == 0 && yVelocity == 0){
            return Double.NaN;
        }
        else {
            return Math.atan2(xVelocity, -yVelocity);
        }
    }
    
    public double getAccelerationAngle() {
        if (xAcceleration == 0 && yAcceleration == 0) {
            return Double.NaN;
        }
        else {
            return Math.atan2(xAcceleration, -yAcceleration);
        }
    }
}