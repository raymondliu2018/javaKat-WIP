package workspace.minions;

public class DefenseMinion extends Minion implements MinionData{
    private int internalTimer2;
    public DefenseMinion(String color) {
        super(color);
    }
    public void move() {
        rect.setMaxSpeed(speed);
        if (timer - internalTimer2 >= DEFENSE_MINION_JITTER_TIMER){
            internalTimer2 = timer;
            System.out.println("trying");
            rect.accel(speed * (Math.random() * 2 - 1), speed * (Math.random() * 2 - 1));
            dealDamage(DEFENSE_MINION_DECAY_RATE);
        }
    }
}
