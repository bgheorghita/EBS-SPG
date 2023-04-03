package uaic.fii.models;

public enum Operator {
    EQUAL("="),
    LESS("<"),
    GREATER(">"),
    LESS_OR_EQUAL("<="),
    GREATER_OR_EQUAL(">=");

    private final String symbol;

    Operator(String symbol){
        this.symbol = symbol;
    }

    public String getSymbol(){
        return symbol;
    }

}
