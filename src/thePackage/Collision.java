package thePackage;

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
                       //Noninertial system with respect to object a
                       if ( object1.getOwner().collidedWith(object2.getOwner()) &&
                            object2.getOwner().collidedWith(object1.getOwner())) {
                           Script.collide(object2.getOwner(),object1.getOwner());
                           collide(object2,object1);
                           object2.updateWithoutFriction();
                           object1.updateWithoutFriction();
                       }
                   }
                }
            }
        }
    }
    
    protected static void collide(Rect a, Rect b){
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
    }
}