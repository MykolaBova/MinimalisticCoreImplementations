package com.example;

import com.example.boot.annotations.Bean;
import com.example.boot.annotations.Component;
import com.example.boot.annotations.Configuration;

// конфиг класс + компонент
@Configuration
@Component
public class AppConfig {
    @Bean(name = "appName")  // Уникальное имя для бина
    public String appName() {
        return "DemoApp";
    }
}
