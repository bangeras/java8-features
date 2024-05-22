package com.svb;

import java.util.concurrent.TimeUnit;

public class Utils {
    public static void delay(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
