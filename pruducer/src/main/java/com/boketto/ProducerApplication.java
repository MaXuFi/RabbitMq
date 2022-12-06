package com.boketto;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Administrator
 */
@SpringBootApplication
public class ProducerApplication {
    public static void main(String[] args) {
        System.setProperty("spring.cloud.bootstrap.enabled", "true");
        SpringApplication.run(ProducerApplication.class,args);
    }
}
