package com.svb.concurrency.completablefuture;

import com.svb.Utils;

import java.util.concurrent.CompletableFuture;

public class CombiningAnyAllOfFutures {

    public static void main(String[] args) {
        testAllOf();

        testAnyOf();
    }

    public static void testAnyOf(){
        long start = System.currentTimeMillis();
        CompletableFuture<Object> results = CompletableFuture.anyOf(getCNBCResearch(),
                getScreenerResearch(), getMoneyControlResearch());

        results.thenAccept(s -> System.out.println(s));

        results.join();
        long end = System.currentTimeMillis();
        System.out.println("Time taken for testAnyOf="+(end-start)/1000);
    }

    public static void testAllOf(){
        long start = System.currentTimeMillis();
        CompletableFuture<Void> results = CompletableFuture.allOf(getCNBCResearch(),
                getScreenerResearch(), getMoneyControlResearch());

        results.thenAccept(s -> System.out.println(s));
        results.join();
        long end = System.currentTimeMillis();
        System.out.println("Time taken for testAllOf="+(end-start)/1000);
    }

    public static CompletableFuture<String> getCNBCResearch(){
        return CompletableFuture.supplyAsync(()->{
            System.out.println("CNBC " + Thread.currentThread().getName());
            Utils.delay(1);
            return "CNBC research report";
        });
    }

    public static CompletableFuture<String> getMoneyControlResearch(){
        return CompletableFuture.supplyAsync(()->{
            System.out.println("MoneyControl " + Thread.currentThread().getName());
            Utils.delay(4);
            return "MoneyControl research report";
        });
    }

    public static CompletableFuture<String> getScreenerResearch(){
        return CompletableFuture.supplyAsync(()->{
            System.out.println("Screener " + Thread.currentThread().getName());
            Utils.delay(1);
            return "Screener research report";
        });
    }


}
