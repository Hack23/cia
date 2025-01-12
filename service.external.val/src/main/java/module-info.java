/**
 * CIA (Citizen Intelligence Agency) Val (Swedish Election Authority) Service Module.
 *
 * <p>This module provides integration with the Swedish Election Authority's data and
 * offers standardized access to electoral district information, party data, and
 * election statistics.</p>
 *
 * <p>Key Features:</p>
 * <ul>
 *   <li>Electoral district mapping and details</li>
 *   <li>Party registration and tracking</li>
 *   <li>XML-based data retrieval and processing</li>
 * </ul>
 *
 * <p>Technologies / Integrations:</p>
 * <ul>
 *   <li>Spring Framework for service implementation</li>
 *   <li>SLF4J for logging</li>
 *   <li>Common external service framework</li>
 * </ul>
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
