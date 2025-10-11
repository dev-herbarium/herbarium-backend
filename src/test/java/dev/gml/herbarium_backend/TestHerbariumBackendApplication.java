package dev.gml.herbarium_backend;

import org.springframework.boot.SpringApplication;

public class TestHerbariumBackendApplication {

	public static void main(String[] args) {
		SpringApplication.from(HerbariumBackendApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
