package com.svb.concurrency.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Mutex is the Semaphore with an access count of 1.
 * Consider a situation of using lockers in the bank. Usually the rule is that only one person is allowed to enter the locker room.
 */
public class MutexDemo {
    public static void main(String[] args) {
        ExecutorService customers = Executors.newFixedThreadPool(3);
        final Semaphore lockerRoomMutex = new Semaphore(1, true);

        // Simulate 10 customers queued up to access bank locker around the same time.
        for (int i = 1; i <= 10; i++) {
            int id = i;
            customers.submit(()->{
                try {
                    lockerRoomMutex.acquire();
                    System.out.println("Customer " + id + " is using Locker" );

                    // Simulate time spent at the ATM
                    Thread.sleep(2000); // Simulated withdrawal time

                    System.out.println("Customer " + id + " has finished using the Locker.");

                    // Release the permit (free up the ATM)
                    lockerRoomMutex.release();

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
