package com.example.javaprinciples.classloader;

import android.app.Activity;
import android.os.Handler;

public class ClassLoader {

    public static void getClassLoader(Activity context) {
        System.out.println("当前activity的classloader " + context.getClassLoader().toString());

        System.out.println("parent classloader " + context.getClassLoader().getParent().toString());

        System.out.println("String.class的classloader " + String.class.getClassLoader().toString());
        System.out.println("Handler.class的classloader " + Handler.class.getClassLoader().toString());
    }


}
