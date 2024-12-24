/**
 * CIA (Citizen Intelligence Agency) External Service Common Module.
 *
 * <p>This module provides common functionality and utilities for all external service
 * integrations in the CIA application. It implements core features for HTTP communication,
 * XML processing, and standardized external service access patterns.</p>
 *
 * <p>The module exports two packages:</p>
 * <ul>
 *   <li>{@code com.hack23.cia.service.external.common.api} - Common interfaces for external services</li>
 *   <li>{@code com.hack23.cia.service.external.common.impl} - Shared implementation components</li>
 * </ul>
 *
 * <p>Core Features:</p>
 * <ul>
 *   <li>HTTP Communication
 *     <ul>
 *       <li>Fluent HTTP client implementation</li>
 *       <li>Standardized request handling</li>
 *       <li>Response processing utilities</li>
 *     </ul>
 *   </li>
 *   <li>XML Processing
 *     <ul>
 *       <li>JAXB integration</li>
 *       <li>JDOM2 XML handling</li>
 *       <li>Spring OXM support</li>
 *     </ul>
 *   </li>
 *   <li>Service Infrastructure
 *     <ul>
 *       <li>Common service patterns</li>
 *       <li>Error handling</li>
 *       <li>Logging framework integration</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 * <p>This module serves as the foundation for all external data source integrations,
 * providing consistent patterns and utilities for:</p>
 * <ul>
 *   <li>Swedish Parliament (Riksdagen) data access</li>
 *   <li>Election Authority (Val) integration</li>
 *   <li>World Bank data retrieval</li>
 *   <li>ESV (Swedish National Financial Management Authority) communication</li>
 * </ul>
 *
 */
open module com.hack23.cia.service.external.common {
	exports com.hack23.cia.service.external.common.api;
	exports com.hack23.cia.service.external.common.impl;
			
	requires org.slf4j;
	requires java.xml.bind;
	requires jakarta.activation;
	requires org.jdom2;
	requires spring.oxm;
	requires spring.context;
	requires org.apache.httpcomponents.httpclient;
	requires org.apache.httpcomponents.httpclient.fluent;
}