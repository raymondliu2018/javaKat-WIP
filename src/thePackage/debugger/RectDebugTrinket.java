
package thePackage.debugger;

import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import thePackage.Entity;
import thePackage.Manager;
import thePackage.Text;

class RectDebugTrinket extends TrinketBase implements RectDebugTrinketSettings, IsDebugger{
    private HashMap <Entity,SubRectDebugTrinket> subRectDebugTrinkets;
    protected RectDebugTrinket(){
        subRectDebugTrinkets = new HashMap<>();
        Text labels = new Text();
        labels.setColor(STANDARD_COLOR);
        labels.setFont(new Font(Font.SANS_SERIF,Font.BOLD,FONT_SIZE));
        labels.setCenterX(rect.getCenterX() + LABELS_OFFSET_X);
        labels.setCenterY(rect.getCenterY() + LABELS_OFFSET_Y);
        labels.setMessage(LABELS_STRING);
        addStat(labels);
        sprite.addImage("thePackage/debugger/RectDebugTrinket.png", "main", true);
        resize();
    }
    
    public void subUpdate() {
    }
    
    protected void entityAdded(Entity input){
        SubRectDebugTrinket temp = new SubRectDebugTrinket(input.getRect());
        temp.getRect().setCornerX(rect.getCornerX());
        temp.getRect().setCornerY((rect.getCornerY() + rect.getHeight()) + subRectDebugTrinkets.size() * SUB_RECT_DEBUG_TRINKET_OFFSET_Y);
        Manager.queueNewEntity(temp);
    }
    
    protected void entityRemoved(Entity input) {
        Manager.removeEntity(subRectDebugTrinkets.get(input));
        subRectDebugTrinkets.remove(input);
        
    }
}
