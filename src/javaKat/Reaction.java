package javaKat;

public enum Reaction {
    AGREE(1,"agree"), GHOST (2,"ghost"), REFLECT(3,"reflect"), DEFLECT(4,"deflect"), BOUNCE(5,"bounce");
    
    private final int code;
    private final String description;
    
    Reaction(int i1, String s1) {
        code = i1;
        description = s1;
    }
    
    protected int getCode() {
        return code;
    }
    
    protected String getDescription() {
        return description;
    }
}
