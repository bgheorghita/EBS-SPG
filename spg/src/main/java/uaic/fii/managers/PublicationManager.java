package uaic.fii.managers;

import uaic.fii.generators.*;
import uaic.fii.models.Publication;
import uaic.fii.models.Subscription;
import uaic.fii.models.SubscriptionField;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class PublicationManager {
    public List<Publication> generatePublicationsWithoutParallelization(int numberOfPublications) throws Exception {
        long startTime = System.currentTimeMillis();
        PublicationGenerator publicationGenerator = new PublicationGenerator(0, numberOfPublications);
        List<Publication> publications = publicationGenerator.call();
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time taken to generate " + numberOfPublications + " publications without parallelization: " + totalTime + " ms");
        return publications;
    }

    public List<Publication> generatePublicationsWithThreadParallelization(int numberOfPublications, int maxThreadsToUse){
        List<Publication> publications = new ArrayList<>();
        int numOfCores = Runtime.getRuntime().availableProcessors();
        if(maxThreadsToUse > numOfCores){
            maxThreadsToUse = numOfCores;
            System.out.println("Info. Max threads has been set to the maximum of the available CPU cores.\n");
        }
        int maxPublicationsPerThread = numberOfPublications / maxThreadsToUse;

        System.out.println("System CPU Cores: " + numOfCores);
        System.out.println("Threads Used: " + maxThreadsToUse);
        System.out.println("Publications/Thread: " + maxPublicationsPerThread);
        ExecutorService executor = Executors.newFixedThreadPool(maxThreadsToUse);
        List<Future<List<Publication>>> futurePublicationsList = new ArrayList<>();

        long startTime = System.currentTimeMillis();
        for(int thread = 0; thread < maxThreadsToUse; thread ++){
            int start = thread * maxPublicationsPerThread;
            int end = (thread + 1) * maxPublicationsPerThread;
            if(thread == maxThreadsToUse - 1){
                end = numberOfPublications;
            }
            System.out.println("thread " + thread + " range [" + start + " - " + end + ")");
            Callable<List<Publication>> publicationCallable = new PublicationGenerator(start, end);
            Future<List<Publication>> futurePublicationList = executor.submit(publicationCallable);
            futurePublicationsList.add(futurePublicationList);
        }

        for(Future<List<Publication>> futurePublication : futurePublicationsList){
            try {
                publications.addAll(futurePublication.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time taken to generate " + numberOfPublications + " publications with thread parallelization: " + totalTime + " ms");
        return publications;
    }
}