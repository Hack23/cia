/**
 * Riksdagen Person List Module.
 *
 * <p>This module provides implementation for handling the person list from Riksdagen.</p>
 *
 * <p>Key Features:</p>
 * <ul>
 *   <li>Fetching and parsing person lists</li>
 *   <li>Data transformation and storage</li>
 *   <li>Integration with other Riksdagen modules</li>
 * </ul>
 *
 * <p>Technologies / Integrations:</p>
 * <ul>
 *   <li>Java Persistence API (JPA)</li>
 *   <li>Hibernate ORM</li>
 *   <li>SLF4J for logging</li>
 * </ul>
 *
 * @see com.hack23.cia.model.common.api
 */
open module com.hack23.cia.model.external.riksdagen.personlista.impl {
	exports com.hack23.cia.model.external.riksdagen.personlista.impl;
	
	requires com.hack23.model.common.api;
    requires transitive com.hack23.cia.model.common.impl;
	
	requires java.xml.bind;
	requires java.persistence;
	requires org.hibernate.orm.jpamodelgen;
	requires org.slf4j;
	requires org.apache.commons.lang3;
	requires jaxb2.basics.runtime;
}