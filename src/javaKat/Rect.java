package javaKat;  

public final class Rect{
    private Entity owner;
    private double xPosition = 0.0;
    private double yPosition = 0.0;
    private double width = 0.0;
    private double height = 0.0;
    private int layer;
    private double xVelocity = 0.0;
    private double yVelocity = 0.0;
    private double xAcceleration = 0.0;
    private double yAcceleration = 0.0;
    private double xMaxSpeed = Integer.MAX_VALUE;
    private double yMaxSpeed = Integer.MAX_VALUE;
    private double fric = 0.0;
    
    public Rect(Entity input){
        layer = input.getLayer();
    }
    
    protected Entity getOwner() {
        return owner;
    }
    
    public void setSize( double inputx, double inputy ) {
        setWidth(inputx);
        setHeight(inputy);
    }
    
    public void setWidth( double input ) {
        limitInput(input);
        width = input;
    }
    
    public void setHeight( double input ) {
        limitInput(input);
        height = input;
    }
    
    public void setCenterPosition(double inputx, double inputy ) {
        setCenterX(inputx);
        setCenterY(inputy);
    }
    
    public void setCenterX(double input) {
        limitInput(input);
        xPosition = input;
    }
    
    public void setCenterY(double input) {
        limitInput(input);
        yPosition = input;
    }
    
    public void setCornerPosition(double inputx, double inputy) {
        setCornerX(inputx);
        setCornerY(inputy);
    }
    
    public void setCornerX(double input) {
        limitInput(input);
        xPosition = input + width/2;
    }
    
    public void setCornerY(double input) {
        limitInput(input);
        yPosition = input + height/2;
    }
    
    public void setVelocity(double inputx, double inputy) {
        setXVelocity(inputx);
        setYVelocity(inputy);
    }
    
    public void setXVelocity(double input) {
        limitInput(input);
        xVelocity = ( input > 0 ) ? Math.min(input , xMaxSpeed ) : Math.max(input , -xMaxSpeed );
    }
    
    public void setYVelocity(double input) {
        limitInput(input);
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
    
    public void setFriction(double input) {
        limitInput(input);
        fric = input;
    }
    
    public void setMaxSpeed(double input) {
        setMaxSpeed(input, input);
    }
    
    public void setMaxSpeed(double inputx, double inputy) {
        setMaxXSpeed(inputx);
        setMaxYSpeed(inputy);
    }
    
    public void setMaxXSpeed(double input) {
        limitInput(input);
        xMaxSpeed = input;
    }
    
    public void setMaxYSpeed(double input) {
        limitInput(input);
        yMaxSpeed = input;
    }
    
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
    
    public void setAcceleration(double inputx, double inputy) {
        setXAcceleration(inputx);
        setYAcceleration(inputy);
    }
    
    public void setXAcceleration(double input) {
        limitInput(input);
        xAcceleration = input;
    }
    
    public void setYAcceleration(double input) {
        limitInput(input);
        yAcceleration = input;
    }
    
    public void offsetAccelerationBy(double inputx, double inputy) {
        offsetXAccelerationBy(inputx);
        offsetYAccelerationBy(inputy);
    }
    
    public void offsetXAccelerationBy(double input){
        setXAcceleration(getXAcceleration() + input);
    }
    
    public void offsetYAccelerationBy(double input){
        setYAcceleration(getYAcceleration() + input);
    }
    
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
    
    public void update(){
        offsetVelocityBy((xVelocity == 0) ? 0 : (( xVelocity > 0 ) ? Math.max(-fric, -xVelocity) : Math.min(fric,-xVelocity )) ,
              (yVelocity == 0) ? 0 : (( yVelocity > 0 ) ? Math.max(-fric, -yVelocity) : Math.min(fric,-yVelocity )));
        offsetVelocityBy(xAcceleration,yAcceleration);
        offsetPositionBy(xVelocity,yVelocity);
    }
    
    public void updateWithoutFriction(){
        xPosition += xVelocity;
        yPosition += yVelocity;
    }
    
    public void setLayer( int input ){
        layer = input;
    }
    
    public int getLayer() {return layer;}
    
    public double getCenterX() {return xPosition;}
    
    public double getCenterY() {return yPosition;}
    
    public double getWidth() {return width;}
    
    public double getHeight() {return height;}
    
    public double getXVelocity() {return xVelocity;}
    
    public double getYVelocity() {return yVelocity;}
    
    public double getXAcceleration() {return xAcceleration;}
    
    public double getYAcceleration() {return yAcceleration;}
    
    public double getMaxXSpeed() {return xMaxSpeed;}
    
    public double getMaxYSpeed() {return yMaxSpeed;}
    
    public double getCornerX() {return xPosition - width/2;}
    
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
    
    public void limitInput(double ... inputs) {
        for (double input: inputs){
            if (Math.abs(input) >= Integer.MAX_VALUE){
                throw new IllegalArgumentException("Input exceeds Integer.MAX_VALUE!");
            }
        }
    }
}