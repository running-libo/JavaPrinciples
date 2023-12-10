package com.example.javaprinciples.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MyClassLoader extends java.lang.ClassLoader {

    private String classPath;

    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //加载指定目录下的class文件

        String clsDir = classPath;
        byte[] classData = getClassData(clsDir);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0 ,classData.length);
        }
    }

    /**
     * 从文件读取字节流
     * @param path
     * @return
     */
    private byte[] getClassData(String path) {
        try {
            InputStream ins = new FileInputStream(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int buffers = 0;
            while((buffers = ins.read(buffer)) != -1) {
                baos.write(buffer, 0 ,buffers);
            }
            return baos.toByteArray();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
