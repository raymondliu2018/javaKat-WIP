
package thePackage.debugger;

import java.awt.Color;
import java.awt.Font;
import thePackage.Camera;
import thePackage.Entity;
import thePackage.GameMaster;
import thePackage.Rect;
import thePackage.Text;

class SubRectDebugTrinket extends TrinketBase implements SubRectDebugTrinketSettings, IsDebugger{
    private Rect rectInfo;
    private Text info;
    protected SubRectDebugTrinket(Rect input) {
        rectInfo = input;
        info = new Text();
        info.setColor(STANDARD_COLOR);
        info.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,FONT_SIZE));
        info.setCenterX(rect.getCenterX());
        info.setCenterY(rect.getCenterY());
        info.setMessage(() -> {return stats();});
        addStat(info);
        sprite.addImage("thePackages/debugger/SubRectDebugTrinket.png", "main", true);
        resize();
    }
    
    public void subUpdate() {
        if (rectInfo.getCenterX() < 0 || rectInfo.getCenterX() > Camera.getMapWidth()){
            info.setColor(WARNING_COLOR);
            
        }
        else if (rectInfo.getCenterY() < 0 || rectInfo.getCenterY() > Camera.getMapHeight()){
            info.setColor(WARNING_COLOR);
        }
        else if (rectInfo.getWidth() <= 0 || rectInfo.getHeight() <= 0){
            info.setColor(WARNING_COLOR);
        }
        else {
            info.setColor(STANDARD_COLOR);
        }
    }
    
    private String stats() {
        String stack = "";
        stack += Double.toString(rectInfo.getCenterX());
        stack += "\t";
        stack += Double.toString(rectInfo.getCenterY());
        stack += "\t";
        stack += Double.toString(rectInfo.getAngle());
        stack += "\t";
        stack += Double.toString(rectInfo.getWidth());
        stack += "\t";
        stack += Double.toString(rectInfo.getHeight());
        
        return stack;
    }
}
