package com.bg.thsb.dipt1;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

class App {


    private static Logger logger = LoggerFactory.getLogger(App.class);


    public static void main(String... args) {
        logger.debug("Hello debug");
        logger.info("Hello info");


        System.out.println();

    }

}