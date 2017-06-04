package thePackage.debugger;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import thePackage.Entity;
import thePackage.Key;
import thePackage.Text;

class KeyDebugTrinket extends TrinketBase implements KeyDebugTrinketSettings, IsDebugger{
    private HashMap<String,Text> individualKeys;
    private HashMap<String,Integer> referenceValues;
    protected KeyDebugTrinket(ArrayList<Key> keys, double xPosition, double yPosition) {
        super(xPosition, yPosition);
        sprite.addImage(IMAGE, "main", true);
        resizeByCorner();
        
        int counter = 0;
        individualKeys = new HashMap<>();
        referenceValues = new HashMap<>();
        for (Key key: keys){
            counter += 1;
            bindCodeToAction(key.toString(),(a) -> {
                individualKeys.get(a.getClass().getName()).setColor(WARNING_COLOR);
            }, (a) -> {
                individualKeys.get(a.getClass().getName()).setColor(STANDARD_COLOR);
            });
            bindKeyToAction(key.getInput(), key.toString());
            Text temp = new Text();
            temp.setColor(STANDARD_COLOR);
            temp.setFont(new Font(DEBUGGER_FONT,Font.PLAIN,FONT_SIZE));
            temp.setMessage(Character.toString((char)(key.getInput())));
            temp.setCenterX(() -> {return rect.getCenterX() + OFFSET_X * referenceValues.get(key.getClass().getName());});
            temp.setCenterY(() -> {return rect.getCenterY();});
            individualKeys.put(key.getClass().getName(),temp);
            referenceValues.put(key.getClass().getName(), counter);
            addStat(temp);
            
        }

    }
    
    public void subUpdate() {
        
    }
}
