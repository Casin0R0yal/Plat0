package cardpackage;

public enum Form{
    CLUB("♣"), //Black // index 0 in form constructor
    DIAMOND("♢"), //Red // index 1 in form constructor
    HEART("♡"), // index 2 in form constructor
    SPADE("♠️"); // index 3 in form constructor


    private String symbol;
    private Form(String symbol){
        this.symbol = symbol;
    }
    public String getSymbol(){
        return this.symbol;
    }
}
