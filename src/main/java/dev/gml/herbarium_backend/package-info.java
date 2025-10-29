/**
 * <b>Herbarium Backend Application</b>
 * <p>
 * The Herbarium Backend is a Spring Boot application that provides
 * <a href="https://en.wikipedia.org/wiki/REST">RESTful</a> API services
 * for plant management and user authentication. This application serves as the
 * backend for the Herbarium system, handling user registration, authentication, and
 * plant data management.
 * </p>
 * 
 * <p>
 * <b>Architecture Overview:</b>
 * </p>
 * <ul>
 *      <li><b>Controller Layer</b> - Handles HTTP requests and responses</li>
 *      <li><b>Service Layer</b> - Contains business logic and validation</li>
 *      <li><b>Repository Layer</b> - Manages data access and persistence</li>
 *      <li><b>Entity Layer</b> - Defines data models and JPA mappings</li>
 * </ul>
 * 
 * <p>
 * <b>Key Features:</b>
 * </p>
 * <ul>
 *      <li>User registration with role-based authentication</li>
 *      <li>Secure password handling with Base64 encoding and BCrypt hashing</li>
 *      <li>RESTful API with comprehensive Swagger documentation</li>
 *      <li>Docker Compose support for MySQL database</li>
 *      <li>Test coverage with JUnit and Mockito</li>
 * </ul>
 * 
 * <p>
 * <b>Technology Stack:</b>
 * </p>
 * <ul>
 *      <li>Spring Boot 3.5.6</li>
 *      <li>Spring Security</li>
 *      <li>Spring Web</li>
 *      <li>Spring Data JPA</li>
 *      <li>MySQL / H2 Database</li>
 *      <li>Docker</li>
 *      <li>Maven</li>
 *      <li>Lombok</li>
 *      <li>DevTools</li>
 *      <li>Testcontainers</li>
 *      <li>Docker Compose</li>
 * </ul>
 * 
 * @author gml
 * @version 0.0.1-SNAPSHOT
 * @since 2025
 */
package dev.gml.herbarium_backend;