package com.example.demo.components;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
class FirstConsoleRunner implements CommandLineRunner {
    @Override
    public void run(String... args) {
        System.out.println("First");
    }
}
