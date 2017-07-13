package javaKat.debugger; ;

import javaKat.PositionMode;
import javaKat.Tag;
import javaKat.Album;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import javaKat.Key;

class KeyDebugTrinket extends TrinketBase implements KeyDebugTrinketSettings, DebuggerTag{
    private HashMap<Integer,Tag> individualKeyMap;
    private ArrayList<Tag> individualKeyList;
    private Album album;
    protected KeyDebugTrinket(ArrayList<Key> keys, double xPosition, double yPosition) {
        super(xPosition, yPosition);
        album = new Album(this);
        album.addPageWithPicture(IMAGE, "main");
        album.setPage("main");
        album.setPositionMode(PositionMode.BY_RECT);
        this.resizeByCorner(album.getCurrentPageWidth(),album.getCurrentPageHeight());
        
        individualKeyMap = new HashMap<>();
        individualKeyList = new ArrayList<>();
        
        for (Key key: keys){
            bindCodeToAction(key.toString(),(a) -> {
                individualKeyMap.get(a.getInput()).setColor(WARNING_COLOR);
            }, (a) -> {
                individualKeyMap.get(a.getInput()).setColor(STANDARD_COLOR);
            });
            bindKeyToAction(key.getInput(), key.toString());
            
            Tag temp = new Tag(this);
            formatText(temp);
            temp.setMessage("'" + Character.toString((char)(key.getInput())) + "'");
            individualKeyMap.put(key.getInput(),temp);
        }
    }
    
    public void subUpdate() {
        
    }
    
    private void formatText(Tag input) {
        input.setPositionMode(PositionMode.BY_INPUT);
        individualKeyList.add(input);
        input.setColor(STANDARD_COLOR);
        input.setFont(new Font(DEBUGGER_FONT,Font.PLAIN,FONT_SIZE));
        input.setCornerX(() -> {return rect.getCornerX() + individualKeyList.indexOf(input) * KEYS_OFFSET_X;});
        input.setCenterY(() -> {return rect.getCenterY();});
    }
}
