package com.example;

import com.example.boot.annotations.Autowired;
import com.example.boot.annotations.Component;
 // сервис + внедрение строки из бина
 @Component
 public class MyService {
//     @Autowired
//     private String dbDriver;

     @Autowired
     private String appName;

     public void print() {
         System.out.println("App name = " + appName);
       //  System.out.println("DB Driver = " + dbDriver);
     }
 }

