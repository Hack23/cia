/**
 * CIA (Citizen Intelligence Agency) Agent API Module.
 *
 * <p>This module defines the public API for the CIA agent component, which is responsible
 * for data collection and processing operations within the Citizen Intelligence Agency
 * application. It provides interfaces and definitions for agent operations while maintaining
 * a clear separation between the API and its implementation.</p>
 *
 * <p>The module exports a single package:</p>
 * <ul>
 *   <li>{@code com.hack23.cia.service.component.agent.api} - Core agent interfaces and definitions</li>
 * </ul>
 *
 * <p>Key Aspects:</p>
 * <ul>
 *   <li>Defines contracts for data collection agents</li>
 *   <li>Specifies interfaces for processing political and financial data</li>
 *   <li>Supports OSINT (Open Source Intelligence) operations</li>
 *   <li>Integrates with internal user model for operation authentication and authorization</li>
 * </ul>
 *
 * <p>This API module is designed to be lightweight, only requiring the internal application
 * user model implementation for authentication and authorization purposes. It serves as the
 * contract between the CIA application and various data collection implementations.</p>
 *
 * @see com.hack23.cia.model.internal.application.user.impl
 */
open module com.hack23.cia.service.component.agent.api {
	exports com.hack23.cia.service.component.agent.api;

	requires com.hack23.cia.model.internal.application.user.impl;
}