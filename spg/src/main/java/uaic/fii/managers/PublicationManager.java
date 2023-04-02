package uaic.fii.managers;

import uaic.fii.generators.*;
import java.util.concurrent.*;

public class PublicationManager {
    public void generatePublicationsWithoutParallelization(int numberOfPublications){
        long startTime = System.currentTimeMillis();
        new PublicationGenerator(0, numberOfPublications).run();
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time taken to generate " + numberOfPublications + " publications without parallelization: " + totalTime + " ms");
    }

    public void generatePublicationsWithThreadParallelization(int numberOfPublications, int maxThreadsToUse){
        int numOfCores = Runtime.getRuntime().availableProcessors();
        if(maxThreadsToUse > numOfCores){
            maxThreadsToUse = numOfCores;
            System.out.println("Info. Max threads has been set to the maximum of the available CPU cores.\n");
        }
        int maxPublicationsPerThread = numberOfPublications / maxThreadsToUse;

        System.out.println("System CPU Cores: " + numOfCores);
        System.out.println("Threads Used: " + maxThreadsToUse);
        System.out.println("Publications/Thread: " + maxPublicationsPerThread);
        ExecutorService executor = Executors.newFixedThreadPool(numOfCores);

        long startTime = System.currentTimeMillis();
        for(int thread = 0; thread < maxThreadsToUse; thread ++){
            int start = thread * maxPublicationsPerThread;
            int end = (thread + 1) * maxPublicationsPerThread;
            if(thread == maxThreadsToUse - 1){
                end = numberOfPublications;
            }
            System.out.println("thread " + thread + " range [" + start + " - " + end + ")");
            executor.execute(new PublicationGenerator(start, end));
        }
        executor.shutdown();

        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MICROSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time taken to generate " + numberOfPublications + " publications with thread parallelization: " + totalTime + " ms");
    }
}