package com.svb.concurrency.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A multithreaded webserver application that needs to initialize several subsystems
 * before it can start accepting and processing client requests.
 */
public class WebserverCountdownLatchDemo {

    private final String[] services = {"Database", "Messaging", "SMTP", "Logging"};
    private final CountDownLatch countDownLatch;

    public WebserverCountdownLatchDemo(){
        countDownLatch = new CountDownLatch(services.length);
    }

    public static void main(String[] args) {
        WebserverCountdownLatchDemo countdownLatchDemo = new WebserverCountdownLatchDemo();
        countdownLatchDemo.start();
    }

    private void start(){
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (String service : services){
            executorService.submit(initializeService(service));
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("**** Webserver started at 8080 and listening.. ****");


        executorService.shutdown();
    }

    private Runnable initializeService(String service){
        Runnable runnable = ()->{
            System.out.println("Starting %s".formatted(service));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            countDownLatch.countDown();
            System.out.println("** Started %s".formatted(service));
        };

        return runnable;
    }

}
