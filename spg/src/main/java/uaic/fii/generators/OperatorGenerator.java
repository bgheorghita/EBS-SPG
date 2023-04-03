package uaic.fii.generators;

import uaic.fii.models.Operator;

import java.util.HashMap;
import java.util.Map;

public class OperatorGenerator {
    private static Map<Integer, String> operators;

    static {
        initOperators();
    }

    private static void initOperators(){
        operators = new HashMap<>();
        operators.put(1, Operator.LESS.getSymbol());
        operators.put(2, Operator.GREATER.getSymbol());
        operators.put(3, Operator.EQUAL.getSymbol());
        operators.put(4, Operator.LESS_OR_EQUAL.getSymbol());
        operators.put(5, Operator.GREATER_OR_EQUAL.getSymbol());
    }

    public static String getRandomOperator(){
        int operatorMapId = NumberGenerator.getRandomInt(1, operators.size());
        return operators.get(operatorMapId);
    }
}
