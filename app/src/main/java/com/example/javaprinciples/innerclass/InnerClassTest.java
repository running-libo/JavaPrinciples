package com.example.javaprinciples.innerclass;

public class InnerClassTest {

    public void test() {
        //创建静态内部类：以Class.Iner的形式
        OuterClass.StaticInerCls inerCls = new OuterClass.StaticInerCls();

        //创建内部类对象方法，需要先创建外部类，以object.new Iner的形式：
        OuterClass outerClass = new OuterClass();
        OuterClass.InnerCls innerCls = outerClass.new InnerCls();
    }
}
