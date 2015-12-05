package com.bg.thsb;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class BootApp extends SpringBootServletInitializer {

    public static void main(String[] args) throws Exception {
        SpringApplicationBuilder sAB = new SpringApplicationBuilder(BootApp.class);

//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        context.scan("com.bg.thsb");
//        context.refresh();
//
//        System.out.println("Context: " + Lists.newArrayList(context.getBeanDefinitionNames()));
        sAB.run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(
                BootApp.class
        );
    }
}

/**
 * Below are commonly used Spring annotation which makes a bean auto-detectable:
 *
 * @Repository - Used to mark a bean as DAO Component on persistence layer
 * @Service - Used to mark a bean as Service Component on business layer
 * @Controller - Used to mark a bean as Controller Component on Presentation layer
 * @Configuration - Used to mark a bean as Configuration Component.
 * @Component - General purpose annotation, can be used as a replacement for above annotations.
 */