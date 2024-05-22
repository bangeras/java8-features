package com.svb.concurrency.completablefuture;

import java.util.concurrent.CompletableFuture;

public class CompletableExceptionHandling {

    public static void main(String[] args) {
        boolean isError = false;

        CompletableFuture<String> futureUsingHandle = CompletableFuture.supplyAsync(()->{
           if(isError) throw new RuntimeException("Exception occurred within futureUsingHandle...");
            return "Success123";
        }).handle((success, error) -> {
            if(error!=null){
                System.out.println(error.getMessage());
            }
            return success;
        });
        System.out.println(futureUsingHandle.join());

        CompletableFuture<String> futureUsingExceptionally = CompletableFuture.supplyAsync(()->{
            if(isError) throw new RuntimeException("Exception occurred within futureUsingExceptionally...");
            return "Success456";
        }).exceptionally((error) -> {
            System.out.println(error.getMessage());
            return null;
        });
        System.out.println(futureUsingExceptionally.join());

    }
}
