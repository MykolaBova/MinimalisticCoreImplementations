package com.example.boot.starter;

import com.example.boot.context.MyApplicationContext;
import com.example.boot.annotations.EnableAutoConfiguration;

public class MyBootApplication {
    public static MyApplicationContext run(Class<?> primary) {
        MyApplicationContext ctx = new MyApplicationContext(primary.getPackageName());
        ctx.refresh();
        return ctx;
    }
}


