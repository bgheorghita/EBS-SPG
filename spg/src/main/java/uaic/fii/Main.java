package uaic.fii;

import uaic.fii.generators.SubscriptionGenerator;
import uaic.fii.managers.PublicationManager;

public class Main {
    public static void main(String[] args) {
//        PublicationManager publicationManager = new PublicationManager();
//        publicationManager.generatePublicationsWithThreadParallelization(1000, 5);
//        publicationManager.generatePublicationsWithoutParallelization(1000);
        SubscriptionGenerator subscriptionGenerator = new SubscriptionGenerator();
        subscriptionGenerator.generateSubscriptions(20, 0.5, 0.25, 0.25);
    }
}