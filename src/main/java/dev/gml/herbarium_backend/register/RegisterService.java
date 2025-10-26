package dev.gml.herbarium_backend.register;

import org.springframework.stereotype.Service;

import dev.gml.herbarium_backend.facade.encryptions.decrypt.IDecryptFacade;
import dev.gml.herbarium_backend.facade.encryptions.encrypt.IEncryptFacade;
import dev.gml.herbarium_backend.role.RoleService;
import dev.gml.herbarium_backend.user.UserEntity;
import dev.gml.herbarium_backend.user.UserRepository;
import dev.gml.herbarium_backend.user.UserService;

@Service
public class RegisterService {
    
    private final UserService userService;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final IDecryptFacade decryptFacade;
    private final IEncryptFacade encryptFacade;
    
    public RegisterService(UserService userService, UserRepository userRepository, RoleService roleService, IDecryptFacade decryptFacade, IEncryptFacade encryptFacade) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.decryptFacade = decryptFacade;
        this.encryptFacade = encryptFacade;
    }

    public UserEntity registerUser(RegisterDTORequest dto) {
        // Check if email already exists
        if (userRepository.findByEmail(dto.email()).isPresent()) {
            throw new IllegalArgumentException("Email already registered");
        }

        // Validate email format:
        if (!isValidEmail(dto.email())) {
            throw new IllegalArgumentException("Email should be valid");
        }
        
        // Validate passwords match
        if (!dto.passwordsMatch()) {
            throw new IllegalArgumentException("Passwords do not match");
        }
        
        // Validate password strength (after decoding)
        try {
            // Decode "base64" password first
            String passwordDecoded = decryptFacade.decode("base64", dto.password());
            
            // Then validate the actual password length
            if (passwordDecoded.length() < 6) {
                throw new IllegalArgumentException("Password must be at least 6 characters");
            }

            // Encrypt password with bcrypt
            String hashedPassword = encryptFacade.encode("bcrypt", passwordDecoded);

            UserEntity newUser = UserEntity.builder()
                .email(dto.email())
                .password(hashedPassword)
                .roles(roleService.assignDefaultRole())
                .build();
    
            return userService.save(newUser);

        } catch (IllegalArgumentException error) {
            // Re-throw with appropiate message:
            if (error.getMessage().contains("Invalid Base64")) {
                throw new IllegalArgumentException("Invalid password encoding!");
            }
            throw error;
        }
    }

    // Basic email validation
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email != null && email.matches(emailRegex);
    }
}
