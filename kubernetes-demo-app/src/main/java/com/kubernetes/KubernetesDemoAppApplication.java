package com.kubernetes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
public class KubernetesDemoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(KubernetesDemoAppApplication.class, args);
	}

}
