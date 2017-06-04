package thePackage.debugger;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import thePackage.Entity;
import thePackage.GameData;
import thePackage.Manager;
import thePackage.Sprite;
import thePackage.Text;
import thePackage.Utility;

class EntityDebugTrinket extends TrinketBase implements IsDebugger, EntityDebugTrinketSettings{
    private Entity exampleEntity;
    private ArrayList<Entity> discoveredEntities;
    private ControlDebugTrinket controlDebugTrinket;
    private RectDebugTrinket rectDebugTrinket;
    private IndicatorDebugTrinket indicatorDebugTrinket;
    protected EntityDebugTrinket (Entity input) {
        super();
        exampleEntity = input;
        discoveredEntities = new ArrayList<>();
        Text entityType = new Text();
        entityType.setColor(STANDARD_COLOR);
        entityType.setFont(new Font(Font.SANS_SERIF,Font.BOLD,ENTITY_TYPE_FONT_SIZE));
        entityType.setMessage(exampleEntity.getClass().getName());
        entityType.setCenterX(() -> {return rect.getCenterX() + ENTITY_TYPE_OFFSET_X;});
        entityType.setCenterY(() -> {return rect.getCenterY() + ENTITY_TYPE_OFFSET_Y;});
        addStat(entityType);
        
        Text entityNumber = new Text();
        entityNumber.setColor(STANDARD_COLOR);
        entityNumber.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,24));
        entityNumber.setMessage(() -> {
            int temp = countInstances();
            if (temp == 0){
                entityNumber.setColor(WARNING_COLOR);
            }
            else {
                entityNumber.setColor(STANDARD_COLOR);
            }
            return Integer.toString(countInstances());
        });
        entityNumber.setCenterX(() -> {return rect.getCenterX() + ENTITY_COUNT_OFFSET_X;});
        entityNumber.setCenterX(() -> {return rect.getCenterY() + ENTITY_COUNT_OFFSET_Y;});
        addStat(entityNumber);
        
        controlDebugTrinket = new ControlDebugTrinket(exampleEntity);
        controlDebugTrinket.getRect().setCornerX(rect.getCornerX() + CONTROL_DEBUG_TRINKET_OFFSET_X);
        controlDebugTrinket.getRect().setCornerY(rect.getCornerY() + rect.getHeight() + CONTROL_DEBUG_TRINKET_OFFSET_Y);
        Manager.queueNewEntity(controlDebugTrinket);
        
        rectDebugTrinket = new RectDebugTrinket();
        rectDebugTrinket.getRect().setCornerX(rect.getCornerX() + RECT_DEBUG_TRINKET_OFFSET_X);
        rectDebugTrinket.getRect().setCornerY(rect.getCornerY() + rect.getHeight() + RECT_DEBUG_TRINKET_OFFSET_Y);
        Manager.queueNewEntity(rectDebugTrinket);
        
        indicatorDebugTrinket = new IndicatorDebugTrinket();
        //DOESN'T NEED TO BE ADDED
        sprite.addImage("thePackage/debugger/EntityDebugTrinket.png","main",true);
        resize();
    }
    public void subUpdate() {
        for (Entity input: GameData.allEntities){
            if (input.matchesClassOf(exampleEntity,this)){
                if (!discoveredEntities.contains(input)){
                    entityAdded(input);
                }
            }
        }
        for (Entity input: discoveredEntities){
            if (!Manager.findThisEntity(input)){
                entityRemoved(input);
            }
        }
    }
    
    private int countInstances() {
        int counter = 0;
        for (Entity input: GameData.allEntities){
            if (input.matchesClassOf(exampleEntity, this)){
                counter += 1;
            }
        }
        return counter;
    }
    
    private void entityAdded(Entity input) {
        discoveredEntities.add(input);
        controlDebugTrinket.entityAdded(input);
        rectDebugTrinket.entityAdded(input);
        indicatorDebugTrinket.entityAdded(input);
        
    }
    
    private void entityRemoved(Entity input) {
        discoveredEntities.remove(input);
        controlDebugTrinket.entityRemoved(input);
        rectDebugTrinket.entityRemoved(input);
        indicatorDebugTrinket.entityRemoved(input);
    }

}
