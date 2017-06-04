package workspace.minions;

public class Tier3SmartMinion extends SmartMinion implements MinionData
{ 
    public Tier3SmartMinion(String color)
    {
        super(color);
        if (this.getColor().equals("red")){
            sprite.addImage("images/Red/Minions/Smart/Tier3.png","main",true);
        }
        if (this.getColor().equals("blue")){
            sprite.addImage("images/Blue/Minions/Smart/Tier3.png","main",true);
        }
        resize();
        speed = TIER3_SMART_MINION_SPEED;
        attackFrequency = TIER3_SMART_MINION_ATTACK_FREQUENCY;
        attack = TIER3_SMART_MINION_ATTACK;
        sight = TIER3_SMART_MINION_SIGHT;
        penetration = TIER3_SMART_MINION_PENETRATION;
        setHealth(TIER3_SMART_MINION_HEALTH);
    }
}