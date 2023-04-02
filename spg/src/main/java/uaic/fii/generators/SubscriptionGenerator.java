package uaic.fii.generators;

import uaic.fii.models.Subscription;
import uaic.fii.models.SubscriptionField;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionGenerator {
    public List<Subscription> generateSubscriptions(int numberOfSubscriptions, double cityFreq, double tempFreq, double windFreq){
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
        System.out.println("City Freq: " + cityFreq);
        System.out.println("Temp Freq: " + tempFreq);
        System.out.println("Wind Freq: " + windFreq);
        System.out.println("Asked Subscriptions: " + numberOfSubscriptions + "\n");

        List<SubscriptionField> cityFields;
        List<SubscriptionField> tempFields;
        List<SubscriptionField> windFields;

        double maxFreq = Math.max(Math.max(cityFreq, tempFreq), windFreq);
        double stabilizingFactor = numberOfSubscriptions / (maxFreq * numberOfSubscriptions);
        System.out.println("stabilizingFactor = " + stabilizingFactor + ", maxFreq = " + maxFreq);

        double numberOfCityFieldsToGenerate = numberOfSubscriptions * cityFreq * stabilizingFactor;
        double numberOfTempFieldsToGenerate = numberOfSubscriptions * tempFreq * stabilizingFactor;
        double numberOfWindFieldsToGenerate = numberOfSubscriptions * windFreq * stabilizingFactor;

        int sizeOfCityFields = (int)numberOfCityFieldsToGenerate;
        int sizeOfTempFields = (int)numberOfTempFieldsToGenerate;
        int sizeOfWindFields = (int)numberOfWindFieldsToGenerate;

        double totalFieldsSizeDouble = numberOfCityFieldsToGenerate + numberOfTempFieldsToGenerate + numberOfWindFieldsToGenerate;
        int totalFieldsSizeInt = sizeOfCityFields + sizeOfTempFields + sizeOfWindFields;
        System.out.println("Fields Lost Due To Conversion: " + (totalFieldsSizeDouble - totalFieldsSizeInt));

        cityFields = generateCityFields(sizeOfCityFields, "=");
        tempFields = generateTempFields(sizeOfTempFields, ">");
        windFields = generateWindFields(sizeOfWindFields, "<");
        System.out.println("City Fields: " + cityFields.size());
        System.out.println("Temp Fields: " + tempFields.size());
        System.out.println("Wind Fields: " + windFields.size());

        List<Subscription> subscriptionList = computeSubscriptions(cityFields, tempFields, windFields);
        System.out.println("Generated Subscriptions: " + subscriptionList.size());
        System.out.println(subscriptionList);
        return subscriptionList;
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


    private List<SubscriptionField> generateCityFields(int numberOfCityFields, String operator){
        List<SubscriptionField> list = new ArrayList<>();
        for(int i=0; i< numberOfCityFields; i++){
            list.add(new SubscriptionField("city", CityGenerator.getRandomCity(), operator));
        }
        return list;
    }

    private List<SubscriptionField> generateTempFields(int numberOfTempFields, String operator){
        List<SubscriptionField> list = new ArrayList<>();
        for(int i=0; i<numberOfTempFields; i++){
            list.add(new SubscriptionField("temp", String.valueOf(NumberGenerator.getRandomInt(-20, 40)), operator));
        }
        return list;
    }

    private List<SubscriptionField> generateWindFields(int numberOfWindFields, String operator){
        List<SubscriptionField> list = new ArrayList<>();
        for(int i=0; i<numberOfWindFields; i++){
            list.add(new SubscriptionField("wind", String.valueOf(NumberGenerator.getRandomInt(0, 100)), operator));
        }
        return list;
    }
}
