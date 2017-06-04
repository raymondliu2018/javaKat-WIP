package workspace;
import thePackage.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyEvent;

public class Script implements SuperScript ,PlayerData {
    static Player red,blue;
    static String winner = null;
    public static void init() {
        Camera.setBackground(Utility.scaleImage("images/Background.png",SCREEN_WIDTH,SCREEN_HEIGHT));
        
        red = new Player("red");
        red.bindKeyToAction('w',"up");
        red.bindKeyToAction('a',"left");
        red.bindKeyToAction('s',"down");
        red.bindKeyToAction('d',"right");
        
        red.bindKeyToAction(KeyEvent.VK_SPACE,"primary");
        red.bindKeyToAction('q',"secondary");
        
        red.getRect().setCenterX(SCREEN_WIDTH/4.0);
        red.getRect().setCenterY(SCREEN_HEIGHT/4.0);
        
        Manager.queueNewEntity(red);
        
        blue = new Player("blue");
        blue.bindKeyToAction(KeyEvent.VK_UP,"up");
        blue.bindKeyToAction(KeyEvent.VK_LEFT,"left");
        blue.bindKeyToAction(KeyEvent.VK_DOWN,"down");
        blue.bindKeyToAction(KeyEvent.VK_RIGHT,"right");
        
        blue.bindKeyToAction('/',"primary");
        blue.bindKeyToAction('.',"secondary");
        
        blue.getRect().setCenterX(3.0*SCREEN_WIDTH/4.0);
        blue.getRect().setCenterY(3.0*SCREEN_HEIGHT/4.0);
        Manager.queueNewEntity(blue);
    }
    
    public static void main(String [] args) {
        new GameMaster("NAME");
    }
    
    public static void loop() {
    }
    
    public static void end() {
        GameMaster.disable();
        Text message = new Text();
        message.setFont(new Font(Font.SANS_SERIF,Font.BOLD,48));
        if (winner.equals("red")){
            message.setColor(Color.RED);
        }
        if (winner.equals("blue")){
            message.setColor(Color.BLUE);
        }
        message.setMessage(winner + " wins!");
        message.setCenterX(GameMaster.getWidth()/2.0);
        message.setCenterY(GameMaster.getHeight()/2.0);
        Manager.addStat(message);
    }
    
    public static void collide(Entity entity1, Entity entity2) {
    }
    
    public static void winner(String input) {
        winner = input;
    }
}

