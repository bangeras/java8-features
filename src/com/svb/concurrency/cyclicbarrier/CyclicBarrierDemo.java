package com.svb.concurrency.cyclicbarrier;

import java.util.concurrent.*;

public class CyclicBarrierDemo {
    private final String[] mealItems = {"Fries", "Burger", "Beverages"};

    private final CyclicBarrier cyclicBarrier;

    public CyclicBarrierDemo(){
        cyclicBarrier = new CyclicBarrier(3, ()->{
            System.out.println("Meal is prepared and Order is ready!");
        });
    }

    public static void main(String[] args) {
        CyclicBarrierDemo cyclicBarrierDemo = new CyclicBarrierDemo();
        cyclicBarrierDemo.prepareMeal();
    }

    public void prepareMeal(){
        ExecutorService mealExecutor = Executors.newFixedThreadPool(4);

        for (String mealItem : mealItems) {
            System.out.println(mealItem);
            mealExecutor.submit(prepareMealItem(mealItem));
        }

        mealExecutor.shutdown();
    }

    private Runnable prepareMealItem(String mealItem){
        Runnable runnable = ()->{
            System.out.println("Preparing %s".formatted(mealItem));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("** Prepared%s".formatted(mealItem));

            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
            System.out.println("!! Proceeding after preparing %s".formatted(mealItem));

        };
        return runnable;
    }

}
