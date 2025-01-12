/**
 * CIA (Citizen Intelligence Agency) Val Kommunvalkrets Model Module.
 *
 * <p>This module contains the data structures to represent municipal electoral
 * districts (kommunvalkrets) in Sweden.</p>
 *
 * <p>Key Features:</p>
 * <ul>
 *   <li>Kommunvalkrets JPA entities</li>
 *   <li>Integration with the CIA model framework</li>
 * </ul>
 *
 * <p>Technologies / Integrations:</p>
 * <ul>
 *   <li>Hibernate ORM for persistence</li>
 *   <li>XML-based data mapping with JAXB</li>
 * </ul>
 */
open module com.hack23.cia.model.external.val.kommunvalkrets.impl {
	exports com.hack23.cia.model.external.val.kommunvalkrets.impl;
	
	requires com.hack23.model.common.api;
    requires transitive com.hack23.cia.model.common.impl;

	requires java.xml.bind;
	requires java.persistence;
	requires org.hibernate.orm.jpamodelgen;
	requires org.slf4j;
	requires org.apache.commons.lang3;
	requires jaxb2.basics.runtime;


}