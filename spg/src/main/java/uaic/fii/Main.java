package uaic.fii;

import uaic.fii.generators.subscriptions.SubscriptionGenerator;
import uaic.fii.generators.subscriptions.fields.CityFieldGenerator;
import uaic.fii.managers.PublicationManager;
import uaic.fii.managers.SubscriptionManager;

public class Main {
    public static void main(String[] args) throws Exception {
//        PublicationManager publicationManager = new PublicationManager();
//        publicationManager.generatePublicationsWithThreadParallelization(1000000, 4);
//        publicationManager.generatePublicationsWithoutParallelization(1000000);
        SubscriptionManager subscriptionManager = new SubscriptionManager();
//        subscriptionManager.generateSubscriptionsWithoutParallelization(12, 0.5, 0.5, 0);
        subscriptionManager.generateSubscriptionsWithThreadParallelization(10, 0.5, 0.5, 0, 0.1, 1);
    }
}