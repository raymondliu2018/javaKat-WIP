package thePackage.debugger;

import java.util.ArrayList;
import thePackage.Entity;
import thePackage.GameData;
import thePackage.Manager;

public class DebugTool extends Entity implements DebugToolSettings, IsDebugger{
    private ArrayList<Entity> entityTypes;
    protected DebugTool() {
        super();
        rect.setCenterX(CENTER_X);
        rect.setCenterY(CENTER_Y);
        entityTypes = new ArrayList<>();
    }
    public void subUpdate() {
        for (Entity input : entityTypes){
            boolean contained = false;
            for (Entity input$ : GameData.allEntities){
                if (input.matchesClassOf(input$, this)){
                    contained = true;
                }
            }
            if (!contained) {
                entityAdded(input);
            }
        }
    }
    
    private void entityAdded(Entity input) {
        EntityDebugTrinket entityDebugTrinket = new EntityDebugTrinket(input);
        entityDebugTrinket.getRect().setCornerX(rect.getCornerX() + entityTypes.size() * ENTITY_DEBUG_TRINKET_OFFSET_X );
        entityDebugTrinket.getRect().setCornerY(rect.getCornerY());
        Manager.queueNewEntity(entityDebugTrinket);
    }
}
