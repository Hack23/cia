/**
 * CIA (Citizen Intelligence Agency) World Bank Countries Data Model Module.
 *
 * <p>This module defines entities for World Bank country data, including metadata
 * about nations, regions, and other related attributes.</p>
 *
 * <p>Key Features:</p>
 * <ul>
 *   <li>Country data structures and JPA mappings</li>
 *   <li>Integration with World Bank data sets</li>
 * </ul>
 *
 * <p>Technologies / Integrations:</p>
 * <ul>
 *   <li>Hibernate ORM for persistence</li>
 *   <li>JAXB for XML-based data processing</li>
 * </ul>
 */
open module com.hack23.cia.model.external.worldbank.countries.impl {
	exports com.hack23.cia.model.external.worldbank.countries.impl;
	
	requires com.hack23.model.common.api;
    requires transitive com.hack23.cia.model.common.impl;

	requires java.xml.bind;
	requires java.persistence;
	requires org.hibernate.orm.jpamodelgen;
	requires org.slf4j;
	requires org.apache.commons.lang3;
	requires jaxb2.basics.runtime;


}