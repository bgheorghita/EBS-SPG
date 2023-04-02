package uaic.fii;

import uaic.fii.managers.SubscriptionManager;

public class Main {
    public static void main(String[] args) throws Exception {
//        PublicationManager publicationManager = new PublicationManager();
//        publicationManager.generatePublicationsWithThreadParallelization(1000, 5);
//        publicationManager.generatePublicationsWithoutParallelization(1000);
//        SubscriptionGenerator subscriptionGenerator = new SubscriptionGenerator();
//        subscriptionGenerator.generateSubscriptions(20, 0.5, 0.25, 0.25);
        SubscriptionManager subscriptionManager = new SubscriptionManager();
        subscriptionManager.generateSubscriptionsWithoutParallelization(5, 0.5, 0.25, 0.25);
        subscriptionManager.generateSubscriptionsWithThreadParallelization(5, 0.5, 0.25, 0.25, 3);
    }
}