/**
 * <b>Decryption Operations Package</b>
 * <p>
 * This package handles password decoding operations for processing user input.
 * It provides a facade interface for different decoding algorithms.
 *
 * <p>
 * <b>Components:</b>
 * 
 * <ul>
 *   <li>{@link dev.gml.herbarium_backend.facade.encryptions.decrypt.IDecryptFacade} - Decryption interface</li>
 *   <li>{@link dev.gml.herbarium_backend.facade.encryptions.decrypt.DecryptFacade} - Base64 implementation</li>
 * </ul>
 *
 * <p>
 * <b>Supported Algorithms:</b>
 * <ul>
 *   <li><b>Base64</b> - Used for decoding transmitted passwords</li>
 * </ul>
 *
 * @author gml
 * @version 1.0
 * @since 2025
 * @see dev.gml.herbarium_backend.facade.encryptions.decrypt.IDecryptFacade
 * @see dev.gml.herbarium_backend.facade.encryptions.decrypt.DecryptFacade
 */
package dev.gml.herbarium_backend.facade.encryptions.decrypt;