package com.example;

import com.example.boot.annotations.Bean;
import com.example.boot.annotations.ConditionalOnClass;
import com.example.boot.annotations.Configuration;

// автоконфиг, активируется только если есть java.sql.Driver в classpath
@Configuration
@ConditionalOnClass("java.sql.Driver")
public class DatabaseAutoConfiguration {
//    @Bean(name = "dbDriver")
//    public String dbDriver() {
//        return "jdbc:loaded";
//    }
}

