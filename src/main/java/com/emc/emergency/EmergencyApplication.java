package com.emc.emergency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

@EnableAutoConfiguration
@ComponentScan
public class EmergencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmergencyApplication.class, args);
	}
}
