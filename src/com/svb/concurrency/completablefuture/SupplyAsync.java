package com.svb.concurrency.completablefuture;

import com.svb.Utils;

import java.util.concurrent.*;
import java.util.function.Supplier;

public class SupplyAsync {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Supplier<String> supplier = () -> {
            Utils.delay(1);
            System.out.println("I am in supplier - "+ Thread.currentThread().getName());
            return "Hello from Supplier";
        };

        ExecutorService executorService = Executors.newCachedThreadPool();

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(supplier, executorService);
        System.out.println("I am in main");

        String value = completableFuture.get();
        System.out.println("Value - " + value);

        executorService.shutdown();
    }
}
