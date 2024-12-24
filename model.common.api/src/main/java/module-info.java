/**
 * CIA (Citizen Intelligence Agency) Common Model API Module.
 *
 * <p>This module defines the core model interfaces and common data structures used
 * throughout the CIA application. It provides the foundational API for all model
 * implementations across the system.</p>
 *
 * <p>The module exports a single package:</p>
 * <ul>
 *   <li>{@code com.hack23.cia.model.common.api} - Core model interfaces and common data structures</li>
 * </ul>
 *
 * <p>Key Components:</p>
 * <ul>
 *   <li>Base Model Interfaces
 *     <ul>
 *       <li>Entity definitions</li>
 *       <li>Data transfer objects</li>
 *       <li>Common model patterns</li>
 *     </ul>
 *   </li>
 *   <li>Shared Data Types
 *     <ul>
 *       <li>Common enumerations</li>
 *       <li>Base value objects</li>
 *       <li>Shared constants</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 * <p>This module serves as the foundation for all model implementations in the CIA
 * application, ensuring consistency across:</p>
 * <ul>
 *   <li>Political data models</li>
 *   <li>Financial metrics</li>
 *   <li>Institution profiles</li>
 *   <li>Performance indicators</li>
 * </ul>
 *
 * <p>The API is designed to be minimal yet extensible, providing only the essential
 * interfaces needed for model implementations while maintaining flexibility for
 * different data types and sources.</p>
 *
 */
open module com.hack23.model.common.api {
 exports com.hack23.cia.model.common.api;
}