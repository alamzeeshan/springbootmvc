package com.rameez.monitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// same as @Configuration @EnableAutoConfiguration @ComponentScan
@SpringBootApplication(scanBasePackages="com.rameez.monitoring.web,com.rameez.monitoring.service,com.rameez.monitoring.dao")
public class Application {	
		
    public static void main(String[] args) {
    	//SpringApplication.run(Application.class, args);
    	SpringApplication app = new SpringApplication(Application.class);
    	app.setWebEnvironment(true);
    	//app.addListeners();
    	app.run(args);
    }

}