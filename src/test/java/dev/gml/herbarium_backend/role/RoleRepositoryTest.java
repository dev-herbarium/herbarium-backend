package dev.gml.herbarium_backend.role;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create",
        "spring.sql.init.mode=never" // (!) Don't load data from "data-h2.sql".
                                     // This is a completely clean test with fresh data.
})
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    @DisplayName("It should save new role from scratch because no 'data-h2.sql' is loaded.")
    void saveRole_WithCleanDatabase() {
        // --- Arrange ---
        RoleEntity role = new RoleEntity();
        role.setName("ROLE_TEST");

        // --- Act ---
        RoleEntity savedRole = roleRepository.save(role);

        // --- Assert ---
        assertNotNull(savedRole.getId());
        assertEquals("ROLE_TEST", savedRole.getName());
    }

    @Test
    @DisplayName("It should verify that role exists.")
    void findById_RoleExists() {
        // --- Arrange ---
        RoleEntity role = new RoleEntity();
        role.setName("ROLE_TEST");
        RoleEntity savedRole = roleRepository.save(role);

        // --- Act ---
        RoleEntity foundRole = roleRepository.findById(savedRole.getId()).orElse(null);

        // --- Assert ----
        assertNotNull(foundRole);
        assertEquals(savedRole.getId(), foundRole.getId());
        assertEquals("ROLE_TEST", foundRole.getName());
    }
}