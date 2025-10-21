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

        // Validate passwords match
        if (!dto.passwordsMatch()) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        // Validate password strength
        if (dto.password().length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }

        // Decode base64 password
        String passwordDecoded = decryptFacade.decode("base64", dto.password());

        // Encrypt password with bcrypt
        String hashedPassword = encryptFacade.encode("bcrypt", passwordDecoded);

        UserEntity newUser = UserEntity.builder()
            .email(dto.email())
            .password(hashedPassword)
            .roles(roleService.assignDefaultRole())
            .build();

        return userService.save(newUser);
    }
}
