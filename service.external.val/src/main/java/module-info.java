/**
 * CIA (Citizen Intelligence Agency) Val (Swedish Election Authority) Service Module.
 *
 * <p>This module provides integration with the Swedish Election Authority's (Valmyndigheten)
 * data services, enabling access to electoral district information, political party data,
 * and election-related statistics.</p>
 *
 * <p>The module exports two packages:</p>
 * <ul>
 *   <li>{@code com.hack23.cia.service.external.val.api} - Public interfaces for election data access</li>
 *   <li>{@code com.hack23.cia.service.external.val.impl} - Implementation of election service interfaces</li>
 * </ul>
 *
 * <p>Electoral Data Categories:</p>
 * <ul>
 *   <li>Electoral Districts
 *     <ul>
 *       <li>Parliamentary constituencies (Riksdagsvalkrets)</li>
 *       <li>Municipal districts (Kommunvalkrets)</li>
 *       <li>County council districts (Landstingvalkrets)</li>
 *     </ul>
 *   </li>
 *   <li>Political Parties
 *     <ul>
 *       <li>Party registration data</li>
 *       <li>Party information</li>
 *       <li>Electoral participation</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 * <p>This module integrates with the common external service framework and provides
 * standardized access to election authority data as part of the CIA's comprehensive
 * political monitoring system.</p>
 *
 * <p>Key Features:</p>
 * <ul>
 *   <li>Complete electoral district mapping</li>
 *   <li>Political party registration tracking</li>
 *   <li>XML-based data processing</li>
 *   <li>Spring-managed service implementation</li>
 * </ul>
 *
 * <p>The service supports analysis of:</p>
 * <ul>
 *   <li>Electoral geography and constituencies</li>
 *   <li>Political party presence and distribution</li>
 *   <li>Electoral system structure</li>
 * </ul>
 *
 * @see com.hack23.cia.service.external.common
 * @see com.hack23.cia.model.external.val
 */
open module com.hack23.cia.service.external.val {
	exports com.hack23.cia.service.external.val.api;
	exports com.hack23.cia.service.external.val.impl;
	
	requires java.xml.bind;
	requires transitive spring.beans;
	requires org.slf4j;
	
	requires transitive com.hack23.cia.service.external.common;

	requires transitive com.hack23.cia.model.external.val.riksdagsvalkrets.impl;
	requires transitive com.hack23.cia.model.external.val.kommunvalkrets.impl;
	requires transitive com.hack23.cia.model.external.val.partier.impl;
	requires transitive com.hack23.cia.model.external.val.landstingvalkrets.impl;
}
