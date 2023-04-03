package uaic.fii.generators.subscriptions.fields;

import uaic.fii.generators.NumberGenerator;
import uaic.fii.generators.OperatorGenerator;
import uaic.fii.models.SubscriptionField;

import java.util.ArrayList;
import java.util.List;

public class WindFieldGenerator {
    private final int totalNumberOfWindFields;

    public WindFieldGenerator(int totalNumberOfWindFields) {
        this.totalNumberOfWindFields = totalNumberOfWindFields;
    }

    public List<SubscriptionField> generateWindFields() {
        java.util.List<SubscriptionField> list = new ArrayList<>();
        for(int i = 0; i< totalNumberOfWindFields; i++){
            list.add(new SubscriptionField("wind", String.valueOf(NumberGenerator.getRandomInt(0, 100)), OperatorGenerator.getRandomOperator()));
        }
        return list;
    }
}
