
package javaKat.debugger; ;

import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import javaKat.Entity;
import javaKat.Manager;
import javaKat.Sprite;
import javaKat.Text;

class RectDebugTrinket extends TrinketBase implements RectDebugTrinketSettings, DebuggerTag{
    private HashMap <Entity,SubRectDebugTrinket> subRectDebugTrinkets;
    private ArrayList<Text> labelList;
    private int listSizeError;
    protected RectDebugTrinket(double xPosition, double yPosition){
        super(xPosition, yPosition);
        Sprite sprite = new Sprite(this);
        sprite.addImage(IMAGE, "main");
        sprite.setImage("main");
        
        subRectDebugTrinkets = new HashMap<>();
        labelList = new ArrayList<>();
        
        Text xCoordinate = new Text(this);
        formatText(xCoordinate);
        xCoordinate.setMessage("x-pos");
        
        Text yCoordinate = new Text(this);
        formatText(yCoordinate);
        yCoordinate.setMessage("y-pos");
        
        Text angle = new Text(this);
        formatText(angle);
        angle.setMessage("angle");
        
        Text width = new Text(this);
        formatText(width);
        width.setMessage("width");
        
        Text height = new Text(this);
        formatText(height);
        height.setMessage("height");

        listSizeError = 0;
    }
    
    public void subUpdate() {
    }
    
    protected void entityAdded(Entity input){
        SubRectDebugTrinket temp = new SubRectDebugTrinket(input.getRect(),
                rect.getCornerX(),
                (rect.getCornerY() + rect.getHeight()) + ((subRectDebugTrinkets.size() + listSizeError) * SUB_RECT_DEBUG_TRINKET_OFFSET_Y));
        subRectDebugTrinkets.put(input,temp);
        Manager.queueNewEntity(temp);
    }
    
    protected void entityRemoved(Entity input) {
        Manager.removeEntity(subRectDebugTrinkets.get(input));
        subRectDebugTrinkets.remove(input);
        listSizeErrorIncrement();
    }
    
    private void formatText(Text input) {
        labelList.add(input);
        input.setColor(STANDARD_COLOR);
        input.setFont(new Font(DEBUGGER_FONT,Font.PLAIN,FONT_SIZE));
        input.setCornerX(() -> {return rect.getCornerX() + labelList.indexOf(input) * LABELS_OFFSET_X;});
        input.setCenterY(() -> {return rect.getCenterY();});
    }
    
    private void listSizeErrorIncrement() {
        listSizeError += 1;
        if (listSizeError > DISPLAY_OVERFLOW){
            listSizeError = -subRectDebugTrinkets.size();
        }
    }
}
