package com.bg.thsb;

import com.bg.thsb.testdrive.TestDriveRunner;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.bg.thsb");
        context.refresh();

        System.out.println("Context: " + Lists.newArrayList(context.getBeanDefinitionNames()));

        TestDriveRunner testDriveRunner = (TestDriveRunner) context.getBean("testDriveRunner");
        testDriveRunner.run();

        // todo fix this. something hangs
        System.exit(0);
    }
}
