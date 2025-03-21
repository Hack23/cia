/**
 * This package provides the main entry points and configuration for the Citizen Intelligence Agency web application.
 *
 * Key classes and interfaces:
 * - CitizenIntelligenceAgencyHealthCheckServlet: Servlet for health check endpoint.
 * - CitizenIntelligenceAgencySpringVaadinServlet: Main Vaadin servlet for the application.
 * - CitizenIntelligenceAgencyUI: Main UI class for the application.
 * - CustomSpringUIProvider: Custom UI provider for Spring integration.
 * - ResourceServlet: Servlet for serving static resources.
 * - UiInstanceErrorHandler: Custom error handler for UI instances.
 * - VaadinSpringConfig: Spring configuration for Vaadin.
 *
 * Dependencies and relationships:
 * - Depends on com.vaadin for Vaadin framework classes and interfaces.
 * - Depends on org.springframework for Spring framework classes and interfaces.
 * - Depends on com.hack23.cia.service.api for application and configuration management services.
 * - Depends on com.hack23.cia.web.impl.ui.application.util for utility classes.
 *
 * The package is responsible for providing the main entry points and configuration for the Citizen Intelligence Agency web application. The key classes and interfaces within this package provide the necessary structure and implementation for the application's main functionalities. The package also has dependencies on other packages for specific functionalities, such as Vaadin framework, Spring framework, application and configuration management services, and utility classes.
 */
package com.hack23.cia.web.impl.ui.application;
