package runtimetools;

import java.util.concurrent.TimeUnit;

public class RunTimeTools {
    public static void delay(int t) {
        try {
            TimeUnit.MILLISECONDS.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
