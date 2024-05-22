package com.svb.concurrency.completablefuture;

import com.svb.Utils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunAsync {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Runnable runnable = () -> {
            Utils.delay(1);
            System.out.println("I am in Runnable - "+ Thread.currentThread().getName());
        };

        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(runnable, executorService);

        System.out.println("I am in main - "+ Thread.currentThread().getName());

        completableFuture.get();
        executorService.shutdown();
    }


}
