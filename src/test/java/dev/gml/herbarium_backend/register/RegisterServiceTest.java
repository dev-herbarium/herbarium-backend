package dev.gml.herbarium_backend.register;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
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

import dev.gml.herbarium_backend.user.UserEntity;
import dev.gml.herbarium_backend.user.UserRepository;
import dev.gml.herbarium_backend.user.UserService;
import dev.gml.herbarium_backend.facade.encryptions.decrypt.IDecryptFacade;
import dev.gml.herbarium_backend.facade.encryptions.encrypt.IEncryptFacade;
import dev.gml.herbarium_backend.role.RoleService;

@ExtendWith(MockitoExtension.class)
class RegisterServiceTest {

        @Mock
        private UserService userService;

        @Mock
        private UserRepository userRepository;

        @Mock
        private RoleService roleService;

        @Mock
        private IDecryptFacade decryptFacade;

        @Mock
        private IEncryptFacade encryptFacade;

        @InjectMocks
        private RegisterService registerService;

        @Test
        void register_Success() {
                // --- Arrange ---
                // "c2VjcmV0MTIz" is Base64 for "secret123".
                RegisterDTORequest request = new RegisterDTORequest(
                                "test@example.com",
                                "c2VjcmV0MTIz",
                                "c2VjcmV0MTIz");

                UserEntity savedUser = UserEntity.builder()
                                .id(1L)
                                .email("test@example.com")
                                .password("bcryptHashedPassword")
                                .build();

                when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());
                when(decryptFacade.decode("base64", "c2VjcmV0MTIz")).thenReturn("secret123");
                when(encryptFacade.encode("bcrypt", "secret123")).thenReturn("bcryptHashedPassword");
                when(roleService.assignDefaultRole()).thenReturn(Set.of());
                when(userService.save(any(UserEntity.class))).thenReturn(savedUser);

                // --- Act ---
                UserEntity result = registerService.registerUser(request);

                // --- Assert ---
                assertNotNull(result);
                assertEquals("test@example.com", result.getEmail());
                assertEquals("bcryptHashedPassword", result.getPassword());
                verify(userRepository, times(1)).findByEmail("test@example.com");
                verify(userService, times(1)).save(any(UserEntity.class));
        }

        @Test
        void registerUser_DuplicateEmail() {
                // --- Arrange ---
                RegisterDTORequest request = new RegisterDTORequest(
                                "existing@example.com",
                                "c2VjcmV0MTIz",
                                "c2VjcmV0MTIz");

                UserEntity existingUser = UserEntity.builder()
                                .email("existing@example.com")
                                .build();

                when(userRepository.findByEmail("existing@example.com")).thenReturn(Optional.of(existingUser));

                // --- Act & Assert ---
                IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                                () -> registerService.registerUser(request));

                assertEquals("Email already registered", exception.getMessage());
                verify(userRepository, times(1)).findByEmail("existing@example.com");
                verify(userService, never()).save(any(UserEntity.class));
        }

        @Test
        void registerUser_InvalidEmail() {
                // --- Arrange ---
                RegisterDTORequest request = new RegisterDTORequest(
                                "invalid-email",
                                "c2VjcmV0MTIz",
                                "c2VjcmV0MTIz");

                when(userRepository.findByEmail("invalid-email")).thenReturn(Optional.empty());

                // --- Act & Assert ---
                IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                                () -> registerService.registerUser(request));

                assertEquals("Email should be valid", exception.getMessage());
                verify(userRepository, times(1)).findByEmail("invalid-email");
                verify(userService, never()).save(any(UserEntity.class));
        }

        @Test
        void registerUser_PasswordMismatch() {
                // --- Arrange ---
                RegisterDTORequest request = new RegisterDTORequest(
                                "test@example.com",
                                "c2VjcmV0MTIz",
                                "ZGlmZmVyZW50");

                when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());

                // --- Act & Assert ---
                IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                                () -> registerService.registerUser(request));

                assertEquals("Passwords do not match", exception.getMessage());
                verify(userRepository, times(1)).findByEmail("test@example.com");
                verify(userService, never()).save(any(UserEntity.class));
        }

        @Test
        void registerUser_ShortPassword() {
                // --- Arrange ---
                // "c2hvcnQ=" is Base64 for "short"
                RegisterDTORequest request = new RegisterDTORequest(
                                "test@example.com",
                                "c2hvcnQ=",
                                "c2hvcnQ=");

                when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());
                when(decryptFacade.decode("base64", "c2hvcnQ=")).thenReturn("short");

                // --- Act & Assert ---
                IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                                () -> registerService.registerUser(request));

                assertEquals("Password must be at least 6 characters", exception.getMessage());
                verify(userRepository, times(1)).findByEmail("test@example.com");
                verify(userService, never()).save(any(UserEntity.class));
        }

}
