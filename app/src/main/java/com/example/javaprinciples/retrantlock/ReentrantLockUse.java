package com.example.javaprinciples.retrantlock;

import static com.example.javaprinciples.MainActivity.TAG;
import android.util.Log;
import androidx.annotation.NonNull;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockUse extends Thread {
    public static int sum = 0;
    public static ReentrantLock lock = new ReentrantLock();

    public ReentrantLockUse(@NonNull String name) {
        super.setName(name);
    }

    @Override
    public void run() {
        for (int i=0;i<1000;i++) {
            lock.lock();
            try {
                Log.i(TAG, getName() + ": " + sum);
                sum++;
            } finally {
                lock.unlock();
            }
        }
    }
}
