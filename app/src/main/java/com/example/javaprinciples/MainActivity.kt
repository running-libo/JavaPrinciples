package com.example.javaprinciples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.javaprinciples.interrupt.InterruptThread
import com.example.javaprinciples.join.JoinTestSync
import com.example.javaprinciples.objectclasslock.ObjectClassLock
import com.example.javaprinciples.objectclasslock.ObjectClassLock2
import com.example.javaprinciples.ui.theme.JavaPrinciplesTheme

class MainActivity : ComponentActivity() {
    companion object {
        const val TAG = "minfo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JavaPrinciplesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }

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
        JoinTestSync.test()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JavaPrinciplesTheme {
        Greeting("Android")
    }
}