
package thePackage.debugger;

import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import thePackage.Entity;
import thePackage.Manager;
import thePackage.Text;

class RectDebugTrinket extends TrinketBase implements RectDebugTrinketSettings, IsDebugger{
    private HashMap <Entity,SubRectDebugTrinket> subRectDebugTrinkets;
    protected RectDebugTrinket(double xPosition, double yPosition){
        super(xPosition, yPosition);
        sprite.addImage(IMAGE, "main", true);
        resizeByCorner();
        
        subRectDebugTrinkets = new HashMap<>();
        Text labels = new Text();
        labels.setColor(STANDARD_COLOR);
        labels.setFont(new Font(DEBUGGER_FONT,Font.BOLD,FONT_SIZE));
        labels.setCornerX(() -> {return rect.getCornerX() + LABELS_OFFSET_X;});
        labels.setCenterY(() -> {return rect.getCenterY() + LABELS_OFFSET_Y;});
        labels.setMessage(LABELS_STRING);
        addStat(labels);

    }
    
    public void subUpdate() {
    }
    
    protected void entityAdded(Entity input){
        SubRectDebugTrinket temp = new SubRectDebugTrinket(input.getRect(),
                rect.getCornerX(),
                (rect.getCornerY() + rect.getHeight()) + subRectDebugTrinkets.size() * SUB_RECT_DEBUG_TRINKET_OFFSET_Y);
        subRectDebugTrinkets.put(input,temp);
        Manager.queueNewEntity(temp);
    }
    
    protected void entityRemoved(Entity input) {
        Manager.removeEntity(subRectDebugTrinkets.get(input));
        subRectDebugTrinkets.remove(input);
        
    }
}
