package com.example.javaprinciples.innerclass;


/**
 * 内部类 demo代码
 */
public class OuterClass {

    private String name;
    private static String staticName;

    public String getName() {
        return name;
    }

    /**
     * 静态内部类
     */
    static class StaticInerCls {
        private String name;

        public String getName() {
            return name;
        }

        /**
         * 获取外部类静态属性
         * @return
         */
        public String getParentName() {
            //能访问外部类的静态属性，无法访问外部类非静态属性
            return OuterClass.staticName;
        }
    }

    /**
     * 成员内部类
     */
    class InnerCls {
        private String name;
//        private static int id;  //不允许，会报错
        private static final int TYPE = 0; //允许

        public String getName() {
            return name;
        }

        /**
         * 获取外部类非静态属性
         * @return
         */
        public String getParentName() {
            return OuterClass.this.name;
        }
    }

    /**
     * 局部内部类
     */
    Object method() {
        final int[] localVariable = {0};
        class Inner {
            void println(){
                System.out.println("localVariable " + localVariable[0]++);
            }
        }
        Object in = new Inner();
        return in;
    }

    /**
     * 匿名内部类
     */
    private Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {

        }
    });
}
