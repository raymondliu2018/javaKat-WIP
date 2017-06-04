package workspace.minions;

public class Tier2AttackMinion extends AttackMinion implements MinionData
{ 
    public Tier2AttackMinion(String color)
    {
        super(color);
        if (this.getColor().equals("red")){
            sprite.addImage("images/Red/Minions/Attack/Tier2.png","main",true);
        }
        if (this.getColor().equals("blue")){
            sprite.addImage("images/Blue/Minions/Attack/Tier2.png","main",true);
        }
        resize();
        speed = TIER2_ATTACK_MINION_SPEED;
        attackFrequency = TIER2_ATTACK_MINION_ATTACK_FREQUENCY;
        attack = TIER2_ATTACK_MINION_ATTACK;
        sight = TIER2_ATTACK_MINION_SIGHT;
        penetration = TIER2_ATTACK_MINION_PENETRATION;
        setHealth(TIER2_ATTACK_MINION_HEALTH);
    }
}