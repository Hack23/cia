/**
 * CIA (Citizen Intelligence Agency) Riksdagen Utskottsförslag Model Module.
 *
 * <p>This module manages committee proposals (utskottsförslag) from the Swedish
 * Parliament, detailing recommended actions and decisions.</p>
 *
 * <p>Key Features:</p>
 * <ul>
 *   <li>Utskottsförslag entity mappings</li>
 *   <li>Structured representation of committee proposals</li>
 * </ul>
 *
 * <p>Technologies / Integrations:</p>
 * <ul>
 *   <li>Hibernate ORM for persistence</li>
 *   <li>HyperJAXB/Java XML Binding</li>
 * </ul>
 */
open module com.hack23.cia.model.external.riksdagen.utskottsforslag.impl {
	exports com.hack23.cia.model.external.riksdagen.utskottsforslag.impl;
	
	requires com.hack23.model.common.api;
    requires transitive com.hack23.cia.model.common.impl;
	requires hyperjaxb3.ejb.runtime;

	requires java.xml.bind;
	requires java.persistence;
	requires org.hibernate.orm.jpamodelgen;
	requires org.slf4j;
	requires org.apache.commons.lang3;
	requires jaxb2.basics.runtime;
	requires transitive java.xml;

}
