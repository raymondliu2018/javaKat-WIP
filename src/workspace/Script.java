package workspace; 
import javaKat.GameMaster;
import javaKat.SuperScript;
import javaKat.Entity;

public class Script implements SuperScript {
    public static void init() {
    }
    
    public static void main(String [] args) {
        GameMaster.start("NAME");
    }
    
    public static void loop() {
    }
    
    public static void end() {
    }
    
    public static void collide(Entity entity1, Entity entity2) {
    }
}

