package com.occ.openstack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * CommandLineApp
 *
 */
@Configuration
@ComponentScan(basePackages = "com.occ")
@EnableAutoConfiguration
public class CommandLineApp {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(CommandLineApp.class);
		app.setWebEnvironment(false); // CONSOLE ME

		//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		//		context.scan("com.occ");
		//		context.refresh();
		//		System.out.println("Context: " + Lists.newArrayList(context.getBeanDefinitionNames()));

		ConfigurableApplicationContext ctx = app.run(args);
	}
}
