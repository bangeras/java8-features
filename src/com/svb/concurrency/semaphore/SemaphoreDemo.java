package com.svb.concurrency.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    public static void main(String[] args) {
        ExecutorService customers = Executors.newFixedThreadPool(3);
        final Semaphore availableATMs = new Semaphore(3, true);

        // Simulate 10 customers trying to withdraw money from ATMs
        for (int i = 1; i <= 10; i++) {
            int id = i;
            customers.submit(()->{
                try {
                    availableATMs.acquire();
                    System.out.println("Customer " + id + " is using ATM" );

                    // Simulate time spent at the ATM
                    Thread.sleep(2000); // Simulated withdrawal time

                    System.out.println("Customer " + id + " has finished using the ATM.");

                    // Release the permit (free up the ATM)
                    availableATMs.release();

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        customers.shutdown();

    }


}
