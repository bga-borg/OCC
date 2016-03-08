package com.bg.thsb.openstack;

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
@ComponentScan
@EnableAutoConfiguration
public class CommandLineApp {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(CommandLineApp.class);
		app.setWebEnvironment(false); //<<<<<<<<<

		ConfigurableApplicationContext ctx = app.run(args);
	}
}
