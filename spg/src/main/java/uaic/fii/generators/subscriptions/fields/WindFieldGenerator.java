package uaic.fii.generators.subscriptions.fields;

import uaic.fii.generators.NumberGenerator;
import uaic.fii.models.SubscriptionField;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class WindFieldGenerator implements Callable<List<SubscriptionField>> {
    private final int numberOfWindFields;
    private final String operator;

    public WindFieldGenerator(int numberOfWindFields, String operator) {
        this.numberOfWindFields = numberOfWindFields;
        this.operator = operator;
    }

    @Override
    public List<SubscriptionField> call() throws Exception {
        java.util.List<SubscriptionField> list = new ArrayList<>();
        for(int i=0; i<numberOfWindFields; i++){
            list.add(new SubscriptionField("wind", String.valueOf(NumberGenerator.getRandomInt(0, 100)), operator));
        }
        return list;
    }
}
