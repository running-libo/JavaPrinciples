package com.example.javaprinciples.atomic;

import static com.example.javaprinciples.MainActivity.TAG;
import android.util.Log;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicUtil {
    private static AtomicInteger count = new AtomicInteger(0);

    public static void test() {
        for (int i=0;i<2;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    for (int j=0;j<100;j++) {
                        count.incrementAndGet();
                    }
                }
            }).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "count=" + count.get());
    }
}
