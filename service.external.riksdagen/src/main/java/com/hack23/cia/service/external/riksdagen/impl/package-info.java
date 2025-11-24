/**
 * Implementation classes for Swedish Parliament (Riksdagen) API integration.
 *
 * <p>This package contains the concrete implementations of the Riksdagen API interfaces
 * for fetching political data including:
 * <ul>
 *   <li>Person data (politicians)</li>
 *   <li>Ballot/voting data</li>
 *   <li>Document data</li>
 *   <li>Committee proposals</li>
 * </ul>
 *
 * <p>All implementations include:
 * <ul>
 *   <li>Input validation using {@link com.hack23.cia.service.external.riksdagen.impl.UrlHelper}</li>
 *   <li>Lenient XML parsing using {@link com.hack23.cia.service.external.riksdagen.impl.LenientValidationEventHandler}</li>
 *   <li>Date handling using {@link com.hack23.cia.service.external.riksdagen.impl.RiksdagenDateUtil}</li>
 *   <li>Comprehensive error logging and exception handling</li>
 * </ul>
 *
 * @see com.hack23.cia.service.external.riksdagen.api
 */
package com.hack23.cia.service.external.riksdagen.impl;