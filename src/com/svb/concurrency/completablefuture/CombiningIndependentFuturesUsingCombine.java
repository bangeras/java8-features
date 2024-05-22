package com.svb.concurrency.completablefuture;

import com.svb.Utils;

import java.util.concurrent.CompletableFuture;

public class CombiningIndependentFuturesUsingCombine {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        CompletableFuture<String> completableFuture = getUserEmail().thenCombine(getMarketResearch(), (email, research) -> {
//            System.out.println("Sending email to " + email + " with " + research);
            Utils.delay(1);
            return "Sending email to " + email + " with " + research;
        });

        System.out.println("Doing something..");
        Utils.delay(3);
        System.out.println(completableFuture.join());
        long end = System.currentTimeMillis();
        System.out.println("Time taken =" + (end-start)/1000);
    }

    public static CompletableFuture<String> getUserEmail(){
        return CompletableFuture.supplyAsync(()->{
            System.out.println("getUserEmail() -" + Thread.currentThread().getName());
            Utils.delay(3);
            return "suraj.bangera@email.com";
        });
    }

    public static CompletableFuture<String> getMarketResearch(){
        return CompletableFuture.supplyAsync(()->{
            System.out.println("getMarketResearch() -" + Thread.currentThread().getName());
            Utils.delay(3);
            return "Market report of BSE";
        });
    }
}
