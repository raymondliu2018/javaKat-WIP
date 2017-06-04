package workspace;

import thePackage.*;
public class Map
{
    private boolean availability[][] = new boolean [16][32]; /*y*x*/
    private int dimensions[] = new int [2];
    private static Map instance = null;
    
    public static Map getInstance() {
        if (instance == null) {
            instance = new Map();
        }
        return instance;
    }
    
    public Map(int [] obstacles)
    {
        for(boolean []a: availability)
        {
            for(boolean b: a)
            {
                b = true;
            }
        }
        int x;
        int y;
        for(int counter = 0; counter<obstacles.length;counter++)
        {
            x = obstacles[counter]%32;
            y = obstacles[counter]/32;
            availability[y][x] = false;
        }
        dimensions[0] = GameMaster.getFrame().getHeight();
        dimensions[1] = GameMaster.getFrame().getWidth();
    }
    public Map()
    {
        for(boolean []a: availability)
        {
            for(boolean b: a)
            {
                b = true;
            }
        }  
        dimensions[0] = GameMaster.getFrame().getHeight();
        dimensions[1] = GameMaster.getFrame().getWidth();
    }
    public boolean occupy(int position)/*check occupy when constructing any building*/
    {
        int x = position%32;
        int y = position/32;
        if(availability[y][x] == false)
        {
            System.out.println("Can't construct the selected building here! Space occupied.");
            return false;
        }
        else
        {
            availability[y][x] = false;
            return true;
        }
    }
    public boolean occupy(int [] position) /*y, x; two element array; first one less than 16, second one less than 32*/
    {
        if(availability[position[0]][position[1]] == false)
        {
            System.out.println("Can't construct the selected building here! Space occupied.");
            return false;
        }
        else
        {
            availability[position[0]][position[1]] = false;
            return true;
        }
    }
    public int [] toPixel(int position) /*converts block indices to pixel indices*/
    {
        int x = position%32;
        int y = position/32;
        int [] pPosition = new int [2];
        pPosition[0] = dimensions[1]/32*x+dimensions[1]/64;
        pPosition[1] = dimensions[0]/16*y+dimensions[0]/32;
        return pPosition;
    }
    public int toBlockIndex(int [] pixel) /*converts pixel indices to block indices*/
    {
        int blockIndex[] = new int [2];
        blockIndex[1] = pixel[0]/(dimensions[1]/32);
        blockIndex[0] = pixel[1]/(dimensions[0]/16);
        return blockIndex[0]*32+blockIndex[1];
    }
    public void free(int position)
    {
        if(availability[position/32][position%32] == false)
        {
            System.out.println("This block has been freed!");
            availability[position/32][position%32] = true;
        }
        else
        {
            System.out.println("Can't free this block!");
        }
    }
    public int arrToInt(int arr[])
    {
        return arr[0]*32+arr[1];
    }
}