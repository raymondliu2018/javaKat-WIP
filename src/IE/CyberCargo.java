package IE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javaKat.GameData;

class CyberCargo implements Serializable{
    private ArrayList allEntities, focusedEntities, keys, buttons, layers, soundFiles, imageFiles, images, sounds, enders;
    private CopyOnWriteArrayList sprites, stats;
    private static CyberCargo storage;
    
    private CyberCargo() {
        pack(allEntities,GameData.allEntities);
        pack(focusedEntities,GameData.focusedEntities);
        pack(keys,GameData.keys);
        pack(buttons,GameData.buttons);
        pack(layers,GameData.layers);
        pack(soundFiles,GameData.soundFiles);
        pack(imageFiles,GameData.imageFiles);
        pack(images,GameData.images);
        pack(sounds,GameData.sounds);
        pack(enders,GameData.enders);
        pack(sprites,GameData.sprites);
        pack(stats,GameData.stats);
    }
    
    protected static void store(CyberCargo input) {
        storage = input;
    }
    protected static void unpack(CyberCargo input) {
        unpack(GameData.allEntities,input.allEntities);
        unpack(GameData.focusedEntities,input.allEntities);
        unpack(GameData.keys,input.keys);
        unpack(GameData.buttons,input.buttons);
        unpack(GameData.layers,input.layers);
        unpack(GameData.imageFiles,input.imageFiles);
        unpack(GameData.soundFiles,input.soundFiles);
        unpack(GameData.images,input.images);
        unpack(GameData.sounds,input.sounds);
        unpack(GameData.enders,input.enders);
        unpack(GameData.sprites,input.sprites);
        unpack(GameData.stats,input.stats);
    }
    
    protected static CyberCargo pack() {
        return new CyberCargo();
    }
    
    private static void unpack(List gameData, List input) {
        gameData.clear();
        gameData.addAll(input);
    }
    
    private static void pack(List input, List gameData){
        input = gameData;
    }
}
