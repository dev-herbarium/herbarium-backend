/**
 * <b>Role Management Package</b>
 * <p>
 * This package handles role-based authorization and security within the application.
 * It defines user roles, manages role assignments, and provides role-related services.
 *
 * <p>
 * <b>Package Components:</b>
 * 
 * <ul>
 *   <li>{@link dev.gml.herbarium_backend.role.RoleEntity} - Role entity definition</li>
 *   <li>{@link dev.gml.herbarium_backend.role.RoleRepository} - Role data access</li>
 *   <li>{@link dev.gml.herbarium_backend.role.RoleService} - Role business logic</li>
 * </ul>
 *
 * <p>
 * <b>Key Responsibilities:</b>
 * 
 * <ul>
 *   <li>Role entity definition and security configuration</li>
 *   <li>Default role assignment for new users</li>
 *   <li>Role-based access control foundation</li>
 *   <li>Integration with user management system</li>
 * </ul>
 *
 * <p>
 * <b>Default Roles:</b>
 * 
 * <ul>
 *   <li><b>ROLE_ADMIN</b> - Administrative privileges</li>
 *   <li><b>ROLE_USER</b> - Standard user privileges (default for new users)</li>
 * </ul>
 *
 * @author gml
 * @version 1.0
 * @since 2025
 * @see dev.gml.herbarium_backend.role.RoleEntity
 * @see dev.gml.herbarium_backend.role.RoleRepository
 * @see dev.gml.herbarium_backend.role.RoleService
 */
package dev.gml.herbarium_backend.role;
