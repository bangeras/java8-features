package com.svb.concurrency.completablefuture;

import com.svb.Utils;

import java.util.concurrent.CompletableFuture;

public class CombiningDependentFuturesUsingCompose {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        CompletableFuture completableFuture = getUserDetails().thenCompose((s) -> {
            return getWishList(s);
        });

        completableFuture.thenAccept(s -> {
            System.out.println(s);
        });

//        System.out.println("Doing something..");
//        Utils.delay(4);

        completableFuture.join();

        long endTime = System.currentTimeMillis();
        System.out.println("Time taken=" + (endTime-startTime)/1000 );
    }

    public static CompletableFuture<String> getUserDetails(){
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("getUserDetails() -" + Thread.currentThread().getName());
            Utils.delay(2);
            return "Suraj V. Bangera's";
        });
    }

    public static CompletableFuture<String> getWishList(String user){
        return CompletableFuture.supplyAsync(()->{
            System.out.println("getWishList() -" + Thread.currentThread().getName());
            Utils.delay(3);
            return user + " Wish list";
        });
    }
}
