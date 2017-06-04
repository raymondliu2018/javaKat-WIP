package workspace;

import java.awt.Color;
import thePackage.*;
import workspace.buildings.*;
import workspace.minions.SmartMinion;
import workspace.minions.MinionData;
import java.awt.Font;

public class Player extends Entity implements PlayerData, GameData, MinionData{
    private int state;
    private int credits = 10;
    private Sprite topMenu, leftMenu, rightMenu, botMenu;
    private int pointer;
    private String color;
    private Factory temporaryFactory;
    public Player(String color) {
        super();
        this.color = color;
        rect.setLayer(5);
        Text creditsCounter = new Text();
        creditsCounter.setMessage(() -> {return Integer.toString(credits);});
        creditsCounter.setFont(new Font(Font.SANS_SERIF,Font.BOLD,24));
        if (color.equals("red")){
            sprite.addImage("images/Red/Cursor.png","main",true);
            creditsCounter.setColor(Color.RED);
            creditsCounter.setCenterX(() -> {return GameMaster.getWidth()/4.0;});
        }
        if (color.equals("blue")){
            sprite.addImage("images/Blue/Cursor.png","main",true);
            creditsCounter.setColor(Color.BLUE);
            creditsCounter.setCenterX(() -> {return 3*GameMaster.getWidth()/4.0;});
        }
        creditsCounter.setCenterY(() -> {return GameMaster.getHeight() - 12;});
        addStat(creditsCounter);
        sprite.setImage("main");
        bindCodeToAction("left",
            (a) -> {left();},
            (a) -> {stopLeft();});
        bindCodeToAction("right",
            (a) -> {right();},
            (a) -> {stopRight();});
        bindCodeToAction("up",
            (a) -> {up();},
            (a) -> {stopUp();});
        bindCodeToAction("down",
            (a) -> {down();},
            (a) -> {stopDown();});
        bindCodeToAction("primary",
            (a) -> {},
            (a) -> {primary();});
        bindCodeToAction("secondary",
            (a) -> {},
            (a) -> {secondary();});
        resize();
        rect.setMaxSpeed(CURSORSPEED);
    }
    
    public void primary() {
        switch( state ) {
            case FREE:
                if (color.equals("red")){
                    if (rect.getCenterX() >= GameMaster.getWidth()/2){
                        break;
                    }
                }
                if (color.equals("blue")){
                    if (rect.getCenterX() <= GameMaster.getWidth()/2){
                        break;
                    }
                }
                temporaryFactory = cursorOverBuilding();
                if (temporaryFactory != null) {
                    beginUsingBuilding();
                }
                else {
                    if (credits > 0){
                        beginPlacingBuilding();
                    }
                }
                break;
            case PLACING_BUILDING:
                switch(pointer) {
                    case CENTER:
                    case LEFT:
                    case RIGHT:
                    case TOP:
                    case BOT:
                        stopPlacingBuilding();
                        break;
                }
                switch(pointer) {
                    case LEFT:
                        placeBuildingLeft();
                        break;
                    case RIGHT:
                        placeBuildingRight();
                        break;
                    case TOP:
                        placeBuildingTop();
                        break;
                    case BOT:
                        placeBuildingBot();
                        break;
                }
                break;
            case USING_BUILDING:
                switch(pointer) {
                    case LEFT:
                        useBuildingLeft();
                        break;
                    case RIGHT:
                        useBuildingRight();
                        break;
                    case TOP:
                        useBuildingTop();
                        break;
                    case BOT:
                        useBuildingBot();
                        break;
                }
                switch(pointer) {
                        case CENTER:
                        case LEFT:
                        case RIGHT:
                        case TOP:
                        case BOT:
                            stopUsingBuilding();
                            break;
                }
        }
    }
    
    public void secondary() {
        switch( state ) {
            case FREE:
                moveMinions();
                break;
            case PLACING_BUILDING:
                stopPlacingBuilding();
                break;
            case USING_BUILDING:
                stopUsingBuilding();
                break;
        }
    }
    
    public Factory cursorOverBuilding() {
        for (Entity input : GameData.allEntities) {
            if (input instanceof Factory) {
                if (within(rect.getCenterX(), rect.getCenterY(),
                    input.getRect().getCornerX(), input.getRect().getCornerY(),
                    input.getRect().getCornerX() + input.getRect().getWidth(),
                    input.getRect().getCornerY() + input.getRect().getHeight())) {System.out.println("within");
                    return (Factory) input;
                    
                }
            }
        }
        return null;
    }
    
    public void beginUsingBuilding() {
        pointer = CENTER;
        state = USING_BUILDING;
        createMenu("Placing Building");
        //Do these images
    }
    
    public void beginPlacingBuilding() {
        pointer = CENTER;
        state = PLACING_BUILDING;
        createMenu("Placing Building");
    }
    
    public void createMenu(String folder) {
        rect.stop();
        topMenu = new Sprite();
        topMenu.addImage("images/Options/" + folder + "/Top.png","main",true);
        topMenu.setX(rect.getCornerX());
        topMenu.setY(rect.getCornerY() - rect.getHeight());
        Manager.addLonelySprite(topMenu,4);
        
        botMenu = new Sprite();
        botMenu.addImage("images/Options/" + folder + "/Bot.png","main",true);
        botMenu.setX(rect.getCornerX());
        botMenu.setY(rect.getCornerY() + rect.getHeight());
        Manager.addLonelySprite(botMenu,4);
        
        leftMenu = new Sprite();
        leftMenu.addImage("images/Options/" + folder + "/Left.png","main",true);
        leftMenu.setX(rect.getCornerX() - rect.getWidth());
        leftMenu.setY(rect.getCornerY());
        Manager.addLonelySprite(leftMenu,4);
        
        rightMenu = new Sprite();
        rightMenu.addImage("images/Options/" + folder + "/Right.png","main",true);
        rightMenu.setX(rect.getCornerX() + rect.getWidth());
        rightMenu.setY(rect.getCornerY());
        Manager.addLonelySprite(rightMenu,4);
    }
    
    public void stopPlacingBuilding() {
        state = FREE;
        deleteMenu();
    }
    
    public void stopUsingBuilding() {
        state = FREE;
        deleteMenu();
        temporaryFactory = null;
    }
    
    public void deleteMenu() {
        Manager.removeLonelySprite(topMenu,4);
        Manager.removeLonelySprite(botMenu,4);
        Manager.removeLonelySprite(leftMenu,4);
        Manager.removeLonelySprite(rightMenu,4);
        spriteNormal();
    }
    
    public void placeBuildingTop() {
        int [] arr = {(int) rect.getCenterX(), (int) rect.getCenterY()};
        int position = Map.getInstance().toBlockIndex(arr);
        Factory building = new SmartFactory(this.color,position);
        Manager.queueNewEntity(building);
        credits -= 1;
    }
    
    public void placeBuildingRight() {
        int [] arr = {(int) rect.getCenterX(), (int) rect.getCenterY()};
        int position = Map.getInstance().toBlockIndex(arr);
        Factory building = new DefenseFactory(this.color,position);
        Manager.queueNewEntity(building);
        credits -= 1;
    }
    
    public void placeBuildingLeft() {
        int [] arr = {(int) rect.getCenterX(), (int) rect.getCenterY()};
        int position = Map.getInstance().toBlockIndex(arr);
        Factory building = new AttackFactory(this.color,position);
        Manager.queueNewEntity(building);
        credits -= 1;
    }
    
    public void placeBuildingBot() {
    }
    
    public void useBuildingTop() {
        temporaryFactory.setTier(1);
    }
    
    public void useBuildingLeft() {
        Manager.removeEntity(temporaryFactory);
        credits += 1;
    }
    
    public void useBuildingRight() {
        temporaryFactory.setTier(2);
    }
    
    public void useBuildingBot() {
        temporaryFactory.setTier(3);
    }
    
    public void left() {
        switch( state ) {
            case FREE:
                rect.accel(-CURSORSPEED,0.0);
                break;
            case PLACING_BUILDING:
                break;
            case USING_BUILDING:
                break;
        }
    }
    
    public void right() {
        switch( state ) {
            case FREE:
                rect.accel(CURSORSPEED,0.0);
                break;
            case PLACING_BUILDING:
                break;
            case USING_BUILDING:
                break;
        }
    }
    
    public void down() {
        switch( state ) {
            case FREE:
                rect.accel(0.0,CURSORSPEED);
                break;
            case PLACING_BUILDING:
                break;
            case USING_BUILDING:
                break;
        }
    }
    
    public void up() {
        switch( state ) {
            case FREE:
                rect.accel(0.0,-CURSORSPEED);
                break;
            case PLACING_BUILDING:
                break;
            case USING_BUILDING:
                break;
        }
    }
    
    public void stopLeft() {
        switch( state ) {
            case FREE:
                rect.accel(CURSORSPEED,0.0);
                break;
            case PLACING_BUILDING:
            case USING_BUILDING:
                switch(pointer) {
                    case CENTER:
                    case RIGHT:
                    sprite.setX(-rect.getWidth() + sprite.getX());
                    break;
                }
                switch(pointer) {
                    case CENTER:
                        pointer = LEFT;
                        break;
                    case RIGHT:
                        pointer = CENTER;
                        break;
                }
                break;
        }
    }
    
    public void stopRight() {
        switch( state ) {
            case FREE:
                rect.accel(-CURSORSPEED,0.0);
                break;
            case USING_BUILDING:
            case PLACING_BUILDING:
                switch(pointer) {
                    case CENTER:
                    case LEFT:
                    sprite.setX(rect.getWidth() + sprite.getX());
                    break;
                }
                switch(pointer) {
                    case CENTER:
                        pointer = RIGHT;
                        break;
                    case LEFT:
                        pointer = CENTER;
                        break;
                }
                break;
        }
    }
    
    public void stopUp() {
        switch( state ) {
            case FREE:
                rect.accel(0.0,CURSORSPEED);
                break;
            case USING_BUILDING:
            case PLACING_BUILDING:
                switch(pointer) {
                    case CENTER:
                    case BOT:
                    sprite.setY(-rect.getHeight() + sprite.getY());
                    break;
                }
                switch(pointer) {
                    case CENTER:
                        pointer = TOP;
                        break;
                    case BOT:
                        pointer = CENTER;
                        break;
                }
                break;
        }
    }
    
    public void stopDown() {
        switch( state ) {
            case FREE:
                rect.accel(0.0,-CURSORSPEED);
                break;
            case USING_BUILDING:
            case PLACING_BUILDING:
                switch(pointer) {
                    case CENTER:
                    case TOP:
                    sprite.setY(rect.getHeight() + sprite.getY());
                    break;
                }
                switch(pointer) {
                    case CENTER:
                        pointer = BOT;
                        break;
                    case TOP:
                        pointer = CENTER;
                        break;
                }
                break;
        }
    }
    
    public void subUpdate(){};
    
    public boolean collidedWith(Entity input) {
        return false;
    }
    
    public void spriteNormal() {
        sprite.setX(() -> {return rect.getCornerX();});
        sprite.setY(() -> {return rect.getCornerY();});
    }
    
    public void moveMinions() {
        double currentX = rect.getCenterX();
        double currentY = rect.getCenterY();
        for (Entity input: GameData.allEntities){
            if (input instanceof SmartMinion){
                SmartMinion input$ = (SmartMinion) input;
                if (input$.getColor().equals(this.color)){
                        if ((int)(Math.abs(input$.getDestinationX() - currentX)) >= SMART_MINION_TOLERANCE ||
                                (int)Math.abs(input$.getDestinationY() - currentY) >= SMART_MINION_TOLERANCE ) {
                            input$.orderTo(currentX, currentY);
                    }
                }
            }
        }
    }
    
    public double getDistance(double x1, double y1, double x2, double y2){
        return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
    }
}