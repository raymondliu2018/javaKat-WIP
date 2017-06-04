package thePackage;

public final class Rect{
    private Entity owner;
    private double xPosition = 0.0;
    private double yPosition = 0.0;
    private double width = 0.0;
    private double height = 0.0;
    private int layer = 1;
    private double mass = 1.0;
    private double xVelocity = 0.0;
    private double yVelocity = 0.0;
    private double xAcceleration = 0.0;
    private double yAcceleration = 0.0;
    private double xMomentum = 0.0;
    private double yMomentum = 0.0;
    private double maximumXVelocity = Integer.MAX_VALUE;
    private double maximumYVelocity = Integer.MAX_VALUE;
    private double fric = 0.0;
    
    public Rect(Entity input){
        owner = input;
    }
    /**
     * @param input set the size of the rect
     */ 
    public void setSize( double initWidth, double initHeight ) {width = initWidth; height = initHeight;}
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
    public void setCenterPosition( double centerx, double centery ) {xPosition = centerx; yPosition = centery;}
    /**
     * @param input set the center x of the rect
     */
    public void setCenterX( double input ) {xPosition = input;}
    /**
     * @param input set the center y of the rect
     */
    public void setCenterY( double input ) {yPosition = input;}
    /**
     * @param input set the top left corner position of the rect
     */
    public void setCornerPosition( double cornerx, double cornery ) {xPosition = cornerx + width/2.0; yPosition = cornery + height/2.0;}
    /**
     * @param input set the top left corner xof the rect
     */
    public void setCornerX( double input ) {xPosition = input + width/2;}
    /**
     * @param input set the top left corner y of the rect
     */
    public void setCornerY( double input ) {yPosition = input + height/2;}
    /**
     * @param input set velocity of the rect
     */
    public void setVelocity( double velocityx, double velocityy ) {xVelocity = velocityx; yVelocity = velocityy;}
    /**
     * @param input set the xvelocity of the rect
     */
    public void setXVelocity( double input ) {xVelocity = input;}
    /**
     * @param input set the y velocity of the rect
     */
    public void setYVelocity( double input ) {yVelocity = input;}
    /**
     * @param input set the friction of the rect
     */
    public void setFriction( double input ) {fric = input;}
    /**
     * @param input set the mass of the rect
     */
    public void setMass( double input ) {mass = input;}
    /**
     * @param input set the maximum speed of the rect
     */
    public void setMaxSpeed( double input ) {maximumXVelocity = input; maximumYVelocity = input;}
    /**
     * @param input set the maximum xspeed of the rect
     */
    public void setMaxXSpeed( double input ) {maximumXVelocity = input;}
    /**
     * @param input set the maximum y speed of the rect
     */
    public void setMaxYSpeed( double input ) {maximumYVelocity = input;}
    /**
     * @param teleportx teleport this many units in the x-direction
     * @param teleporty teleport this many units in the y-direction
     */
    public void teleportBy( double teleportx, double teleporty ){
        xPosition += teleportx;
        yPosition += teleporty;
    }
    /**
     * calculate and incorperate the jerk in the the rect's movement
     */
    public void jerk(double jerkx, double jerky) {
        xAcceleration += jerkx;
        yAcceleration += jerky;
    }
    /**
     * stop all movement
     */
    public void stop() {
        xVelocity = 0;
        yVelocity = 0;
        xAcceleration = 0;
        yAcceleration = 0;
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
     * inplement acceleration into the movement
     */
    public void accel(double aX, double aY)
    {
        xVelocity = ( aX > 0 ) ? Math.min( xVelocity + aX , maximumXVelocity ) : Math.max( xVelocity + aX , -maximumXVelocity );
        yVelocity = ( aY > 0 ) ? Math.min( yVelocity + aY , maximumYVelocity ) : Math.max( yVelocity + aY , -maximumYVelocity );
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
    public void update()
    {
        accel((xVelocity == 0) ? 0 : (( xVelocity > 0 ) ? Math.max(-fric, -xVelocity) : Math.min(fric,-xVelocity )) ,
              (yVelocity == 0) ? 0 : (( yVelocity > 0 ) ? Math.max(-fric, -yVelocity) : Math.min(fric,-yVelocity )));
        accel(xAcceleration,yAcceleration);
        updateMomentum();
        xPosition += xVelocity;
        yPosition += yVelocity;
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
        int previous = layer;
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
    public double getMaxXSpeed() {return maximumXVelocity;}
    /**
     * @return returns the maximum y velocity of the rect
     */
    public double getMaxYSpeed() {return maximumYVelocity;}
    /**
     * @return returns the owner of the rect
     */
    public Entity getOwner() {return owner;}
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
    
    public double getAngle() {
        if (xVelocity == 0 && yVelocity == 0){
            return Double.MAX_VALUE;
        }
        else {
            return Math.atan2(xVelocity, -yVelocity);
        }
    }
}