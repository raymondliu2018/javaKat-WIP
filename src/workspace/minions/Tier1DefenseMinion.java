package workspace.minions;

public class Tier1DefenseMinion extends DefenseMinion implements MinionData{
    public Tier1DefenseMinion(String color) {
        super(color);
        if (this.getColor().equals("red")){
            sprite.addImage("images/Red/Minions/Defense/Tier1.png","main",true);
        }
        if (this.getColor().equals("blue")){
            sprite.addImage("images/Blue/Minions/Defense/Tier1.png","main",true);
        }
        resize();
        speed = TIER1_DEFENSE_MINION_SPEED;
        attackFrequency = TIER1_DEFENSE_MINION_ATTACK_FREQUENCY;
        attack = TIER1_DEFENSE_MINION_ATTACK;
        sight = TIER1_DEFENSE_MINION_SIGHT;
        penetration = TIER1_DEFENSE_MINION_PENETRATION;
        setHealth(TIER1_DEFENSE_MINION_HEALTH);
    }
}
