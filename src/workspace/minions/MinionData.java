package workspace.minions;

public interface MinionData
{
    public static int TIER1_ATTACK_MINION_SPEED = 6;
    public static int TIER1_ATTACK_MINION_PRODUCTION_TIME = 2;
    public static int TIER1_ATTACK_MINION_HEALTH = 2;
    public static int TIER1_ATTACK_MINION_ATTACK = 2;
    public static int TIER1_ATTACK_MINION_ATTACK_FREQUENCY = 2;
    public static int TIER1_ATTACK_MINION_SIGHT = 150;
    public static int TIER1_ATTACK_MINION_PENETRATION = 1;
    
    public static int TIER2_ATTACK_MINION_SPEED = 4;
    public static int TIER2_ATTACK_MINION_PRODUCTION_TIME = 3;
    public static int TIER2_ATTACK_MINION_HEALTH = 3;
    public static int TIER2_ATTACK_MINION_ATTACK = 3;
    public static int TIER2_ATTACK_MINION_ATTACK_FREQUENCY = 1;
    public static int TIER2_ATTACK_MINION_SIGHT = 200;
    public static int TIER2_ATTACK_MINION_PENETRATION = 1;
    
    public static int TIER3_ATTACK_MINION_SPEED = 2;
    public static int TIER3_ATTACK_MINION_PRODUCTION_TIME = 4;
    public static int TIER3_ATTACK_MINION_HEALTH = 4;
    public static int TIER3_ATTACK_MINION_ATTACK = 4;
    public static int TIER3_ATTACK_MINION_ATTACK_FREQUENCY = 0;
    public static int TIER3_ATTACK_MINION_SIGHT = 250;
    public static int TIER3_ATTACK_MINION_PENETRATION = 1;
    
    public static int ATTACK_MINION_ACCELERATION_SCALE = 8;

    public static int TIER1_DEFENSE_MINION_SPEED = 1;
    public static int TIER1_DEFENSE_MINION_PRODUCTION_TIME = 6;
    public static int TIER1_DEFENSE_MINION_HEALTH = 100;
    public static int TIER1_DEFENSE_MINION_ATTACK = 1;
    public static int TIER1_DEFENSE_MINION_ATTACK_FREQUENCY = 10;
    public static int TIER1_DEFENSE_MINION_SIGHT = 400;
    public static int TIER1_DEFENSE_MINION_PENETRATION = 10;
    
    public static int TIER2_DEFENSE_MINION_SPEED = 1;
    public static int TIER2_DEFENSE_MINION_PRODUCTION_TIME = 8;
    public static int TIER2_DEFENSE_MINION_HEALTH = 150;
    public static int TIER2_DEFENSE_MINION_ATTACK = 1;
    public static int TIER2_DEFENSE_MINION_ATTACK_FREQUENCY = 10;
    public static int TIER2_DEFENSE_MINION_SIGHT = 500;
    public static int TIER2_DEFENSE_MINION_PENETRATION = 10;
    
    public static int TIER3_DEFENSE_MINION_SPEED = 1;
    public static int TIER3_DEFENSE_MINION_PRODUCTION_TIME = 10;
    public static int TIER3_DEFENSE_MINION_HEALTH = 200;
    public static int TIER3_DEFENSE_MINION_ATTACK = 1;
    public static int TIER3_DEFENSE_MINION_ATTACK_FREQUENCY = 10;
    public static int TIER3_DEFENSE_MINION_SIGHT = 600;
    public static int TIER3_DEFENSE_MINION_PENETRATION = 10;
    
    public static int DEFENSE_MINION_JITTER_TIMER = 20;
    public static int DEFENSE_MINION_DECAY_RATE = 5;
    
    public static int TIER1_SMART_MINION_SPEED = 8;
    public static int TIER1_SMART_MINION_PRODUCTION_TIME = 10;
    public static int TIER1_SMART_MINION_HEALTH = 1;
    public static int TIER1_SMART_MINION_ATTACK = 1;
    public static int TIER1_SMART_MINION_ATTACK_FREQUENCY = 0;
    public static int TIER1_SMART_MINION_SIGHT = 150;
    public static int TIER1_SMART_MINION_PENETRATION = 1;
    
    public static int TIER2_SMART_MINION_SPEED = 10;
    public static int TIER2_SMART_MINION_PRODUCTION_TIME = 10;
    public static int TIER2_SMART_MINION_HEALTH = 1;
    public static int TIER2_SMART_MINION_ATTACK = 1;
    public static int TIER2_SMART_MINION_ATTACK_FREQUENCY = 0;
    public static int TIER2_SMART_MINION_SIGHT = 150;
    public static int TIER2_SMART_MINION_PENETRATION = 1;
    
    public static int TIER3_SMART_MINION_SPEED = 12;
    public static int TIER3_SMART_MINION_PRODUCTION_TIME = 10;
    public static int TIER3_SMART_MINION_HEALTH = 1;
    public static int TIER3_SMART_MINION_ATTACK = 1;
    public static int TIER3_SMART_MINION_ATTACK_FREQUENCY = 0;
    public static int TIER3_SMART_MINION_SIGHT = 150;
    public static int TIER3_SMART_MINION_PENETRATION = 1;
    
    public static int SMART_MINION_ACCELERATION_SCALE = 32;
    public static int SMART_MINION_TOLERANCE = 25;
    public static int MOVING = 0;
    public static int ATTACKING = 1;
    public static int WAITING = 2;
    
    public static int MINION_PRODUCTION_SCALE = 50;
    public static int MINION_PAUSE_SCALE = 10;
    public static int MINION_SPAWN_LOCATION_RANDOMNESS_SCALE = 25;
}
