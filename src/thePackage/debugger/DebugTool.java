package thePackage.debugger;

import java.util.ArrayList;
import thePackage.Entity;
import thePackage.GameData;
import thePackage.Manager;

public class DebugTool extends Entity implements DebugToolSettings, IsDebugger{
    private ArrayList<String> entityTypes;
    protected DebugTool() {
        super();
        entityTypes = new ArrayList<>();
        sprite.addImage(IMAGE, "main", true);
        resizeByCorner();
    }
    public void subUpdate() {
        for (Entity input : GameData.allEntities){
            if (!(input instanceof IsDebugger)){
                boolean contained = false;
                for (String input$ : entityTypes){
                    if (input.matchesClassOf(input$, this)){
                        contained = true;
                    }
                }
                if (!contained) {
                    entityAdded(input);
                }
            }
        }
    }
    
    private void entityAdded(Entity input) {
        EntityDebugTrinket entityDebugTrinket = new EntityDebugTrinket(input,
                rect.getCornerX() + entityTypes.size() * ENTITY_DEBUG_TRINKET_OFFSET_X,
                rect.getCornerY() + rect.getHeight());
        Manager.queueNewEntity(entityDebugTrinket);
        entityTypes.add(input.getClass().getName());
    }
}
