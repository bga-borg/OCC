package com.bg.thsb.config;

import com.bg.thsb.testdrive.TestDriveRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@SuppressWarnings("unused")
public class SpringConfiguration {

    TestDriveRunner testDriveRunner() {
        return new TestDriveRunner();
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