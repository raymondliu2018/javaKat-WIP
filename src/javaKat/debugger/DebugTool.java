package javaKat.debugger; ;

import javaKat.Album;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import javaKat.Entity;
import javaKat.GameData;
import javaKat.Manager;

public final class DebugTool extends TrinketBase implements DebugToolSettings, DebuggerTag{
    private ArrayList<String> entityTypes;
    private ArrayList<Entity> registeredEntities;
    private ArrayList<Integer> entityDebugTrinketOffsetList;
    private HashMap<String,EntityDebugTrinket> entityDebugTrinketMap;
    private static DebugTool instance = null;
    
    protected DebugTool() {
        super(0,0);
        entityTypes = new ArrayList<>();
        entityDebugTrinketOffsetList = new ArrayList<>();
        entityDebugTrinketMap = new HashMap<>();
        registeredEntities = new ArrayList<>();
        Album album = new Album(this);
        album.addPageWithPicture(IMAGE, "main");
        album.setPage("main");
        this.resizeByCenter(album.getCurrentPageWidth(),album.getCurrentPageHeight());
        
        instance = this;
    }
    
    protected static DebugTool getInstance() {
        return instance;
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
    
    protected HashMap getEntityKeyMapPressed(Entity input){
        return input.getKeyMapPressed(this);
    }
    
    protected HashMap getEntityKeyMapReleased(Entity input) {
        return input.getKeyMapReleased(this);
    }
    
    protected boolean entityClassMatchesString(Entity entity, String input){
        return entity.matchesClassOf(input, this);
    }
}
