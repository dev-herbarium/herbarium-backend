/**
 * <b>Encryption Facades Package</b>
 * <p>
 * This package provides abstraction layers for encryption and decryption operations
 * used throughout the application. It separates encryption concerns from business logic.
 *
 * <p>
 * <b>Package Structure:</b>
 * 
 * <ul>
 *   <li><b>encrypt</b> - Password hashing and encryption operations</li>
 *   <li><b>decrypt</b> - Password decoding and decryption operations</li>
 * </ul>
 *
 * <p>
 * <b>Supported Operations:</b>
 * 
 * <ul>
 *   <li>Base64 encoding/decoding for password transmission</li>
 *   <li>BCrypt hashing for secure password storage</li>
 *   <li>Extensible design for additional encryption methods</li>
 * </ul>
 *
 * @author gml
 * @version 1.0
 * @since 2025
 */
package dev.gml.herbarium_backend.facade.encryptions;