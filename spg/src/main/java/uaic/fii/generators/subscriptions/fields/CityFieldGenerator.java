package uaic.fii.generators.subscriptions.fields;

import uaic.fii.generators.CityGenerator;
import uaic.fii.models.SubscriptionField;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class CityFieldGenerator implements Callable<List<SubscriptionField>> {

    private final int numberOfCityFields;
    private final String operator;

    public CityFieldGenerator(int numberOfCityFields, String operator) {
        this.numberOfCityFields = numberOfCityFields;
        this.operator = operator;
    }

    @Override
    public List<SubscriptionField> call() throws Exception {
        List<SubscriptionField> list = new ArrayList<>();
        for(int i=0; i< numberOfCityFields; i++){
            list.add(new SubscriptionField("city", CityGenerator.getRandomCity(), operator));
        }
        return list;
    }
}
