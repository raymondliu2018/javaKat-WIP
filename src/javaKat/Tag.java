package javaKat;  

import java.awt.Font;
import java.awt.Color;
import java.awt.FontMetrics;

public final class Tag
{
    private String message = "";
    private double x = 0.0;
    private double y = 0.0;
    private StringCommand message$ = null;
    private DoubleCommand x$ = null;
    private DoubleCommand y$ = null;
    private Font font;
    private Color color;
    private FontMetrics fontMetrics = null;
    
    public Tag() {
        setFont(new Font("Textbox",0,12));
        setColor(new Color(0,0,0));
        Manager.addStat(this);
    }
    
    public Tag(Entity input) {
        this();
        input.attachStat(this);
    }
    
    protected void update() {
        if (message$ != null) {
            message = message$.value();
        }
        if (x$ != null) {
            x = x$.value();
        }
        if (y$ != null) {
            y = y$.value();
        }
    }
    
    /**
     * @param input set the String displayed by this Tag
     */
    public void setMessage(String input) {message = input;}
    
    /**
     * @param input bind code to the String dislayed by this Tag to automatically rewrite its message
     */
    public void setMessage(StringCommand input) {message$ = input;}
    
    /**
     * @return get the current String this Tag is displaying
     */
    public String getMessage() {return message;}
    
    public void setCenterX(double input) {
        x = input;
        x$ = null;
    }

    public void setCenterY(double input) {
        y = input;
        y$ = null;
    }
    
    /**
     * @param input bind code to the x position of this Tag to automatically recalculate position
     */
    public void setCenterX(DoubleCommand input) {x$ = input;}    
    
    /**
     * @param input bind code to the y position of this Tag to automatically recalculate position
     */
    public void setCenterY(DoubleCommand input) {y$ = input;}
    
    public void setCornerX(double input) {
        x = input + getRoughWidth()/2;
        x$ = null;
    }
    
    public void setCornerY(double input) {
        y = input + getRoughHeight()/2;
        y$ = null;
    }
    
    public void setCornerX(DoubleCommand input) {
        x$ = () -> {
            return input.value() + getRoughWidth()/2;
        };
    }
    
    public void setCornerY(DoubleCommand input) {
        y$ = () -> {
            return input.value() + getRoughHeight()/2;
        };
    }
    
    /**
     * @return get the x position of this Tag
     */
    public double getCenterX() {return x;}
    
    /**
     * @return get the y position of this Tag
     */
    public double getCenterY() {return y;}

    /**
     * @param input set the Font for this Tag
     */
    public void setFont( Font input ) {
        font = input;
        fontMetrics = GameMaster.getFrame().getFontMetrics(font);
    }
    
    /**
     * @param input set the Color for this Tag
     */
    public void setColor( Color input ) {color = input;}
    
    /**
     * @return get the Font for this Tag
     */
    public Font getFont() {return font;}
    
    /**
     * @return get the Color for this Tag
     */
    public Color getColor() {return color;}
    
    /**
     * @return a Tag element that has the same color and font
     */
    public Tag duplicateStyleOnly() {
        Tag tag = new Tag();
        tag.setColor(this.color);
        tag.setFont(this.font);
        return tag;
    }
    
    /**
     * @return a Tag element that has the exact same properties
     */
    public Tag duplicate() {
        Tag tag = new Tag();
        tag.setColor(this.color);
        tag.setFont(this.font);
        tag.setMessage(this.message);
        tag.setMessage(this.message$);
        tag.setCenterX(this.x$);
        tag.setCenterY(this.y$);
        tag.setCenterX(this.x);
        tag.setCenterY(this.y);
        return tag;
    }
    
    public int getRoughWidth() {
        return fontMetrics.stringWidth(this.getMessage());
    }
    
    public int getRoughHeight() {
        return fontMetrics.getHeight();
        
    }
}
