package workspace.minions;

public class AttackMinion extends Minion implements MinionData{
    public AttackMinion(String color) {
        super(color);
    }
    public void move() {
        rect.setMaxSpeed(this.getSpeed());
        rect.stopY();
        if( this.getColor().equals("red") ){
            rect.offsetVelocityBy(this.getSpeed()/(double)ATTACK_MINION_ACCELERATION_SCALE,0.0);
        }
        if( this.getColor().equals("blue") ){
            rect.offsetVelocityBy(-this.getSpeed()/(double)ATTACK_MINION_ACCELERATION_SCALE,0.0);
        }
    }
}
