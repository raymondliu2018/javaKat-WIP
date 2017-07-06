package javaKat.debugger; ;

import javaKat.Tag;
import javaKat.Album;
import java.awt.Font;
import java.util.ArrayList;
import javaKat.Entity;
import javaKat.Manager;
import javaKat.Utility;

class EntityDebugTrinket extends TrinketBase implements DebuggerTag, EntityDebugTrinketSettings{
    private String entityName;
    private ArrayList<Entity> discoveredEntities;
    private ControlDebugTrinket controlDebugTrinket;
    private RectDebugTrinket rectDebugTrinket;
    private int instances;
    private Album album;
    protected EntityDebugTrinket (Entity input, double xPosition, double yPosition) {
        super(xPosition, yPosition);
        album = new Album(this);
        album.addPageWithPicture(IMAGE,"main");
        album.setPage("main");   
        this.resizeByCenter(album.getCurrentPageWidth(),album.getCurrentPageHeight());
        
        entityName = input.getClass().getName();
        discoveredEntities = new ArrayList<>();
        
        controlDebugTrinket = new ControlDebugTrinket(input,
                rect.getCornerX() + CONTROL_DEBUG_TRINKET_OFFSET_X,
                rect.getCornerY() + rect.getHeight() + CONTROL_DEBUG_TRINKET_OFFSET_Y);
        Manager.queueNewEntity(controlDebugTrinket);
        
        rectDebugTrinket = new RectDebugTrinket(
                rect.getCornerX() + RECT_DEBUG_TRINKET_OFFSET_X,
                rect.getCornerY() + rect.getHeight() + RECT_DEBUG_TRINKET_OFFSET_Y);
        Manager.queueNewEntity(rectDebugTrinket);
        
        //DOESN'T NEED TO BE ADDED
        Tag entityType = new Tag(this);
        entityType.setColor(STANDARD_COLOR);
        entityType.setFont(new Font(DEBUGGER_FONT,Font.BOLD,ENTITY_TYPE_FONT_SIZE));
        entityType.setMessage(truncateString(entityName.substring(entityName.lastIndexOf(".") + 1)));
        entityType.setCornerX(() -> {return rect.getCornerX() + ENTITY_TYPE_OFFSET_X;});
        entityType.setCenterY(() -> {return rect.getCenterY() + ENTITY_TYPE_OFFSET_Y;});
        
        Tag entityNumber = new Tag(this);
        entityNumber.setColor(STANDARD_COLOR);
        entityNumber.setFont(new Font(DEBUGGER_FONT,Font.BOLD,ENTITY_COUNT_FONT_SIZE));
        entityNumber.setMessage(() -> {
            int temp = instances;
            if (temp == 0){
                entityNumber.setColor(WARNING_COLOR);
            }
            else {
                entityNumber.setColor(STANDARD_COLOR);
            }
            return Integer.toString(temp);
        });
        entityNumber.setCornerX(() -> {return rect.getCornerX() + rect.getWidth() + ENTITY_COUNT_OFFSET_X;});
        entityNumber.setCenterY(() -> {return rect.getCenterY() + ENTITY_COUNT_OFFSET_Y;});
        instances = 0;
    }
    
    public void subUpdate() {
    }
    
    protected void entityAdded(Entity input) {
        discoveredEntities.add(input);
        rectDebugTrinket.entityAdded(input);
        
        if (!controlDebugTrinket.noControls()){
            controlDebugTrinket.entityAdded(input);
        }
        instances += 1;
    }
    
    protected void entityRemoved(Entity input) {
        discoveredEntities.remove(input);
        rectDebugTrinket.entityRemoved(input);
        
        if (!controlDebugTrinket.noControls()){
        controlDebugTrinket.entityRemoved(input);
        }
        instances -= 1;
    }
    
    protected boolean noControls(){
        if (controlDebugTrinket.noControls()){
            Manager.removeEntity(controlDebugTrinket);
            album.addPageWithPicture(Utility.scaleImage(album.getPicture(), 250, 25), "smaller");
            album.setPage("smaller");
            return true;
        }
        else {
            return false;
        }
    }
    
    private String truncateString(String input) {
        if (controlDebugTrinket.noControls()){
            if (input.length() > 13){
                return input.substring(0, 11) + "...";
            }
            else {
                return input;
            }
        }
        return input;
    }
}
