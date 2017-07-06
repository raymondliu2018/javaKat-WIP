package javaKat;  

import java.util.ArrayList;
import javaKat.debugger.DebuggerTag;

public final class Manager extends Manipulator implements GameData{
    private static ArrayList<VoidCommand> queuedCommands = new ArrayList<>();
    protected static void run() {
        if (enabled){
            for (VoidCommand command : queuedCommands) {
                command.run();
            }
            queuedCommands.clear();
        }
    }
    
    public static boolean commandsQueued(Object requestor) {
        if (requestor instanceof DebuggerTag){
            return queuedCommands.isEmpty();
        }
        throw new IllegalArgumentException(DebuggerTag.DEBUGGER_MESSAGE);
    }
    
    public static void queueWipeAll() {
        queuedCommands.add(() -> {wipeAll();});
    }
    
    public static void wipeAll() {
         GameData.allEntities.clear();
         GameData.sprites.clear();
         GameData.focusedEntities.clear();
         GameData.keys.clear();
         GameData.buttons.clear();
         GameData.layers.clear();
         GameData.imageFiles.clear();
         GameData.images.clear();
         GameData.stats.clear();
         GameData.enders.clear();
    }
    
    public static void queueNewEntity( Entity input ) {
        addEntity(input);
    }
    
    public static void addStat( Text input ) {
        queuedCommands.add(() -> {GameData.stats.add(input);});
    }
    
    public static void removeStat( Text input ) {
        queuedCommands.add(() -> {GameData.stats.remove(input);});
    }
    
    public static boolean findThisEntity( Entity input ) {
        return GameData.allEntities.indexOf(input) != -1;
    }
    
    private static void addEntity( Entity input ) {
        queuedCommands.add(() -> {if (!input.superCalled()){throw new RuntimeException("Super not called on " + input);}});
        queuedCommands.add(() -> {GameData.keys.addAll(input.getKeys());});
        queuedCommands.add(() -> {GameData.buttons.addAll(input.getButtons());});
        addSprites(input.getSprites(),input.getLayer());
        addRect(input.getRect(),input.getLayer());
        addEnder(input);
        addFocused(input);
        queuedCommands.add(() -> {GameData.allEntities.add(input);});
    }
    
    protected static void addSprites(ArrayList <Sprite> sprites, int layer) {
        for (Sprite sprite: sprites) {
            addSprite(sprite, layer);
        }
    }
    
    public static void removeEntity( Entity input ){
        queuedCommands.add(() -> {GameData.keys.removeAll(input.getKeys());});
        queuedCommands.add(() -> {GameData.buttons.removeAll(input.getButtons());});
        queuedCommands.add(() -> {GameData.stats.removeAll(input.getStats());});
        removeSprites(input.getSprites(), input.getLayer());
        removeRect(input.getRect(),input.getLayer());
        removeEnder(input);
        removeFocused(input);
        queuedCommands.add(() -> {GameData.allEntities.remove(input);});
    }
    
    protected static void removeSprites(ArrayList <Sprite> sprites, int layer) {
        for (Sprite sprite: sprites) {
            removeSprite(sprite, layer);
        }
    }
    
    public static void addSprite( Sprite input, int to ) {
        queuedCommands.add(() -> {Utility.addSprite(input, to);});
    }
    
    public static void removeSprite( Sprite input, int from ) {
        queuedCommands.add(() -> {
            GameData.sprites.get(from).remove(input);
        });
    }
    
    public static void addSprite( Sprite input ) {
        if (input.getLayer() == Integer.MAX_VALUE) {
            throw new IllegalStateException("Sprite not bound to Entity nor assinged a layer to reside in");
        }
        else {
            addSprite( input, input.getLayer() );
        }
    }
    
    public static void removeSprite( Sprite input ) {
        if (input.getLayer() == Integer.MAX_VALUE) {
            throw new IllegalStateException("Sprite not bound to Entity nor assinged a layer to reside in");
        }
        else {
            removeSprite( input, input.getLayer());
        }
    }
    protected static void addRect( Rect input, int to ) {
        queuedCommands.add(() -> {Utility.addLayer(input,to);});
    }
    
    protected static void addFocused(Entity input){
        queuedCommands.add(() -> {
            if( input.getFocused() ) {
                GameData.focusedEntities.add(input);
            }
        });
    }
    
    private static void addEnder( Entity input ) {
        queuedCommands.add(() -> {
            if (input instanceof Ender) {
                GameData.enders.add((Ender)input);
            }
        });
    }
    
    protected static void removeFocused( Entity input ){
        queuedCommands.add(() -> {
            if( input.getFocused() ) {
                GameData.focusedEntities.remove(input);
            }
        });
    }
    
    protected static void removeRect( Rect input, int from ){
        queuedCommands.add(() -> {
            try {
                GameData.layers.get(from).remove(input);
            } catch (IndexOutOfBoundsException exception) {}});
    }
    
    private static void removeEnder( Entity input ) {
         queuedCommands.add(() -> {
            if( input instanceof Ender ) {
                GameData.enders.remove((Ender) input);
            }
        });
    }
}