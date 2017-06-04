package thePackage.debugger;

import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Set;
import thePackage.Entity;
import thePackage.GameData;
import thePackage.KeyCommand;
import thePackage.Manager;
import thePackage.Text;

class ControlDebugTrinket extends TrinketBase implements ControlDebugTrinketSettings, IsDebugger, GameData{
    Entity exampleEntity;
    private HashMap<Entity, KeyDebugTrinket> keyDebugTrinkets;
    protected ControlDebugTrinket(Entity input, double xPosition, double yPosition) {
        super(xPosition, yPosition);
        sprite.addImage(IMAGE, "main", true);
        resizeByCorner();
        
        exampleEntity = input;
        Text availableKeys = new Text();
        availableKeys.setColor(STANDARD_COLOR);
        availableKeys.setFont(new Font(Font.SANS_SERIF,Font.ITALIC,FONT_SIZE));
        availableKeys.setMessage(getActions());
        availableKeys.setCenterX(() -> {return rect.getCenterX() + ACTIONS_OFFSET_X;});
        availableKeys.setCenterY(() -> {return rect.getCenterY() + ACTIONS_OFFSET_Y;});
        addStat(availableKeys);
        keyDebugTrinkets = new HashMap<>();

    }
    
    public void subUpdate(){
    }
    
    private String getActions() {
        String stack = "";
        HashMap <String, KeyCommand>keyMapPressed = exampleEntity.getKeyMapPressed(this);
        Set <String> actionSet = keyMapPressed.keySet();
        for (String action: actionSet){
            stack += actionSet + "\t";
        }
        return stack;
    }
    
    protected void entityAdded(Entity input) {
        KeyDebugTrinket temp = new KeyDebugTrinket(input.getKeys(),
                rect.getCornerX(),
                (rect.getCornerY() + rect.getHeight()) + KEY_DEBUG_TRINKET_OFFSET_Y * keyDebugTrinkets.size());
        Manager.queueNewEntity(temp);
        keyDebugTrinkets.put(input, temp);
    }
    
    protected void entityRemoved(Entity input){
        Manager.removeEntity(keyDebugTrinkets.get(input));
        keyDebugTrinkets.remove(input);
    }
}