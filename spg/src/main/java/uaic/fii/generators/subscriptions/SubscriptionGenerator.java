package uaic.fii.generators.subscriptions;

import uaic.fii.generators.subscriptions.fields.CityFieldGenerator;
import uaic.fii.generators.subscriptions.fields.TempFieldGenerator;
import uaic.fii.generators.subscriptions.fields.WindFieldGenerator;
import uaic.fii.models.Subscription;
import uaic.fii.models.SubscriptionField;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class SubscriptionGenerator implements Callable<List<Subscription>> {
    private final int start;
    private final int end;
    private double cityFreq;
    private double tempFreq;
    private double windFreq;
    private final double minFreqEqualOperatorForCityField;

    public SubscriptionGenerator(int start, int end, double cityFreq, double tempFreq, double windFreq, double minFreqEqualOperatorForCityField) {
        this.start = start;
        this.end = end;
        this.cityFreq = cityFreq;
        this.tempFreq = tempFreq;
        this.windFreq = windFreq;
        this.minFreqEqualOperatorForCityField = minFreqEqualOperatorForCityField;
    }

    private List<Subscription> computeSubscriptions(List<SubscriptionField> cityFields, List<SubscriptionField> tempFields, List<SubscriptionField> windFields){
        int sizeCityFields = cityFields.size();
        int sizeTempFields = tempFields.size();
        int sizeWindFields = windFields.size();
        int maxPossibleSubscriptions = Math.max(Math.max(sizeCityFields, sizeTempFields), sizeWindFields);

        List<Subscription> subscriptions = new ArrayList<>();

        for(int i=0; i<maxPossibleSubscriptions; i++){
            boolean hasCityField = i < sizeCityFields;
            boolean hasTempField = i < sizeTempFields;
            boolean hasWindField = i < sizeWindFields;

            List<SubscriptionField> subscriptionFields = new ArrayList<>();

            if(hasCityField){
                subscriptionFields.add(cityFields.get(i));
            }

            if(hasTempField){
                subscriptionFields.add(tempFields.get(i));
            }

            if(hasWindField){
                subscriptionFields.add(windFields.get(i));
            }

            Subscription subscription = new Subscription(subscriptionFields);
            subscriptions.add(subscription);
        }
        return subscriptions;
    }

    @Override
    public List<Subscription> call() {
        checkFrequencies();
        int numberOfSubscriptions = end - start;
        System.out.println("Parameters info");
        System.out.println("City Freq: " + cityFreq);
        System.out.println("Temp Freq: " + tempFreq);
        System.out.println("Wind Freq: " + windFreq);
        System.out.println("Asked Subscriptions: " + numberOfSubscriptions);
        System.out.println();

        List<SubscriptionField> cityFields;
        List<SubscriptionField> tempFields;
        List<SubscriptionField> windFields;

        double maxFreq = Math.max(Math.max(cityFreq, tempFreq), windFreq);
        double stabilizingFactor = numberOfSubscriptions / (maxFreq * numberOfSubscriptions);
        System.out.println("stabilizingFactor = " + stabilizingFactor + " based on maxFreq = " + maxFreq);

        double numberOfCityFieldsToGenerate = numberOfSubscriptions * cityFreq * stabilizingFactor;
        double numberOfTempFieldsToGenerate = numberOfSubscriptions * tempFreq * stabilizingFactor;
        double numberOfWindFieldsToGenerate = numberOfSubscriptions * windFreq * stabilizingFactor;

        int sizeOfCityFields = (int)numberOfCityFieldsToGenerate;
        int sizeOfTempFields = (int)numberOfTempFieldsToGenerate;
        int sizeOfWindFields = (int)numberOfWindFieldsToGenerate;

        double totalFieldsSizeDouble = numberOfCityFieldsToGenerate + numberOfTempFieldsToGenerate + numberOfWindFieldsToGenerate;
        int totalFieldsSizeInt = sizeOfCityFields + sizeOfTempFields + sizeOfWindFields;
        System.out.println("Fields Lost And Replaced Due To Conversion: " + (totalFieldsSizeDouble - totalFieldsSizeInt));
        System.out.println();

        cityFields = new CityFieldGenerator(sizeOfCityFields, minFreqEqualOperatorForCityField).generateCityFields();
        tempFields = new TempFieldGenerator(sizeOfTempFields).generateTempFields();
        windFields = new WindFieldGenerator(sizeOfWindFields).generateWindFields();
        System.out.println();
        System.out.println("City Fields Generated: " + cityFields.size());
        System.out.println("Temp Fields Generated: " + tempFields.size());
        System.out.println("Wind Fields Generated: " + windFields.size());

        List<Subscription> subscriptionList = computeSubscriptions(cityFields, tempFields, windFields);
        System.out.println("Generated Subscriptions: " + subscriptionList.size());
        return subscriptionList;
    }

    private void checkFrequencies() {
        if(cityFreq > 1){
            cityFreq = 1;
        } else if(cityFreq < 0){
            cityFreq = 0;
        }

        if(tempFreq > 1){
            tempFreq = 1;
        } else if (tempFreq < 0) {
            tempFreq = 0;
        }

        if(windFreq > 1){
            windFreq = 1;
        } else if(windFreq < 0){
            windFreq = 0;
        }

        if((cityFreq + tempFreq + windFreq) != 1)   {
            cityFreq = 0.33;
            tempFreq = 0.33;
            windFreq = 0.34;
        }
    }
}
