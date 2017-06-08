package thePackage.debugger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import thePackage.Entity;
import thePackage.GameData;
import thePackage.Manager;

public class DebugTool extends TrinketBase implements DebugToolSettings, DebuggerTag{
    private ArrayList<String> entityTypes;
    private ArrayList<Entity> registeredEntities;
    private ArrayList<Integer> entityDebugTrinketOffsetList;
    private HashMap<String,EntityDebugTrinket> entityDebugTrinketMap;
    protected DebugTool() {
        super(0,0);
        entityTypes = new ArrayList<>();
        entityDebugTrinketOffsetList = new ArrayList<>();
        entityDebugTrinketMap = new HashMap<>();
        registeredEntities = new ArrayList<>();
        sprite.addImage(IMAGE, "main", true);
        resizeByCorner();
    }
    public void subUpdate() {
        for (Entity input : GameData.allEntities){
            if (!(input instanceof DebuggerTag)){
                boolean typeContained = false;
                for (String input$ : entityTypes){
                    if (input.matchesClassOf(input$, this)){
                        typeContained = true;
                        boolean instanceContained = false;
                        for (Entity entity: registeredEntities) {
                            if (input == entity) {
                                instanceContained = true;
                                break;
                            }
                        }
                        if (!instanceContained) {
                            entityDebugTrinketMap.get(input$).entityAdded(input);
                            registeredEntities.add(input);
                        }
                        break;
                    }
                }
                if (!typeContained) {
                    entityAdded(input);
                }
            }
        }
        ListIterator <Entity> iterator = registeredEntities.listIterator();
        while (iterator.hasNext()) {
            Entity input = iterator.next();
            if (!Manager.findThisEntity(input)){
                entityDebugTrinketMap.get(input.getClass().getName()).entityRemoved(input);
                iterator.remove();
            }
        }
    }
    
    private int getOffset() {
        int total = 0;
        for (Integer width: entityDebugTrinketOffsetList){
            total += width;
        }
        return total;
    }
    
    private void entityAdded(Entity input) {
        EntityDebugTrinket entityDebugTrinket = new EntityDebugTrinket(input,
                rect.getCornerX() + getOffset(),
                rect.getCornerY() + rect.getHeight());
        if (entityDebugTrinket.noControls()) {
            entityDebugTrinketOffsetList.add(250);
        }
        else {
            entityDebugTrinketOffsetList.add(750);
        }
        Manager.queueNewEntity(entityDebugTrinket);
        entityTypes.add(input.getClass().getName());
        entityDebugTrinketMap.put(input.getClass().getName(),entityDebugTrinket);
    }
}
