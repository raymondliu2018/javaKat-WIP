package workspace.minions;

import java.awt.Color;
import thePackage.*;
import workspace.Entity$;
import workspace.Script;
import workspace.bullets.Bullet;
import workspace.bullets.BulletData;
import workspace.buildings.Factory;
public abstract class Minion extends Entity$ implements GameData, MinionData, BulletData, Ender
{
    protected int attack;
    protected int attackFrequency;
    protected int speed;
    protected int penetration;
    protected int state = MOVING;
    protected Entity enemy;
    private int internalTimer;
    protected int sight;
    
    public Minion(String color){
        super(color);
        rotating = true;
        Text text = new Text();
        if (this.getColor().equals("red")){
            text.setColor(Color.RED);
        }
        if (this.getColor().equals("blue")){
            text.setColor(Color.BLUE);
        }
        text.setMessage(() -> {return Integer.toString(getHealth());});
        text.setCenterX(() -> {return this.rect.getCenterX();});
        text.setCenterY(() -> {return (this.rect.getCornerY() + 2 * this.rect.getHeight());});
        this.addStat(text);
    }
    
    public void subUpdate()
    {
        if(getHealth()<=0)
        {
            Manager.removeEntity(this);
        }
        if(rect.getCornerY() < 0 || rect.getCornerY() + rect.getWidth() > GameMaster.getHeight()){
            Manager.removeEntity(this);
        }
        switch (state) {
            case MOVING:
                if (timer % 5 == 0) {
                    getEnemy();
                    if (enemy != null) {
                        state = ATTACKING;
                    }
                }
                move();
                break;
            case ATTACKING:
                bulletProduction();
                state = WAITING;
                internalTimer = timer;
                enemy = null;
                break;
            case WAITING:
                rect.stop();
                if (timer - internalTimer >= (attackFrequency * MINION_PAUSE_SCALE)) {
                    state = MOVING;
                }
                break;
        }
    }
    
    public int getAttack()
    {
        return attack;
    }

    public int getAttackFrequency()
    {
        return attackFrequency;
    }
    
    public int getSpeed()
    {
        return speed;
    }
 
    public void setAttack(int i)
    {
        attack = i;
    }
    
    public void setAttackFrequency(int i)
    {
        attackFrequency = i;
    }
    
    public void setSpeed(int i)
    {
       speed = i;
    }
    
    public void getEnemy() {
        double distancePriority = Double.MAX_VALUE;
        for (Entity input: GameData.allEntities) {
            if (visibility(input)) {
                if (input instanceof Entity$) {
                    Entity$ input$ = (Entity$) input;
                    if (!input$.getColor().equals(this.getColor())){
                        if (input$ instanceof Minion) {
                            double distance = getDistance(rect.getCenterX(),rect.getCenterY(),input$.getRect().getCenterX(),input$.getRect().getCenterY());
                            if (distance <= distancePriority) {
                                distancePriority = distance;
                                enemy = input;
                            }
                        }
                        if (input$ instanceof Factory) {
                            distancePriority = Double.MAX_VALUE;
                            double distance = getDistance(rect.getCenterX(),rect.getCenterY(),input$.getRect().getCenterX(),input$.getRect().getCenterY());
                            if (distance <= distancePriority) {
                                distancePriority = distance;
                                enemy = input;
                            }
                        }
                    }
                }
            }
        }
    }
    
    public double getDistance(double x1, double y1, double x2, double y2){
        return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
    }
    public boolean visibility(Entity input)
    {
        if(getDistance(rect.getCenterX(),rect.getCenterY(),input.getRect().getCenterX(),input.getRect().getCenterY()) <= sight)
        {return true;}
        return false;
    }
    
    public void bulletProduction(){
        Bullet bullet = getBullet();
        bullet.getRect().setCenterX(rect.getCenterX());
        bullet.getRect().setCenterY(rect.getCenterY());
        bullet.getRect().setXVelocity(getXVelocity(BULLET_SPEED));
        bullet.getRect().setYVelocity(getYVelocity(BULLET_SPEED));
        Manager.queueNewEntity(bullet);
    }
    
    protected double getXVelocity(int scalar) {
        double distance = getDistance(rect.getCenterX(),rect.getCenterY(),enemy.getRect().getCenterX(),enemy.getRect().getCenterY());
        return (double)( ( (double) scalar / distance ) * ( enemy.getRect().getCenterX() - rect.getCenterX() ) );
    }
    
    
    protected double getYVelocity(int scalar) {
        double distance = getDistance(rect.getCenterX(),rect.getCenterY(),enemy.getRect().getCenterX(),enemy.getRect().getCenterY());
        return (double)( ( (double) scalar / distance ) * ( enemy.getRect().getCenterY() - rect.getCenterY() ) );
    }
    
    public Bullet getBullet() {
        return new Bullet(this.getColor(), attack, penetration);
    }
    
    public abstract void move();
    
    public boolean checkEndGameCondition() {
        if (this.getColor().equals("red")){
            if (this.rect.getCenterX() > GameMaster.getWidth()){
                Script.winner("red");
                return true;
            }
        }
        if (this.getColor().equals("blue")){
            if (this.rect.getCenterX() < 0){
                Script.winner("blue");
                return true;
            }
        }
        return false;
    } 
}