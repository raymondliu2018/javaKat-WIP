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
         GameData.albums.clear();
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
    
    public static void addStat( Tag input ) {
        queuedCommands.add(() -> {GameData.stats.add(input);});
    }
    
    public static void removeStat( Tag input ) {
        queuedCommands.add(() -> {GameData.stats.remove(input);});
    }
    
    public static boolean findThisEntity( Entity input ) {
        return GameData.allEntities.indexOf(input) != -1;
    }
    
    private static void addEntity( Entity input ) {
        queuedCommands.add(() -> {if (!input.superCalled()){throw new RuntimeException("Super not called on " + input);}});
        queuedCommands.add(() -> {GameData.keys.addAll(input.getKeys());});
        queuedCommands.add(() -> {GameData.buttons.addAll(input.getButtons());});
        addAlbums(input.getAlbums(),input.getLayer());
        addRect(input.getRect(),input.getLayer());
        addEnder(input);
        addFocused(input);
        queuedCommands.add(() -> {GameData.allEntities.add(input);});
    }
    
    protected static void addAlbums(ArrayList <Album> albums, int layer) {
        for (Album album: albums) {
            Manager.addAlbum(album, layer);
        }
    }
    
    public static void removeEntity( Entity input ){
        queuedCommands.add(() -> {GameData.keys.removeAll(input.getKeys());});
        queuedCommands.add(() -> {GameData.buttons.removeAll(input.getButtons());});
        queuedCommands.add(() -> {GameData.stats.removeAll(input.getStats());});
        removeAlbums(input.getAlbums(), input.getLayer());
        removeRect(input.getRect(),input.getLayer());
        removeEnder(input);
        removeFocused(input);
        queuedCommands.add(() -> {GameData.allEntities.remove(input);});
    }
    
    protected static void removeAlbums(ArrayList <Album> albums, int layer) {
        for (Album album: albums) {
            Manager.removeAlbum(album, layer);
        }
    }
    
    public static void addAlbum( Album input, int to ) {
        queuedCommands.add(() -> {Utility.addAlbum(input, to);});
    }
    
    public static void removeAlbum( Album input, int from ) {
        queuedCommands.add(() -> {
            GameData.albums.get(from).remove(input);
        });
    }
    
    public static void addAlbum( Album input ) {
        if (input.getLayer() == Integer.MAX_VALUE) {
            throw new IllegalStateException("Album not bound to Entity nor assinged a layer to reside in");
        }
        else {
            Manager.addAlbum( input, input.getLayer() );
        }
    }
    
    public static void removeAlbum( Album input ) {
        if (input.getLayer() == Integer.MAX_VALUE) {
            throw new IllegalStateException("Album not bound to Entity nor assinged a layer to reside in");
        }
        else {
            Manager.removeAlbum( input, input.getLayer());
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