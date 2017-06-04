package workspace;

public interface PlayerData
{
    public static double CURSORSPEED = 10.0;
    public static int FREE = 0;
    public static int PLACING_BUILDING = 1;
    public static int USING_BUILDING = 2;
    public static int CENTER = 0;
    public static int LEFT = 1;
    public static int RIGHT = 2;
    public static int TOP = 3;
    public static int BOT = 4;
    public static int BLOCK_SIZE = 60;
    public static int SCREEN_WIDTH = 1920;
    public static int SCREEN_HEIGHT = 960;
    
    public default boolean within(double aX, double aY, double bX, double bY, double cX, double cY)
    {
        if(bX>cX)
        {
            if(bY>cY)
            {
                if(aX>cX&&aX<bX&&aY>cY&&aY<bY)
                {
                    return true;
                }
                else{return false;}
            }
            else{
                if(aX>cX&&aX<bX&&aY<cY&&aY>bY)
                {
                    return true;
                }
                else{return false;}
            }
        }
        else{
            if(bY>cY)
            {
                if(aX<cX&&aX>bX&&aY>cY&&aY<bY)
                {
                    return true;
                }
                else{return false;}
            }
            else{
                if(aX<cX&&aX>bX&&aY<cY&&aY>bY)
                {
                    return true;
                }
                else{return false;}
            }    
        }
    }
}
