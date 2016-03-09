package com.bg.thsb.openstack;

import com.google.common.collect.Lists;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * CommandLineApp
 *
 */
@Configuration
@ComponentScan(basePackages = "com.bg.thsb")
@EnableAutoConfiguration
public class CommandLineApp {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(CommandLineApp.class);
		app.setWebEnvironment(false); // CONSOLE ME

//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//		context.scan("com.bg.thsb");
//		context.refresh();
//		System.out.println("Context: " + Lists.newArrayList(context.getBeanDefinitionNames()));

		ConfigurableApplicationContext ctx = app.run(args);
	}
}
