package workspace.minions;

public class Tier3DefenseMinion extends DefenseMinion implements MinionData{
    public Tier3DefenseMinion(String color) {
        super(color);
        if (this.getColor().equals("red")){
            sprite.addImage("images/Red/Minions/Defense/Tier3.png","main",true);
        }
        if (this.getColor().equals("blue")){
            sprite.addImage("images/Blue/Minions/Defense/Tier3.png","main",true);
        }
        resize();
        speed = TIER3_DEFENSE_MINION_SPEED;
        attackFrequency = TIER3_DEFENSE_MINION_ATTACK_FREQUENCY;
        attack = TIER3_DEFENSE_MINION_ATTACK;
        sight = TIER3_DEFENSE_MINION_SIGHT;
        penetration = TIER3_DEFENSE_MINION_PENETRATION;
        setHealth(TIER3_DEFENSE_MINION_HEALTH);
    }
}
