package thePackage.debugger; ;

import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import thePackage.Entity;
import thePackage.GameData;
import thePackage.Key;
import thePackage.KeyCommand;
import thePackage.Manager;
import thePackage.Text;

class ControlDebugTrinket extends TrinketBase implements ControlDebugTrinketSettings, DebuggerTag, GameData{
    Entity exampleEntity;
    private HashMap<Entity, KeyDebugTrinket> keyDebugTrinketMap;
    private ArrayList<Text> actions;
    private int listSizeError;
    protected ControlDebugTrinket(Entity input, double xPosition, double yPosition) {
        super(xPosition, yPosition);
        sprite.addImage(IMAGE, "main", true);
        resizeByCorner();
        
        exampleEntity = input;
        
        actions = new ArrayList<>();
        fillActions();

        keyDebugTrinketMap = new HashMap<>();
    }
    
    public void subUpdate(){
    }
    
    private void fillActions() {
        HashMap <String, KeyCommand> keyMapPressed = DebugTool.getInstance().getEntityKeyMapPressed(exampleEntity);
        ArrayList <Key> keys = exampleEntity.getKeys();
        HashMap<KeyCommand, String> keyMapPressed$ = new HashMap<>();
        for (String action: keyMapPressed.keySet()){
            for (KeyCommand commandPressed: keyMapPressed.values()){
                if (keyMapPressed.get(action) == commandPressed) {
                    keyMapPressed$.put(commandPressed, action);
                }
            }
        }
        for (Key key: keys){
            Text temp = new Text();
            formatText(temp);
            String action = keyMapPressed$.get(key.getCommandPressed(this));
            if (action.length() > 5) {
                temp.setMessage(action.substring(0, 3) + "...");
            }
            else {
                temp.setMessage(action);
            }
            addStat(temp);
        }
        listSizeError = 0;
    }
    
    protected void entityAdded(Entity input) {
        KeyDebugTrinket temp = new KeyDebugTrinket(input.getKeys(),
                rect.getCornerX(),
                (rect.getCornerY() + rect.getHeight()) + (KEY_DEBUG_TRINKET_OFFSET_Y * (keyDebugTrinketMap.size() + listSizeError)));
        Manager.queueNewEntity(temp);
        keyDebugTrinketMap.put(input, temp);
    }
    
    protected void entityRemoved(Entity input){
        Manager.removeEntity(keyDebugTrinketMap.get(input));
        keyDebugTrinketMap.remove(input);
        listSizeErrorIncrement();
    }
    
    private void formatText(Text input) {
        actions.add(input);
        input.setColor(STANDARD_COLOR);
        input.setFont(new Font(DEBUGGER_FONT,Font.PLAIN,FONT_SIZE));
        input.setCornerX(() -> {return rect.getCornerX() + actions.indexOf(input) * ACTIONS_OFFSET_X;});
        input.setCenterY(() -> {return rect.getCenterY();});
    }
    
    protected boolean noControls() {
        if (actions.isEmpty()){
            for (KeyDebugTrinket keyDebugTrinket: keyDebugTrinketMap.values()){
                Manager.removeEntity(keyDebugTrinket);
            }
            return true;
        }
        else {
            return false;
        }
    }
    
    private void listSizeErrorIncrement() {
        listSizeError += 1;
        if (listSizeError > DISPLAY_OVERFLOW){
            listSizeError = 0;
        }
    }
}
