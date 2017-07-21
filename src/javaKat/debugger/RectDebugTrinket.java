
package javaKat.debugger; ;

import javaKat.PositionMode;
import javaKat.Tag;
import javaKat.Album;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import javaKat.Entity;
import javaKat.Manager;

class RectDebugTrinket extends TrinketBase implements RectDebugTrinketSettings, DebuggerTag{
    private HashMap <Entity,SubRectDebugTrinket> subRectDebugTrinkets;
    private ArrayList<Tag> labelList;
    private int listSizeError;
    protected RectDebugTrinket(double xPosition, double yPosition){
        super(xPosition, yPosition);
        Album album = new Album(this);
        album.addPageWithPicture(IMAGE, "main");
        album.setPage("main");
        album.setPositionMode(PositionMode.BY_RECT);
        this.resizeByCorner(album.getCurrentPageWidth(),album.getCurrentPageHeight());
        
        subRectDebugTrinkets = new HashMap<>();
        labelList = new ArrayList<>();
        
        Tag xCoordinate = new Tag(this);
        formatText(xCoordinate);
        xCoordinate.setMessage("x-pos");
        
        Tag yCoordinate = new Tag(this);
        formatText(yCoordinate);
        yCoordinate.setMessage("y-pos");
        
        Tag angle = new Tag(this);
        formatText(angle);
        angle.setMessage("angle");
        
        Tag width = new Tag(this);
        formatText(width);
        width.setMessage("width");
        
        Tag height = new Tag(this);
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
    }
    
    protected void entityRemoved(Entity input) {
        Manager.removeEntity(subRectDebugTrinkets.get(input));
        subRectDebugTrinkets.remove(input);
        listSizeErrorIncrement();
    }
    
    private void formatText(Tag input) {
        input.setPositionMode(PositionMode.BY_INPUT);
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
