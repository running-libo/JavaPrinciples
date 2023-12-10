package com.example.javaprinciples.classloader;

public class Test {

    public static void showClassLoader() {
        System.out.println("Test类已成功加载运行！");
        java.lang.ClassLoader classLoader = Test.class.getClassLoader();
        System.out.println("加载我的classLoader：" + classLoader);
        System.out.println("classLoader.parent：" + classLoader.getParent());
    }
}
