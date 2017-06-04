package workspace.minions;

public class Tier3AttackMinion extends AttackMinion implements MinionData
{ 
    public Tier3AttackMinion(String color)
    {
        super(color);
        if (this.getColor().equals("red")){
            sprite.addImage("images/Red/Minions/Attack/Tier3.png","main",true);
        }
        if (this.getColor().equals("blue")){
            sprite.addImage("images/Blue/Minions/Attack/Tier3.png","main",true);
        }
        resize();
        speed = TIER3_ATTACK_MINION_SPEED;
        attackFrequency = TIER3_ATTACK_MINION_ATTACK_FREQUENCY;
        attack = TIER3_ATTACK_MINION_ATTACK;
        sight = TIER3_ATTACK_MINION_SIGHT;
        penetration = TIER3_ATTACK_MINION_PENETRATION;
        setHealth(TIER3_ATTACK_MINION_HEALTH);
    }
}