package javaKat;

abstract class Attachable {
    private Entity owner;
    private MovementMode movementMode = MovementMode.NONE;
    private DoubleCommand x$ = null;
    private DoubleCommand y$ = null;
    private int x;
    private int y;

    protected Attachable() {
        owner = null;
    }
    
    protected Attachable(Entity input) {
        owner = input;
    }
    
    protected Entity getOwner() {return owner;}
    
    protected void movementUpdate() {
        switch (movementMode) {
            case BY_RECT:
                x = (int) owner.getRect().getCornerX();
                y = (int) owner.getRect().getCornerY();
                break;
            case BY_INPUT:
                if (x$ != null) {
                    x = (int) x$.value();
                }
                if (y$ != null) {
                    y = (int) y$.value();
                }
                break;
            case NONE:
                break;
        }
    }
    
    public void setCornerPosition( int inputx, int inputy ){
        setCornerX(inputx);
        setCornerY(inputy);
    }
    
    public void setCornerX(int input) {
        checkMovementMode();
        x = input;
        x$ = null;
    }
    
    public void setCornerY(int input) {
        checkMovementMode();
        y = input;
        y$ = null;
    }
    
    public void setCornerX(DoubleCommand input) {
        checkMovementMode();
        x$ = input;
    }
    
    public void setCornerY(DoubleCommand input) {
        checkMovementMode();
        y$ = input;
    }
    
    public void setCenterPosition(int inputx, int inputy){
        setCenterX(inputx);
        setCenterX(inputy);
    }
    
    public void setCenterX(DoubleCommand input) {
        checkMovementMode();
        x$ = () -> {
            return input.value() - getWidth()/2;
        };
    }
    
    public void setCenterY(DoubleCommand input) {
        checkMovementMode();
        x$ = () -> {
            return input.value() - getHeight()/2;
        };
    }
    
    public void setCenterX(int input) {
        setCornerX(input - getWidth()/2);
    }
    
    public void setCenterY(int input) {
        setCornerY(input - getHeight()/2);
    }
    
    public int getCornerX() {return x;}
    
    public int getCornerY() {return y;}
    
    protected abstract int getWidth();
    
    protected abstract int getHeight();
    
    private void checkMovementMode() {
        if (movementMode != MovementMode.BY_INPUT) {
            throw new IllegalArgumentException("Movement mode not by input; cannot set coordinates");
        }
    }
    
    public void setMovementMode(MovementMode input) {
        switch(input) {
            case BY_RECT:
                if (owner == null) {
                    throw new IllegalArgumentException("This album is not attached to an Entity to calculate position from");
                }
                break;
        }
        movementMode = input;
    }
}
