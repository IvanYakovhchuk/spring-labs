package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Order(2)
public class ConsoleHelloWorldApplication implements CommandLineRunner {

	public static void main(String[] args) {
		System.out.println("Begin of main!");
		SpringApplication.run(ConsoleHelloWorldApplication.class, args);
		System.out.println("End of main!");
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello World!");
	}
}

@Component
@Order(1)
class FirstConsoleRunner implements CommandLineRunner {
	@Override
	public void run(String... args) throws Exception {
		System.out.println("First");
	}
}

@Component
@Order(3)
class SecondConsoleRunner implements CommandLineRunner {
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Second");
	}
}
