package netPackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import thePackage.GameData;

class CyberCargo implements Serializable{
    private ArrayList allEntities, focusedEntities, keys, buttons, layers, files, images, enders;
    private CopyOnWriteArrayList sprites, stats;
    private static CyberCargo storage;
    
    private CyberCargo() {
        pack(allEntities,GameData.allEntities);
        pack(focusedEntities,GameData.focusedEntities);
        pack(keys,GameData.keys);
        pack(buttons,GameData.buttons);
        pack(layers,GameData.layers);
        pack(files,GameData.files);
        pack(images,GameData.images);
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
        unpack(GameData.files,input.files);
        unpack(GameData.images,input.images);
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
