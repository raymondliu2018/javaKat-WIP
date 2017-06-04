package thePackage;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.awt.image.BufferedImage;

public interface GameData {
    ArrayList<Entity> allEntities = new ArrayList<>();
    CopyOnWriteArrayList<CopyOnWriteArrayList<Sprite>> sprites = new CopyOnWriteArrayList<>();
    ArrayList<Entity> focusedEntities = new ArrayList<>();
    ArrayList<Key> keys = new ArrayList<>();
    ArrayList<Button> buttons = new ArrayList<>();
    ArrayList<ArrayList<Rect>> layers = new ArrayList<>();
    ArrayList<String> files = new ArrayList<>();
    ArrayList<BufferedImage> images = new ArrayList<>();
    CopyOnWriteArrayList<Text> stats = new CopyOnWriteArrayList<>();
    ArrayList<Ender> enders = new ArrayList<>();
}