package dev.gml.herbarium_backend.role;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <b>Repository interface for {@link RoleEntity} data access operations</b>
 * <p>
 * This interface extends {@link JpaRepository} and provides standard CRUD operations
 * for role management. Spring Data JPA automatically implements this interface
 * at runtime, eliminating the need for manual implementation.
 * <p>
 * <b>Supported Operations:</b>
 * <ul>
 *      <li>Standard CRUD operations (save, findById, findAll, delete)</li>
 *      <li>Pagination and storing support</li>
 *      <li>Query derivation from method names</li>
 * </ul>
 * <p>
 * <b>Usage Example:</b>
 * <pre>
 * {@code
 * // Find role by ID
 * RoleEntity userRole = roleRepository.findById(2L).orElse(null);
 * 
 * // Save new role
 * RoleEntity newRole = roleRepository.save(roleEntity);
 * }
 * </pre>
 * 
 * @author gml
 * @version 1.0
 * @since 2025
 * @see RoleEntity
 * @see JpaRepository
 * @see RoleService
 */
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    // Spring Data JPA automatically provides implementation.
    // Custom query methods can be added here as needed.
}
