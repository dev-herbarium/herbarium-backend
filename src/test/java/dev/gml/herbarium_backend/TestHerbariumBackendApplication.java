package dev.gml.herbarium_backend;

import org.springframework.boot.SpringApplication;

public class TestHerbariumBackendApplication {

	public static void main(String[] args) {
		// The .with(TestcontainersConfiguration_DISABLED.class) also explicitly
		// loads the Testcontainers setup. It should be commented in the initial setup.
		// FIXME 3o3:
		// SpringApplication.from(HerbariumBackendApplication::main).with(TestcontainersConfiguration_DISABLED.class).run(args);
		// Active it in the future for test contatiners configuration and comment the next line!
		SpringApplication.from(HerbariumBackendApplication::main).run(args);
	}

}
