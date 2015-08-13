package com.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApplication {
	
	//Unable to find contextual data of type: javax.servlet.ServletContext
	
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }


}