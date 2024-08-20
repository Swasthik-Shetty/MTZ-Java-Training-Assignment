package com.monetize360.lsc;

import com.monetize360.lsc.di.GreetingManager;
import com.monetize360.lsc.di.GreetingService;
import com.monetize360.lsc.di.NewYearGreetings;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class LearningSpringCoreApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LearningSpringCoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.monetize360.lsc");

		GreetingManager greetingManager = applicationContext.getBean(GreetingManager.class);
		greetingManager.greet("Alex");
	}
}
