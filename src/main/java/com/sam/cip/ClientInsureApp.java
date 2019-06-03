package com.sam.cip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientInsureApp {

    public static final String APPLICATION_ID = "121";

    public static void main(String[] args) {
		SpringApplication.run(ClientInsureApp.class, args);
	}
}
