package com.example;

import com.example.boot.annotations.*;
import com.example.boot.starter.MyBootApplication;
import com.example.boot.context.MyApplicationContext;


public class MyApplication {
    public static void main(String[] args) {
        MyApplicationContext context = MyBootApplication.run(MyApplication.class);
        MyService svc = context.getBean(MyService.class);
        svc.print();
    }
}


