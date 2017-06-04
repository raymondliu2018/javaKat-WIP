package workspace.buildings;

import workspace.minions.*;

public class DefenseFactory extends Factory implements MinionData
{
    public DefenseFactory(String color, int position)
    {
        super(color,position);
    }
    
    public int getProductionTime() {
        int counter;
        switch(getTier()) {
            case 1:
                counter = TIER1_DEFENSE_MINION_PRODUCTION_TIME;
                break;
            case 2:
                counter = TIER2_DEFENSE_MINION_PRODUCTION_TIME;
                break;
            case 3:
                counter = TIER3_DEFENSE_MINION_PRODUCTION_TIME;
                break;
            default:
                counter = TIER1_DEFENSE_MINION_PRODUCTION_TIME;
        }
        return counter;
    }
    
    public Minion getMinion() {
        Minion minion;
        switch(getTier()) {
            case 1:
                minion = new Tier1DefenseMinion(this.getColor());
                break;
            case 2:
                minion = new Tier2DefenseMinion(this.getColor());
                break;
            case 3:
                minion = new Tier3DefenseMinion(this.getColor());
                break;
            default:
                minion = new Tier1DefenseMinion(this.getColor());
                break;
        }
        return minion;
    }
    
    public String getType() {return "Defense";}
}