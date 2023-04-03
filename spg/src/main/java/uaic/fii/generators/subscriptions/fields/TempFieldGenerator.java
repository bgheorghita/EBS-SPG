package uaic.fii.generators.subscriptions.fields;

import uaic.fii.generators.NumberGenerator;
import uaic.fii.generators.OperatorGenerator;
import uaic.fii.models.SubscriptionField;

import java.util.ArrayList;
import java.util.List;

public class TempFieldGenerator {
    private final int totalNumberOfTempFields;

    public TempFieldGenerator(int totalNumberOfTempFields) {
        this.totalNumberOfTempFields = totalNumberOfTempFields;
    }

    public List<SubscriptionField> generateTempFields() {
        List<SubscriptionField> list = new ArrayList<>();
        for(int i = 0; i< totalNumberOfTempFields; i++){
            list.add(new SubscriptionField("temp", String.valueOf(NumberGenerator.getRandomInt(-20, 40)), OperatorGenerator.getRandomOperator()));
        }
        return list;
    }
}
