package workspace.bullets;

import thePackage.*;
import thePackage.Utility;
import workspace.Entity$;
public class Bullet extends Entity$ implements BulletData
{
    private int damage; 
    public Bullet(String color, int damage, int health)
    {
        super(color);
        this.damage = damage;
        String image = "";
        if (this.getColor().equals("red")){
            image = "images/Red/Bullets/Normal.png";
        }
        if (this.getColor().equals("blue")){
            image = "images/Blue/Bullets/Normal.png";
        }
        sprite.addImage( Utility.scaleImage(image, BULLET_SIZE, BULLET_SIZE), "main", true);
        setHealth(health);
        resize();
    }
    public boolean collidedWith(Entity input) {
        if (input instanceof Entity$){
            Entity$ input$ = (Entity$) input;
            if (!input$.getColor().equals(this.getColor())){
                this.dealDamage(1);
                input$.dealDamage(this.getDamage());
            }
        }
        return false;
    }
    public int getDamage()
    {
        return damage;
    }
    public void subUpdate() {
        if (timer >= BULLET_RANGE || this.getHealth() <= 0){
            Manager.removeEntity(this);
        }
    }
}