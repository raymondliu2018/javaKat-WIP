package javaKat;  

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.awt.image.BufferedImage;
import javax.sound.sampled.Clip;

public interface GameData {
    ArrayList<Entity> allEntities = new ArrayList<>();
    CopyOnWriteArrayList<CopyOnWriteArrayList<Album>> sprites = new CopyOnWriteArrayList<>();
    ArrayList<Entity> focusedEntities = new ArrayList<>();
    ArrayList<Key> keys = new ArrayList<>();
    ArrayList<Button> buttons = new ArrayList<>();
    ArrayList<ArrayList<Rect>> layers = new ArrayList<>();
    ArrayList<String> imageFiles = new ArrayList<>();
    ArrayList<BufferedImage> images = new ArrayList<>();
    ArrayList<String> soundFiles = new ArrayList<>();
    ArrayList<Clip> sounds = new ArrayList<>();
    CopyOnWriteArrayList<Text> stats = new CopyOnWriteArrayList<>();
    ArrayList<Ender> enders = new ArrayList<>();
}