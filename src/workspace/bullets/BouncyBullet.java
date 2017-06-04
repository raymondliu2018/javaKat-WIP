
package workspace.bullets;

import thePackage.*;
import workspace.buildings.Factory;
import workspace.minions.Minion;

public class BouncyBullet extends Bullet implements BulletData{
    public BouncyBullet(String color){
        super(color, 0, 100);
        sprite.addImage(Utility.scaleImage(sprite.getImage(), 2.0), "bigger", true);
        resize();
    }
    
    public boolean collidedWith(Entity input){
        boolean temp = super.collidedWith(input);
        if (input instanceof Minion) {
            input.getRect().setXVelocity(rect.getXVelocity() / BOUNCY_BULLET_BOUNCE);
            input.getRect().setYVelocity(rect.getYVelocity() / BOUNCY_BULLET_BOUNCE);
        }
        if (input instanceof Factory) {
            ((Factory)input).dealDamage(1);
        }
        return temp;
        
    }
}
