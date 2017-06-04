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
    protected KeyDebugTrinket(ArrayList<Key> keys) {
        super();
        int counter = 0;
        individualKeys = new HashMap<>();
        for (Key key: keys){
            counter += 1;
            bindCodeToAction(key.toString(),(a) -> {
                Text temp = individualKeys.get(key.toString());
                temp.setColor(WARNING_COLOR);
            }, (a) -> {
                Text temp = individualKeys.get(key.toString());
                temp.setColor(STANDARD_COLOR);
            });
            bindKeyToAction(key.getInput(), key.toString());
            Text temp = new Text();
            temp.setColor(STANDARD_COLOR);
            temp.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,FONT_SIZE));
            temp.setMessage(Character.toString((char)(key.getInput())));
            temp.setCenterX(rect.getCenterX() + OFFSET_X * counter);
            temp.setCenterY(rect.getCenterY());
            addStat(temp);
            
        }
        sprite.addImage("thePackage/debugger/KeyDebugTrinket.png", "main", true);
        resize();
    }
    
    public void subUpdate() {
        
    }
}
