
package javaKat.debugger; ;

import java.awt.Font;
import java.util.ArrayList;
import javaKat.Camera;
import javaKat.Rect;
import javaKat.Sprite;
import javaKat.Text;

class SubRectDebugTrinket extends TrinketBase implements SubRectDebugTrinketSettings, DebuggerTag{
    private Rect rectInfo;
    private ArrayList<Text> info;
    protected SubRectDebugTrinket(Rect input, double xPosition, double yPosition) {
        super(xPosition,yPosition);
        Sprite sprite = new Sprite(this);
        sprite.addImage(IMAGE, "main");
        sprite.setImage("main");
        rectInfo = input;
        info = new ArrayList<>();
        
        Text xCoordinate = new Text(this);
        formatText(xCoordinate);
        xCoordinate.setMessage(() -> {return roundDouble(rectInfo.getCenterX());});
        
        Text yCoordinate = new Text(this);
        formatText(yCoordinate);
        yCoordinate.setMessage(() -> {return roundDouble(rectInfo.getCenterY());});
        
        Text angle = new Text(this);
        formatText(angle);
        angle.setMessage(() -> {
            double temp = rectInfo.getVelocityAngle();
            if (temp == Double.NaN) {
                return "???";
            }
            else {
                return roundDouble(180 * rectInfo.getVelocityAngle() / Math.PI);
            }
        });
        
        Text width = new Text(this);
        formatText(width);
        width.setMessage(() -> {return roundDouble(rectInfo.getWidth());});
        
        Text height = new Text(this);
        formatText(height);
        height.setMessage(() -> {return roundDouble(rectInfo.getHeight());});
    }
    
    public void subUpdate() {
        if (rectInfo.getCenterX() < 0 || rectInfo.getCenterX() > Camera.getMapWidth()){
            info.get(0).setColor(WARNING_COLOR);
        }
        else {
            info.get(0).setColor(STANDARD_COLOR);
        }
        if (rectInfo.getCenterY() < 0 || rectInfo.getCenterY() > Camera.getMapHeight()){
            info.get(1).setColor(WARNING_COLOR);
        }
        else {
            info.get(1).setColor(STANDARD_COLOR);
        }
        if (rectInfo.getWidth() <= 0){
            info.get(3).setColor(WARNING_COLOR);
        }
        else {
            info.get(3).setColor(STANDARD_COLOR);
        }
        if (rectInfo.getHeight() <= 0){
            info.get(4).setColor(WARNING_COLOR);
        }
        else {
            info.get(4).setColor(STANDARD_COLOR);
        }
    }
    
    private String roundDouble(double input) {
        return Double.toString(((int)(input * 10))/10.0);
    }
    
    private void formatText(Text input) {
        info.add(input);
        input.setColor(STANDARD_COLOR);
        input.setFont(new Font(DEBUGGER_FONT,Font.PLAIN,FONT_SIZE));
        input.setCornerX(() -> {return rect.getCornerX() + info.indexOf(input) * INFO_OFFSET_X;});
        input.setCenterY(() -> {return rect.getCenterY();});
    }
}
