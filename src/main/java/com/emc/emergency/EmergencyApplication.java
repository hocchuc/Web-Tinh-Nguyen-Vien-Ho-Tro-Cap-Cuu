package com.emc.emergency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

import java.util.logging.Logger;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class EmergencyApplication {
	public static final Logger logger = Logger.getLogger(EmergencyApplication.class.getName());

	public static void main(String[] args)throws IOException  {
		SpringApplication.run(EmergencyApplication.class, args);

	}
}
