package com.example.javaprinciples.handler

object HandlerTest {
    fun handleMessage() {
        Looper.prepareMain()

        val handler: Handler = object: Handler() {
            override fun handleMessage(message: Message?) {
                message?.let {
                    if (message.callback != null) {
                        message.callback.run() //如果是post类型，执行runnable的run方法
                    } else {
                        println("收到what: " + it.what + "收到arg1" + it.arg1 + "收到arg2" + it.arg2)
                    }
                }
            }
        }

        Thread {
            handler.sendMessage(Message.obtain().apply {
                what = "这里what数据"
                arg1 = "这是arg1数据"
                arg2 = "这是arg2数据"
            })

            handler.sendMessage(Message.obtain().apply {
                what = "再发送what数据"
                arg1 = "再发送arg1数据"
                arg2 = "再发送arg2数据"
            })

            Thread.sleep(4000)

            handler.post(Runnable {
                println("执行了handler post任务")
            })

            Looper.getMainLooper().setNativeEpoll()  //停止循环获取消息
        }.start()

        Looper.loop()
    }
}