package com.example.task1;

import com.example.task1.ui.OrderUI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Task1Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Task1Application.class, args);
		context.getBean(OrderUI.class).run();
	}
}
