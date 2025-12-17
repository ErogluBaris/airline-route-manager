package com.thy.airlineroutemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class AirlineRouteManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirlineRouteManagerApplication.class, args);
	}

}
