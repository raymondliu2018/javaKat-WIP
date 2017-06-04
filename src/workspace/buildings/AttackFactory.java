package workspace.buildings;

import workspace.minions.*;

public class AttackFactory extends Factory implements MinionData
{
    public AttackFactory(String color, int position)
    {
        super(color,position);
    }
    
    public int getProductionTime() {
        int counter;
        switch(getTier()) {
            case 1:
                counter = TIER1_ATTACK_MINION_PRODUCTION_TIME;
                break;
            case 2:
                counter = TIER2_ATTACK_MINION_PRODUCTION_TIME;
                break;
            case 3:
                counter = TIER3_ATTACK_MINION_PRODUCTION_TIME;
                break;
            default:
                counter = TIER1_ATTACK_MINION_PRODUCTION_TIME;
        }
        return counter;
    }
    
    public Minion getMinion() {
        Minion minion;
        switch(getTier()) {
            case 1:
                minion = new Tier1AttackMinion(this.getColor());
                break;
            case 2:
                minion = new Tier2AttackMinion(this.getColor());
                break;
            case 3:
                minion = new Tier3AttackMinion(this.getColor());
                break;
            default:
                minion = new Tier1AttackMinion(this.getColor());
                break;
        }
        return minion;
    }
    
    public String getType() {return "Attack";}
}