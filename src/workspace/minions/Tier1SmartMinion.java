package workspace.minions;

public class Tier1SmartMinion extends SmartMinion implements MinionData
{ 
    public Tier1SmartMinion(String color)
    {
        super(color);
        if (this.getColor().equals("red")){
            sprite.addImage("images/Red/Minions/Smart/Tier1.png","main",true);
        }
        if (this.getColor().equals("blue")){
            sprite.addImage("images/Blue/Minions/Smart/Tier1.png","main",true);
        }
        resize();
        speed = TIER1_SMART_MINION_SPEED;
        attackFrequency = TIER1_SMART_MINION_ATTACK_FREQUENCY;
        attack = TIER1_SMART_MINION_ATTACK;
        sight = TIER1_SMART_MINION_SIGHT;
        penetration = TIER1_SMART_MINION_PENETRATION;
        setHealth(TIER1_SMART_MINION_HEALTH);
    }
}