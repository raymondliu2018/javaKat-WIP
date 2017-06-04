package workspace.minions;

public class Tier1AttackMinion extends AttackMinion implements MinionData
{ 
    public Tier1AttackMinion(String color)
    {
        super(color);
        if (this.getColor().equals("red")){
            sprite.addImage("images/Red/Minions/Attack/Tier1.png","main",true);
        }
        if (this.getColor().equals("blue")){
            sprite.addImage("images/Blue/Minions/Attack/Tier1.png","main",true);
        }
        resize();
        speed = TIER1_ATTACK_MINION_SPEED;
        attackFrequency = TIER1_ATTACK_MINION_ATTACK_FREQUENCY;
        attack = TIER1_ATTACK_MINION_ATTACK;
        sight = TIER1_ATTACK_MINION_SIGHT;
        penetration = TIER1_ATTACK_MINION_PENETRATION;
        setHealth(TIER1_ATTACK_MINION_HEALTH);
    }
}