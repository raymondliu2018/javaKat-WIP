package workspace.minions;

public class Tier2DefenseMinion extends DefenseMinion implements MinionData{
    public Tier2DefenseMinion(String color) {
        super(color);
        if (this.getColor().equals("red")){
            sprite.addImage("images/Red/Minions/Defense/Tier2.png","main",true);
        }
        if (this.getColor().equals("blue")){
            sprite.addImage("images/Blue/Minions/Defense/Tier2.png","main",true);
        }
        resize();
        speed = TIER2_DEFENSE_MINION_SPEED;
        attackFrequency = TIER2_DEFENSE_MINION_ATTACK_FREQUENCY;
        attack = TIER2_DEFENSE_MINION_ATTACK;
        sight = TIER2_DEFENSE_MINION_SIGHT;
        penetration = TIER2_DEFENSE_MINION_PENETRATION;
        setHealth(TIER2_DEFENSE_MINION_HEALTH);
    }
}
