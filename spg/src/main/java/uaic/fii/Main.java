package uaic.fii;

import uaic.fii.managers.PublicationManager;

public class Main {
    public static void main(String[] args) {
        PublicationManager publicationManager = new PublicationManager();
        publicationManager.generatePublicationsWithThreadParallelization(10000000, 5);
        publicationManager.generatePublicationsWithoutParallelization(10000000);
    }
}