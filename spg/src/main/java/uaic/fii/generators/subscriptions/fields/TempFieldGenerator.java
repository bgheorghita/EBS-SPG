package uaic.fii.generators.subscriptions.fields;

import uaic.fii.generators.NumberGenerator;
import uaic.fii.models.SubscriptionField;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class TempFieldGenerator implements Callable<List<SubscriptionField>> {

    private final int numberOfTempFields;
    private final String operator;

    public TempFieldGenerator(int numberOfTempFields, String operator) {
        this.numberOfTempFields = numberOfTempFields;
        this.operator = operator;
    }

    @Override
    public List<SubscriptionField> call() throws Exception {
        List<SubscriptionField> list = new ArrayList<>();
        for(int i=0; i<numberOfTempFields; i++){
            list.add(new SubscriptionField("temp", String.valueOf(NumberGenerator.getRandomInt(-20, 40)), operator));
        }
        return list;
    }
}
