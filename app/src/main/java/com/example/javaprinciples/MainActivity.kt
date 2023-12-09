package com.example.javaprinciples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.javaprinciples.atomic.AtomicUtil
import com.example.javaprinciples.deadlock.DeadLock
import com.example.javaprinciples.deadlock.ReleaseDeadLock
import com.example.javaprinciples.retrantlock.ReentrantLockUse
import com.example.javaprinciples.retrantlock.Singleton
import com.example.javaprinciples.semaphore.SemaphoreUtil
import com.example.javaprinciples.threadlocal.ThreadLocalUse
import com.example.javaprinciples.threadpool.ThreadPoolCreate
import com.example.javaprinciples.threadpool.ThreadPoolExecutor
import com.example.javaprinciples.ui.theme.JavaPrinciplesTheme
import com.example.javaprinciples.volatileuse.NotSafe
import com.example.javaprinciples.volatileuse.VolatileCase
import com.example.javaprinciples.waitnotify.StrangePrinter
import java.util.concurrent.Executors


class MainActivity : ComponentActivity() {
    companion object {
        const val TAG = "minfo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //多窗口售票
//        TicketsDemo.test()

        //类锁对象锁
//        ObjectClassLock.test()
//        ObjectClassLock2.test()

        //中断线程
//        InterruptThread().apply {
//            start()
//        }.toInterrupt()

        //join
//        JoinTestSync.test()

        //生产者、消费者模式
//        Producter().start()
//        Consumer().start()

        //交替打印数字
//        val strangePrinter = StrangePrinter(20)
//        val executorService = Executors.newFixedThreadPool(2)
//        executorService.submit(strangePrinter.MyPrinter("Printer1", 0))
//        executorService.submit(strangePrinter.MyPrinter("Printer2", 1))
//        executorService.shutdown()

        //必然死锁
//        DeadLock().deadLock()

        //信号量
//        SemaphoreUtil().test()

        //Reetrantlock
//        Singleton.relock()
//        ReentrantLockUse("thread1").start()
//        ReentrantLockUse("thread2").start()
//        ReentrantLockUse("thread3").start()

        //volatile
//         VolatileCase.test()
//        NotSafe.test()

        //atomic
//        AtomicUtil.test()

        //threadlocal
//        ThreadLocalUse.test()

        //死锁
//        DeadLock().deadLock()
//        ReleaseDeadLock.test()

        //线程池操作
//        ThreadPoolCreate().createThreadPool()
//        ThreadPoolExecutor.createThreadPool()
    }
}
