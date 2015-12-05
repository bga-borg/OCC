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