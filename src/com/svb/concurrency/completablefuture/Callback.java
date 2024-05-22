package com.svb.concurrency.completablefuture;

import com.svb.Utils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class Callback {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Supplier<String> supplier = () -> {
            Utils.delay(1);
            System.out.println("I am in supplier - " + Thread.currentThread().getName());
            return "Mishti S. Bangera";
        };

        ExecutorService executorService = Executors.newCachedThreadPool();

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(supplier, executorService);
        System.out.println("I am in main - " + Thread.currentThread().getName());

        completableFuture.thenApply((s) -> {
            return "Hello " + s;
        }).thenAccept((s) -> {
            System.out.println(s);
        });

        completableFuture.get();
        executorService.shutdown();
    }
}
