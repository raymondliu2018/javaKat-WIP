
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
    protected SubRectDebugTrinket(Rect input, double xPosition, double yPosition) {
        super(xPosition,yPosition);
        sprite.addImage(IMAGE, "main", true);
        resizeByCorner();
        rectInfo = input;
        info = new Text();
        info.setColor(STANDARD_COLOR);
        info.setFont(new Font(DEBUGGER_FONT,Font.PLAIN,FONT_SIZE));
        info.setCornerX(() -> {return rect.getCornerX();});
        info.setCenterY(() -> {return rect.getCenterY();});
        info.setMessage(() -> {return stats();});
        addStat(info);

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
        stack += roundDouble(rectInfo.getCenterX());
        stack += "\t";
        stack += roundDouble(rectInfo.getCenterY());
        stack += "\t";
        stack += roundDouble(rectInfo.getAngle());
        stack += "\t";
        stack += roundDouble(rectInfo.getWidth());
        stack += "\t";
        stack += roundDouble(rectInfo.getHeight());
        
        return stack;
    }
    
    private String roundDouble(double input) {
        return Double.toString((int)(1000.0 * input)/1000.0);
    }
}
