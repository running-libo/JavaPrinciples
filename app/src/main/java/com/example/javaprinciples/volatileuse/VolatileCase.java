package com.example.javaprinciples.volatileuse;

import static com.example.javaprinciples.MainActivity.TAG;
import android.util.Log;

public class VolatileCase {

    private static volatile boolean ready;
    private static String value;

    private static class PrintThread extends Thread {

        @Override
        public void run() {
            Log.i(TAG, "PrintThread is running...");
            value = "hello world";
            while (!ready) {

            }
            Log.i(TAG, value.toUpperCase());
        }
    }

    private static class ChangeThread extends Thread {
        @Override
        public void run() {
            ready = true;
            Log.i(TAG, "ready has changed");
        }
    }

    public static void test() {
        new PrintThread().start();
        new ChangeThread().start();
    }
}
