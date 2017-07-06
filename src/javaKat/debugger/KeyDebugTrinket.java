package javaKat.debugger; ;

import javaKat.Album;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import javaKat.Key;
import javaKat.Text;

class KeyDebugTrinket extends TrinketBase implements KeyDebugTrinketSettings, DebuggerTag{
    private HashMap<Integer,Text> individualKeyMap;
    private ArrayList<Text> individualKeyList;
    private Album album;
    protected KeyDebugTrinket(ArrayList<Key> keys, double xPosition, double yPosition) {
        super(xPosition, yPosition);
        album.addPageWithPicture(IMAGE, "main");
        album.setPage("main");
        this.resizeByCenter(album.getCurrentPageWidth(),album.getCurrentPageHeight());
        
        individualKeyMap = new HashMap<>();
        individualKeyList = new ArrayList<>();
        
        for (Key key: keys){
            bindCodeToAction(key.toString(),(a) -> {
                individualKeyMap.get(a.getInput()).setColor(WARNING_COLOR);
            }, (a) -> {
                individualKeyMap.get(a.getInput()).setColor(STANDARD_COLOR);
            });
            bindKeyToAction(key.getInput(), key.toString());
            
            Text temp = new Text(this);
            formatText(temp);
            temp.setMessage("'" + Character.toString((char)(key.getInput())) + "'");
            individualKeyMap.put(key.getInput(),temp);
        }
    }
    
    public void subUpdate() {
        
    }
    
    private void formatText(Text input) {
        individualKeyList.add(input);
        input.setColor(STANDARD_COLOR);
        input.setFont(new Font(DEBUGGER_FONT,Font.PLAIN,FONT_SIZE));
        input.setCornerX(() -> {return rect.getCornerX() + individualKeyList.indexOf(input) * KEYS_OFFSET_X;});
        input.setCenterY(() -> {return rect.getCenterY();});
    }
}
