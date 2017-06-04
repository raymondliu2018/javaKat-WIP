package workspace.minions;

public class Tier2SmartMinion extends SmartMinion implements MinionData
{ 
    public Tier2SmartMinion(String color)
    {
        super(color);
        if (this.getColor().equals("red")){
            sprite.addImage("images/Red/Minions/Smart/Tier2.png","main",true);
        }
        if (this.getColor().equals("blue")){
            sprite.addImage("images/Blue/Minions/Smart/Tier2.png","main",true);
        }
        resize();
        speed = TIER2_SMART_MINION_SPEED;
        attackFrequency = TIER2_SMART_MINION_ATTACK_FREQUENCY;
        attack = TIER2_SMART_MINION_ATTACK;
        sight = TIER2_SMART_MINION_SIGHT;
        penetration = TIER2_SMART_MINION_PENETRATION;
        setHealth(TIER2_SMART_MINION_HEALTH);
    }
}