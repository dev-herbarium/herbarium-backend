package dev.gml.herbarium_backend.register;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import dev.gml.herbarium_backend.config.TestSecurityConfig;
import dev.gml.herbarium_backend.user.UserEntity;

@WebMvcTest(RegisterController.class)
// (!) Import the following "test security configuration" to disable security
// for testing all requests without authentication.
@Import(TestSecurityConfig.class)
class RegisterControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockitoBean
        private RegisterService registerService;

        @Test
        void registerUser_Success() throws Exception {
                // --- Arrange ---
                UserEntity user = UserEntity.builder()
                                .id(1L)
                                .email("test@example.com")
                                .password("hashedPassword")
                                .build();

                when(registerService.registerUser(any(RegisterDTORequest.class))).thenReturn(user);

                String requestBody = """
                                {
                                    "email": "test@example.com",
                                    "password": "c2VjcmV0MTIz",
                                    "confirmPassword": "c2VjcmV0MTIz"
                                }
                                """;

                // --- Act & Assert ---
                mockMvc.perform(post("/api/v1/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.message").value("User registered successfully"))
                                .andExpect(jsonPath("$.email").value("test@example.com"))
                                .andExpect(jsonPath("$.userId").value(1));
        }

        @Test
        void registerUser_ValidatorError() throws Exception {
                // --- Arrange ---
                when(registerService.registerUser(any(RegisterDTORequest.class)))
                                .thenThrow(new IllegalArgumentException("Email already registered"));

                String requestBody = """
                                {
                                    "email": "existing@example.com",
                                    "password": "c2VjcmV0MTIz",
                                    "confirmPassword": "c2VjcmV0MTIz"
                                }
                                """;

                // --- Act & Assert ---
                mockMvc.perform(post("/api/v1/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                                .andExpect(status().isBadRequest())
                                .andExpect(jsonPath("$.message").value("Registration failed: Email already registered"))
                                .andExpect(jsonPath("$.email").value("existing@example.com"))
                                .andExpect(jsonPath("$.userId").isEmpty());
        }
}