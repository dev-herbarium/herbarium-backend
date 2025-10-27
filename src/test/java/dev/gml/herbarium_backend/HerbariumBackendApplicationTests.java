package dev.gml.herbarium_backend;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
// FIXME2o3: 
import org.springframework.context.annotation.Import; // Active it in the future for test contatiners configuration!

// FIXME1o3: 
@Disabled
@Import(TestcontainersConfiguration.class) // Active it in the future for test contatiners configuration!
@SpringBootTest
class HerbariumBackendApplicationTests {

	@Test
	void contextLoads() {
	}

}
