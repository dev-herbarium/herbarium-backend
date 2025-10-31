/**
 * <b>Encryption Operations Package</b>
 * <p>
 * This package handles password encryption and hashing operations for secure storage.
 * It provides a facade interface for different encryption algorithms.
 *
 * <p>
 * <b>Components:</b>
 * <ul>
 *   <li>{@link dev.gml.herbarium_backend.facade.encryptions.encrypt.IEncryptFacade} - Encryption interface</li>
 *   <li>{@link dev.gml.herbarium_backend.facade.encryptions.encrypt.EncryptFacade} - BCrypt implementation</li>
 * </ul>
 *
 * <p>
 * <b>Supported Algorithms:</b>
 * 
 * <ul>
 *   <li><b>BCrypt</b> - Used for password hashing with salt</li>
 * </ul>
 *
 * @author gml
 * @version 1.0
 * @since 2025
 * @see dev.gml.herbarium_backend.facade.encryptions.encrypt.IEncryptFacade
 * @see dev.gml.herbarium_backend.facade.encryptions.encrypt.EncryptFacade
 */
package dev.gml.herbarium_backend.facade.encryptions.encrypt;