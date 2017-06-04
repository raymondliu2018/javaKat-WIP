package workspace.buildings;

import workspace.minions.*;

public class SmartFactory extends Factory implements MinionData
{
    public SmartFactory(String color, int position)
    {
        super(color,position);
    }
    
    public int getProductionTime() {
        int counter;
        switch(getTier()) {
            case 1:
                counter = TIER1_SMART_MINION_PRODUCTION_TIME;
                break;
            case 2:
                counter = TIER2_SMART_MINION_PRODUCTION_TIME;
                break;
            case 3:
                counter = TIER3_SMART_MINION_PRODUCTION_TIME;
                break;
            default:
                counter = TIER1_SMART_MINION_PRODUCTION_TIME;
        }
        return counter;
    }
    
    public Minion getMinion() {
        Minion minion;
        switch(getTier()) {
            case 1:
                minion = new Tier1SmartMinion(this.getColor());
                break;
            case 2:
                minion = new Tier2SmartMinion(this.getColor());
                break;
            case 3:
                minion = new Tier3SmartMinion(this.getColor());
                break;
            default:
                minion = new Tier1SmartMinion(this.getColor());
                break;
        }
        return minion;
    }
    
    public String getType() {return "Smart";}
}