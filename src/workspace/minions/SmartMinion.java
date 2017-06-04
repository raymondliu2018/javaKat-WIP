
package workspace.minions;

import thePackage.*;
import workspace.bullets.BouncyBullet;
import workspace.bullets.Bullet;
public class SmartMinion extends Minion implements MinionData{
    private Double destinationX, destinationY;
    private Sprite shield;
    public SmartMinion(String color) {
        super(color);
        destinationX = null;
        destinationY = null;
        shield = new Sprite();
        if (this.getColor().equals("red")){
            shield.addImage("images/Red/Minions/Smart/Shield.png", "on",true);
    }
        if (this.getColor().equals("blue")){
            shield.addImage("images/Blue/Minions/Smart/Shield.png", "on",true);
        }
        shield.setX(() -> {return rect.getCornerX();});
        shield.setY(() -> {return rect.getCornerY();});
        Manager.addLonelySprite(shield, 4);
    }
    
    public void subUpdate() {
        super.subUpdate();
        rect.setMaxSpeed(speed);
        rect.setFriction(2.0);
        if (getHealth() <= 0) {
            Manager.removeLonelySprite(shield, 4);
        }
    }
    
    public void move() {
        if (destinationX != null){
            double tempY = destinationY != null ? destinationY : rect.getCenterY();
            double distance = getDistance(rect.getCenterX(),rect.getCenterY(),destinationX,tempY);
            rect.accel((SMART_MINION_ACCELERATION_SCALE / distance) *
                    (destinationX - rect.getCenterX()), 0);
            if (Math.abs(destinationX - rect.getCenterX()) <= SMART_MINION_TOLERANCE){
                destinationX = null;
            }
        }
        if (destinationY != null){
            double tempX = destinationX != null ? destinationX : rect.getCenterX();
            double distance = getDistance(rect.getCenterX(),rect.getCenterY(),tempX,destinationY);
            rect.accel(0, (SMART_MINION_ACCELERATION_SCALE / distance) *
                    (destinationY - rect.getCenterY()));
            if (Math.abs(destinationY - rect.getCenterY()) <= SMART_MINION_TOLERANCE){
                destinationY = null;
            }
        }
    }
    
    public void orderTo(double x, double y){
        destinationX = x + (Math.random() - 0.5) * SMART_MINION_TOLERANCE;
        destinationY = y + (Math.random() - 0.5) * SMART_MINION_TOLERANCE;
    }
    
    public double getDestinationX() {
        return destinationX != null ? destinationX : rect.getCenterX();
    }
    
    public double getDestinationY() {
        return destinationY != null ? destinationY : rect.getCenterY();
    }
    public boolean checkEndGameCondition() {
        return false;
    }
    
    public void dealDamage(int input) {
        super.dealDamage(input);
        if (state == MOVING) {
            setHealth(1);
            shield.setImage("on");
        }
        else {
            shield.setImage("off");
        }
    }
    
    public Bullet getBullet() {
        return new BouncyBullet(this.getColor());
    }
}
