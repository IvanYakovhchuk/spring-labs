package com.example.demo.components;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
class SecondConsoleRunner implements CommandLineRunner {
    @Override
    public void run(String... args) {
        System.out.println("Second");
    }
}
