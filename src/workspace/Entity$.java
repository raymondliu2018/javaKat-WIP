package workspace;

import thePackage.*;
public abstract class Entity$ extends Entity
{
    private final String color;
    private int health;
    public Entity$(String input){
        color = input;
    }
    public String getColor(){
        return color;
    }
    public void dealDamage(int damage){
        health -= damage;
    }
    
    public int getHealth(){
        return health;
    }
    
    public void setHealth(int input) {
        health = input;
    }
    
    public boolean collidedWith(Entity input){
        return false;
    }
}
