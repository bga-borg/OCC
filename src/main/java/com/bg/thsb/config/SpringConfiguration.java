package com.bg.thsb.config;

import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@ComponentScan
@SuppressWarnings("unused")
public class SpringConfiguration {

    Cache<String, Object> infinispanCache = null;

    @Bean
    public Cache infinispanCache() {
        if (infinispanCache == null) {
            try {
                infinispanCache = new DefaultCacheManager("infinispan-configuration.xml")
                        .getCache("objectInMem");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return infinispanCache;
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