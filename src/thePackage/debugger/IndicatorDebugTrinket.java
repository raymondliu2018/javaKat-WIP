
package thePackage.debugger;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import thePackage.Entity;
import thePackage.Manager;
import thePackage.Sprite;
import thePackage.Utility;

class IndicatorDebugTrinket extends TrinketBase implements IsDebugger{
    private HashMap<Entity,Sprite> indicators;
    public IndicatorDebugTrinket() {
        super();
        indicators = new HashMap<>();
    }
    
    protected void entityAdded(Entity input) {
        Sprite temp = new Sprite();
        temp.setX(() -> {
            return input.getRect().getCornerX();
        });
        temp.setY(() -> {
            return input.getRect().getCornerY();
        });
        BufferedImage image = Utility.scaleImage("thePackage/debugger/indicator.png", (int) input.getRect().getWidth(), (int) input.getRect().getHeight());
        temp.addImage(image,"main",true);
        indicators.put(input, temp);
        Manager.addLonelySprite(temp, DEBUGGER_LAYER);
    }
    
    protected void entityRemoved(Entity input) {
        Manager.removeLonelySprite(indicators.get(input), DEBUGGER_LAYER);
        indicators.remove(input);
        
    }
    public void subUpdate() {
        
    }
}
