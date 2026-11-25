package com.melbourne.bit235.wikilogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Marks this class as the configuration & launch class of Spring Boot.
public class WikiLoginApplication {

    /**
     * The main method is the JVM entry point. It hands control to Spring Boot,
     * which creates the application context and starts the embedded web server.
     *
     * @param args command-line arguments passed to the program
     */
    public static void main(String[] args) {
        // Run the Spring application; this also starts Tomcat on the configured port.
        SpringApplication.run(WikiLoginApplication.class, args);
    }
}
