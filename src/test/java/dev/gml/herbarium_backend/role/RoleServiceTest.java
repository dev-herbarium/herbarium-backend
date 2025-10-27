package dev.gml.herbarium_backend.role;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    @Test
    void getById_RoleExists() {
        // --- Arrange ---
        Long roleId = 1L;
        RoleEntity expectedRole = new RoleEntity();
        expectedRole.setId(roleId);
        expectedRole.setName("ROLE_ADMIN");

        when(roleRepository.findById(roleId)).thenReturn(Optional.of(expectedRole));

        // --- Act ---
        RoleEntity result = roleService.getById(roleId);

        // --- Assert ---
        assertNotNull(result);
        assertEquals(roleId, result.getId());
        assertEquals("ROLE_ADMIN", result.getName());
        verify(roleRepository, times(1)).findById(roleId);
    }

    @Test
    void getById_RoleNotFound() {
        // --- Arrange ---
        Long roleId = 999L;
        when(roleRepository.findById(roleId)).thenReturn(Optional.empty());

        // --- Act & Assert ---
        assertThrows(RuntimeException.class, () -> roleService.getById(roleId));
        verify(roleRepository, times(1)).findById(roleId);
    }

    @Test
    void assignDefaultRole_Success() {
        // --- Arrange ---
        RoleEntity defaultRole = new RoleEntity();
        defaultRole.setId(2L);
        defaultRole.setName("ROLE_USER");

        when(roleRepository.findById(2L)).thenReturn(Optional.of(defaultRole));

        // --- Act ---
        Set<RoleEntity> result = roleService.assignDefaultRole();

        // --- Assert ---
        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.contains(defaultRole));
        assertEquals("ROLE_USER", result.iterator().next().getName());
        verify(roleRepository, times(1)).findById(2L);
    }

    @Test
    void assignDefaultRole_RoleNotFound() {
        // --- Arrange ---
        when(roleRepository.findById(2L)).thenReturn(Optional.empty());

        // --- Act & Assert ---
        assertThrows(RuntimeException.class, () -> roleService.assignDefaultRole());
        verify(roleRepository, times(1)).findById(2L);
    }
}
