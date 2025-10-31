package dev.gml.herbarium_backend.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertNotNull;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void saveUser_Success() {
        // --- Arrange ---
        UserEntity user = UserEntity.builder()
                .email("test@example.com")
                .password("hashedPasword")
                .build();

        when(userRepository.save(any(UserEntity.class))).thenReturn(user);

        // --- Act ---
        UserEntity savedUser = userService.save(user);

        // --- Assert ---
        assertNotNull(savedUser);
        assertEquals("test@example.com", savedUser.getEmail());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void findByEmail_UserExists() {
        // --- Arrange ---
        String email = "test@example.com";
        UserEntity user = UserEntity.builder()
                .email(email)
                .password("hashedPassword")
                .build();

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // --- Act ---
        UserEntity foundUser = userService.findByEmail(email);

        // --- Assert ---
        assertNotNull(foundUser);
        assertEquals(email, foundUser.getEmail());
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void findByEmail_UserNotFound() {
        // --- Arrange ---
        String email = "nonexistent@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // --- Act & Assert ---
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.findByEmail(email));

        assertEquals("User not found", exception.getMessage());
        verify(userRepository, times(1)).findByEmail(email);
    }

}
