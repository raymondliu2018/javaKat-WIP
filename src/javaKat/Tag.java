package javaKat;  

import java.awt.Font;
import java.awt.Color;
import java.awt.FontMetrics;

public final class Tag extends Attachable
{
    private String message = "";
    private StringCommand message$ = null;
    private Font font;
    private Color color;
    private FontMetrics fontMetrics = null;
    
    public Tag() {
        super();
        constructionHelper();
    }
    
    public Tag(Entity input) {
        super(input);
        input.attachStat(this);
        constructionHelper();
    }
    
    public void constructionHelper() {
        setFont(new Font("Textbox",0,12));
        setColor(new Color(0,0,0));
        Manager.addStat(this);
    }
    
    protected void update() {
        if (message$ != null) {
            message = message$.value();
        }
        movementUpdate();
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
    
    public int getRoughWidth() {
        return fontMetrics.stringWidth(this.getMessage());
    }
    
    public int getRoughHeight() {
        return fontMetrics.getHeight(); 
    }
    
    protected int getWidth() {
        return getRoughWidth();
    }
    
    protected int getHeight() {
        return getRoughHeight();
    }
}
