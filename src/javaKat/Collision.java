package javaKat;  

import java.util.Arrays;
import workspace.Script;

final class Collision extends Manipulator implements GameData
{
    protected static void run() {
        if (enabled) {
            checkCollision();
        }
    }
    
    protected static void checkCollision(){
        int x,y,z;
        int max_1 = GameData.layers.size();
        for(x=0;x<max_1;x++)
        {
            int max_2 = GameData.layers.get(x).size();
            for (y=0;y<max_2;y++)
            {
                Rect object2 = GameData.layers.get(x).get(y);
                double a = object2.getCenterX();
                double b = object2.getCenterY();
                double w_1 = object2.getWidth();
                double l_1 = object2.getHeight();
                double bound_l2 = a-w_1/2.0;
                double bound_r2 = a+w_1/2.0;
                double bound_u2 = b-l_1/2.0;
                double bound_b2 = b+l_1/2.0;
                for(z=y+1;z<max_2;z++)
                {
                    Rect object1 = GameData.layers.get(x).get(z);
                    double c = object1.getCenterX();
                    double d = object1.getCenterY();
                    double w_2 = object1.getWidth();
                    double l_2 = object1.getHeight(); 
                    double bound_l1 = c-w_2/2.0;
                    double bound_r1 = c+w_2/2.0;
                    double bound_u1 = d-l_2/2.0;
                    double bound_b1 = d+l_2/2.0;
                    if(((bound_l1-w_1<=bound_l2  && bound_l2<= bound_r1) &&
                        (bound_u1<=bound_b2 &&  bound_b2<=bound_b1+l_1))){
                        Reaction reaction_1 = object1.getOwner().collidedWith(object2.getOwner());
                        Reaction reaction_2 = object2.getOwner().collidedWith(object1.getOwner());
                        switch((reaction_1.getCode() * 10) + reaction_2.getCode()){
                            case 12:
                            case 21:
                            case 22:
                                //Ghost Collision; Nothing happens
                                break;
                            case 34:
                                //Object 2 Deflects, Object 1 Reflects (Object 1 blocks Object 2)
                                reflect$deflect(object1,object2);
                                break;
                            case 43:
                                //Opposite of above
                                reflect$deflect(object2,object1);
                                break;
                            case 15:
                            case 51:
                            case 55:
                                //Bounce
                                bounce(object1,object2);
                                break;
                            default:
                                String error = new String();
                                String entity1 = object1.getOwner().toString();
                                String entity2 = object2.getOwner().toString();
                                error += entity1 + " and " + entity2 + " collided. ";
                                error += entity1 + " requested to ";
                                error += reaction_1.getDescription();
                                error += ", and " + entity2 + " requested to ";
                                error += reaction_2.getDescription();
                                error += ". This is invalid.";
                                throw new IllegalArgumentException(error);
                        }
                    }
                }
            }
        }
    }
    
    private static void reflect$deflect(Rect reflector, Rect deflector) {
        int intersectionWidth = Utility.intersectionWidth(reflector, deflector);
        int intersectionHeight = Utility.intersectionHeight(reflector, deflector);
        int intersectionWidthAbs = Math.abs(intersectionWidth);
        int intersectionHeightAbs = Math.abs(intersectionHeight);
        if (intersectionWidthAbs <= intersectionHeightAbs) {
            //X
            double deflectorCenterX = deflector.getCenterX();
            double reflectorCenterX = reflector.getCenterX();
            double deflectorVelocityX = deflector.getXVelocity();
            if (reflectorCenterX < deflectorCenterX && deflectorVelocityX < 0.0) {
                deflector.setXVelocity(-deflectorVelocityX);
            }
            if (reflectorCenterX > deflectorCenterX && deflectorVelocityX > 0.0) {
                deflector.setXVelocity(-deflectorVelocityX);
            }
        }
        if (intersectionWidthAbs >= intersectionHeightAbs) {
            //Y
            double deflectorCenterY = deflector.getCenterY();
            double reflectorCenterY = reflector.getCenterY();
            double deflectorVelocityY = deflector.getYVelocity();
            if (reflectorCenterY < deflectorCenterY && deflectorVelocityY < 0.0) {
                deflector.setYVelocity(-deflectorVelocityY);
            }
            if (reflectorCenterY > deflectorCenterY && deflectorVelocityY > 0.0) {
                deflector.setYVelocity(-deflectorVelocityY);
            }
        }
        deflector.updateWithoutFriction();
    }
    
    private static class Utility{
        private static int intersectionHeight(Rect r1, Rect r2) {
            int y1 = (int) r1.getCornerY();
            int y2 = (int) r2.getCornerY();
            return distanceBetweenMiddlePoints(y1,y1 + (int) r1.getHeight(),y2,y2 + (int)r2.getHeight());
        }
        
        private static int intersectionWidth(Rect r1, Rect r2) {
            int x1 = (int) r1.getCornerX();
            int x2 = (int) r2.getCornerX();
            return distanceBetweenMiddlePoints(x1,x1 + (int) r1.getWidth(),x2,x2 + (int)r2.getWidth());
        }
        
        private static int distanceBetweenMiddlePoints(int i1, int i2, int i3, int i4) {
            int [] array = {i1, i2, i3, i4};
            sort( array );
            return array[2] - array[1];
        }
        
        private static void sort( int [] input ) {
            int length = input.length;
            int a, b, temp;
            for (a = 1; a < length; a++) {
                b = a;
                temp = input[a];
                while (b > 0 && temp < input[b]){
                    input[b] = input[b - 1];
                    b = b - 1;
                }
                input[b] = temp;
            }
        }
    }
    
    private static void bounce(Rect r1, Rect r2) {
        double r1x = r1.getXVelocity();
        double r1y = r1.getYVelocity();
        double r2x = r2.getXVelocity();
        double r2y = r2.getYVelocity();
        r1.setXVelocity(r2x);
        r1.setYVelocity(r2y);
        r2.setXVelocity(r1x);
        r2.setYVelocity(r1y);
        r1.updateWithoutFriction();
        r2.updateWithoutFriction();
    }
//No longer in use
    /*protected static void collide(Rect a, Rect b){
        double Vax = a.getXVelocity();
        double Vay = a.getYVelocity();
        double Vbx = b.getXVelocity();
        double Vby = b.getYVelocity();
        double m_a = a.getMass();
        double m_b = b.getMass();
        b.setXVelocity((-Vbx*(m_a-m_b)+2*m_a*Vax)/(m_a+m_b));
        b.setYVelocity((-Vby*(m_a-m_b)+2*m_a*Vay)/(m_a+m_b));
        a.setXVelocity((Vax*(m_a-m_b)+2*m_b*Vbx)/(m_a+m_b));
        a.setYVelocity((Vay*(m_a-m_b)+2*m_b*Vby)/(m_a+m_b));
    }*/
}