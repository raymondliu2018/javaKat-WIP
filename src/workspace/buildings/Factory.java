package workspace.buildings;
import java.awt.Color;
import thePackage.*;
import workspace.*;
import workspace.minions.Minion;
import workspace.minions.MinionData;
public abstract class Factory extends Entity$ implements PlayerData, MinionData
{
    private int [] location = new int[2];/*block index*/
    private int tier;
    private int internalTimer;
    public Factory(String color, int position)
    {
        super(color);
        prepareImages();
        setTier(1);
        location = Map.getInstance().toPixel(position);
        rect.setCenterX(location[0]);
        rect.setCenterY(location[1]);
        setHealth(1000);
        Text text = new Text();
        if (this.getColor().equals("red")){
            text.setColor(Color.RED);
        }
        if (this.getColor().equals("blue")){
            text.setColor(Color.BLUE);
        }
        text.setMessage(() -> {return Integer.toString(getHealth());});
        text.setCenterX(() -> {return this.rect.getCenterX();});
        text.setCenterY(() -> {return (this.rect.getCenterY());});
        this.addStat(text);
    }
    
    public int [] display()
    {
        return location;
    }
    
    public void setTier(int input) {
        tier = input;
        sprite.setImage(Integer.toString(input));
        resize();
    }
    
    public void subUpdate()
    {
        if(getHealth()>0)
        {
            spawn();
        }
        else{Manager.removeEntity(this);}
    }
    
    public void destroy() {
        setHealth(0);
    }
    
    public void spawn(){
        if(timer - internalTimer > (getProductionTime() * MINION_PRODUCTION_SCALE) || timer == 1)
        {
            internalTimer = timer;
            Minion minion = getMinion();
            minion.getRect().setCenterX(this.getRect().getCenterX() + 
                    ((Math.random() * MINION_SPAWN_LOCATION_RANDOMNESS_SCALE * 2) - MINION_SPAWN_LOCATION_RANDOMNESS_SCALE));
            minion.getRect().setCenterY(this.getRect().getCenterY() + 
                    ((Math.random() * MINION_SPAWN_LOCATION_RANDOMNESS_SCALE * 2) - MINION_SPAWN_LOCATION_RANDOMNESS_SCALE));
            Manager.queueNewEntity(minion);
        }
    }
    
    public abstract String getType();
    
    public abstract Minion getMinion();
    
    public abstract int getProductionTime();
    
    public void prepareImages() {
        String image;
        if (this.getColor().equals("red")){
            image = "images/Red/Buildings/" + getType() + "/Tier1.png";
            sprite.addImage(Utility.scaleImage(image, BLOCK_SIZE, BLOCK_SIZE),"1",false);
            image = "images/Red/Buildings/" + getType() + "/Tier2.png";
            sprite.addImage(Utility.scaleImage(image, BLOCK_SIZE, BLOCK_SIZE),"2",false);
            image = "images/Red/Buildings/" + getType() + "/Tier3.png";
            sprite.addImage(Utility.scaleImage(image, BLOCK_SIZE, BLOCK_SIZE),"3",false);
        }
        if (this.getColor().equals("blue")){
            image = "images/Blue/Buildings/" + getType() + "/Tier1.png";
            sprite.addImage(Utility.scaleImage(image, BLOCK_SIZE, BLOCK_SIZE),"1",false);
            image = "images/Blue/Buildings/" + getType() + "/Tier2.png";
            sprite.addImage(Utility.scaleImage(image, BLOCK_SIZE, BLOCK_SIZE),"2",false);
            image = "images/Blue/Buildings/" + getType() + "/Tier3.png";
            sprite.addImage(Utility.scaleImage(image, BLOCK_SIZE, BLOCK_SIZE),"3",false);
        }
    }
    
    public int getTier() {return tier;}
}